/*
 * Copyright 2021 IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <cassert>
#include <cmath>
#include <cstring>
#include <iostream>
#include <string>
#include <vector>

#include <SPL/Runtime/Serialization/NativeByteBuffer.h>
#include <SPL/Runtime/Type/SPLType.h>
#include <UTILS/DistilleryApplication.h>
#include <UTILS/DistilleryException.h>
#include <unicode/unistr.h>

using namespace icu;
using namespace std;
using namespace std::tr1;

UTILS_NAMESPACE_USE
using namespace SPL;

class nativebuftest : public DistilleryApplication
{
  public:
    virtual int run(const arg_vector_t& args)
    {
        int32_t ii[] = { 1, 4, 5, 6, 3, 1, 2, 7 };
        const static char* strs[] = { "no error",
                                      "the data source table is full",
                                      "data source read method failed",
                                      "catalog error",
                                      "page has already been released",
                                      "data source is not registered",
                                      "hash collision",
                                      "eof of file" };

        NativeByteBuffer s(4);

        unsigned i;
        unsigned max = sizeof(ii) / sizeof(int);

        for (i = 0; i < max; ++i) {
            s.addInt32(ii[i]);
            // NT is for "null terminated"
            s.addNTStr(strs[i]);
        }
        s.addDouble(4 * atan(static_cast<double>(1)));
        s.addInt32(1024 * 1024 * 1024);
        s.addInt64(static_cast<int64_t>(128) * static_cast<int64_t>(1024) *
                   static_cast<int64_t>(1024) * static_cast<int64_t>(1024));
        string s1;
        string s2("IBM");
        s.addSTLString(s1);
        s.addSTLString(s2);

        SPL::ustring ustr((UChar*)L"International Business Machines");
        s.addUnicodeString(ustr);

        for (i = 0; i < max; ++i) {
            int32_t n32 = s.getInt32();
            cout << "int32_t: " << n32 << endl;
            assert(n32 == ii[i]);
            char* ptr = s.getNTStr();
            char str[strlen(ptr) + 1];
            strncpy(str, ptr, strlen(ptr) + 1);
            cout << "String: " << str << endl;
            assert(strcmp(str, strs[i]) == 0);
        }
        double d = s.getDouble();
        cout << "Double: " << d << endl;
        assert(d - 4 * atan(static_cast<double>(1)) < 0.000001);
        int32_t i32 = s.getInt32();
        cout << "int32: " << i32 << endl;
        assert(i32 == 1024 * 1024 * 1024);
        int64_t i64 = s.getInt64();
        cout << "int64: " << i64 << endl;
        assert(i64 == static_cast<int64_t>(128) * static_cast<int64_t>(1024) *
                        static_cast<int64_t>(1024) * static_cast<int64_t>(1024));
        string rs1(s.getSTLString());
        cout << "string1: [" << rs1 << "]" << endl;
        assert(rs1.empty());
        string rs2;
        s.getSTLString(rs2);
        cout << "string2: [" << rs2 << "]" << endl;
        assert(rs2 == s2);

        cout << endl;
        SPL::ustring ustr2(s.getUnicodeString());
        assert(ustr == ustr2);
        const UChar* uptr = ustr.getBuffer();
        const UChar* uptr2 = ustr2.getBuffer();
        cout << "unicode string length: " << ustr.length() << ":" << ustr2.length() << endl;
        assert(ustr.length() == ustr2.length());
        for (int k = 0; k < ustr2.length(); ++k) {
            cout << uptr2[k] << ":" << uptr[k] << " (" << (char)uptr[k] << ")" << endl;
            assert(uptr2[k] == uptr[k]);
        }
        cout << endl;

        cout << s.getSerializedDataSize() << endl;

        char my_strings[4] = "aaa";
        NativeByteBuffer bad_resize((unsigned char*)my_strings, 4);
        try {
            bad_resize.addNTStr("philippe");
        } catch (DistilleryException& ex) {
            cout << "exception caught " << ex.getExplanation() << endl;
        }

        NativeByteBuffer tmp;
        tmp.addInt32(1);
        tmp.addInt32(2);
        tmp.addInt32(3);

        const unsigned char* buf = tmp.getPtr();

        // we need to explicitly const_cast it because we need to let the
        // compiler know that we know what we are doing
        NativeByteBuffer tmp2(const_cast<unsigned char*>(buf), tmp.getSerializedDataSize());

        /*
           cout << tmp2.getInt32() << endl;
           cout << tmp2.getInt32() << endl;
           cout << tmp2.getInt32() << endl;
         */

        assert(tmp2.getInt32() == 1);
        assert(tmp2.getInt32() == 2);
        assert(tmp2.getInt32() == 3);

        NativeByteBuffer tmp3;
        tmp3.addSTLString("Henrique Andrade");
        tmp3.addUInt32(35);
        tmp3.addFloat(3.1415927);
        const char* ts = "19 Skyline Dr -- Hawthorne/NY -- 10532";
        tmp3.addCharSequence(ts, strlen(ts));
        string xx(tmp3.getSTLString());
        assert(xx == "Henrique Andrade");
        assert(tmp3.getUInt32() == 35);
        assert(static_cast<uint32_t>(tmp3.getFloat() - 3.1415927) == 0);
        uint32_t chssize;
        char* chs = tmp3.getCharSequence(chssize);
        char str[chssize + 1];
        memcpy(str, chs, chssize);
        str[chssize] = '\0';
        cout << endl << "str is [" << str << "]" << endl;
        assert(strcmp(str, ts) == 0);

        NativeByteBuffer tmp4(15);
        const char* ts0 = "0123456789012345";
        tmp4.addCharSequence(ts0, strlen(ts0) - 1);
        char ts1[10 + 1];
        char ts2[5 + 1];
        chs = tmp4.getFixedCharSequence(10);
        memcpy(ts1, chs, 10);
        ts1[10] = '\0';
        cout << "ts1 is [" << ts1 << "]" << endl;
        chs = tmp4.getFixedCharSequence(5);
        memcpy(ts2, chs, 5);
        ts2[5] = '\0';
        cout << "ts2 is [" << ts2 << "]" << endl;

        cout << endl;
        NativeByteBuffer tmp5;
        char char_val = 'A', char_val_r;
        UChar UChar_val = 4, UChar_val_r;
        bool bool_val = true, bool_val_r;
        int8_t int8_t_val = -10, int8_t_val_r;
        int16_t int16_t_val = -40, int16_t_val_r;
        int32_t int32_t_val = -80, int32_t_val_r;
        int64_t int64_t_val = -120, int64_t_val_r;
        uint32_t uint32_t_val = 80, uint32_t_val_r;
        uint64_t uint64_t_val = 120, uint64_t_val_r;
        float float_val = 10.45, float_val_r;
        double double_val = 0.531e-8, double_val_r;
        char const* char_star_val = "Spade";
        char* char_star_val_r;
        string string_val = "Space", string_val_r;
        SPL::ustring UnicodeString_val = "State", UnicodeString_val_r;
        void *void_star_val = &int8_t_val, *void_star_val_r;

        tmp5 << char_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 1);
        tmp5 << UChar_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 3);
        tmp5 << bool_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 4);
        tmp5 << int8_t_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 5);
        tmp5 << int16_t_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 7);
        tmp5 << int32_t_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 11);
        tmp5 << int64_t_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 19);
        tmp5 << uint32_t_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 23);
        tmp5 << uint64_t_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 31);
        tmp5 << float_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 35);
        tmp5 << double_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 43);
        tmp5 << char_star_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 49);
        tmp5 << string_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 55);
        tmp5 << UnicodeString_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 66);
        tmp5 << void_star_val;
        cout << "size " << tmp5.getSerializedDataSize() << endl;
        assert(tmp5.getSerializedDataSize() == 67 + sizeof(void*));

        cout << endl;
        NativeByteBuffer tmp6(const_cast<unsigned char*>(tmp5.getPtr()),
                              tmp5.getSerializedDataSize());
        tmp6 >> char_val_r;
        cout << char_val_r << " read" << endl;
        tmp6 >> UChar_val_r;
        cout << UChar_val_r << " read" << endl;
        tmp6 >> bool_val_r;
        cout << bool_val_r << " read" << endl;
        tmp6 >> int8_t_val_r;
        cout << int8_t_val_r << " read" << endl;
        tmp6 >> int16_t_val_r;
        cout << int16_t_val_r << " read" << endl;
        tmp6 >> int32_t_val_r;
        cout << int32_t_val_r << " read" << endl;
        tmp6 >> int64_t_val_r;
        cout << int64_t_val_r << " read" << endl;
        tmp6 >> uint32_t_val_r;
        cout << uint32_t_val_r << " read" << endl;
        tmp6 >> uint64_t_val_r;
        cout << uint64_t_val_r << " read" << endl;
        tmp6 >> float_val_r;
        cout << float_val_r << " read" << endl;
        tmp6 >> double_val_r;
        cout << double_val_r << " read" << endl;
        tmp6 >> char_star_val_r;
        cout << char_star_val_r << " read" << endl;
        tmp6 >> string_val_r;
        cout << string_val_r << " read" << endl;
        tmp6 >> UnicodeString_val_r;
        cout << /*UnicodeString_val_r <<*/ "[UnicodeString] read" << endl;
        tmp6 >> void_star_val_r;
        cout << void_star_val_r << " read" << endl;
        assert(tmp6.getNRemainingBytes() == 0);

        assert(char_val == char_val_r);
        assert(UChar_val == UChar_val_r);
        assert(bool_val == bool_val_r);
        assert(int8_t_val == int8_t_val_r);
        assert(int16_t_val == int16_t_val_r);
        assert(int32_t_val == int32_t_val_r);
        assert(int64_t_val == int64_t_val_r);
        assert(uint32_t_val == uint32_t_val_r);
        assert(uint64_t_val == uint64_t_val_r);

        NativeByteBuffer tmp6_1;
        vector<int> vec_int;
        for (int z = 0; z < 10; ++z) {
            vec_int.push_back(z);
        }
        vector<int> vec_int_r;
        unordered_set<int> hset_int;
        for (int z = 0; z < 10; ++z) {
            hset_int.insert(z);
        }
        unordered_set<int> hset_int_r;
        unordered_map<int, int> hmap_int;
        for (int z = 0; z < 10; ++z) {
            hmap_int.insert(make_pair(z, 10 - z));
        }
        unordered_map<int, int> hmap_int_r;
        SPL::map<int, int> rmap_int;
        for (int z = 0; z < 10; ++z) {
            rmap_int.insert(make_pair(z, 10 - z));
        }
        SPL::map<int, int> rmap_int_r;

        tmp6_1 << vec_int << hset_int << hmap_int << rmap_int;

        tmp6_1 >> vec_int_r >> hset_int_r >> hmap_int_r >> rmap_int_r;

        assert(vec_int == vec_int_r);
        assert(hset_int == hset_int_r);
        assert(hmap_int == hmap_int_r);
        assert(rmap_int == rmap_int_r);

        NativeByteBuffer tmp7;
        long double ld1 = 3.1415927;
        tmp7.addLongDouble(ld1);
        long double ld2 = tmp7.getLongDouble();
        assert(static_cast<uint32_t>(ld1 - ld2) == 0);
        (void)ld2;

#if defined(__x86_64__) || defined(__powerpc__)
        union
        {
            float f;
            uint32_t i;
        } vf1, vf2;
        vf1.f = float_val;
        vf2.f = float_val_r;
        if (vf1.i != vf2.i) {
            THROW(Distillery, "assert(vf1.i==vf2.i)");
        }
        union
        {
            double d;
            uint64_t i;
        } vd1, vd2;
        vd1.d = double_val;
        vd2.d = double_val_r;
        if (vd1.i != vd2.i) {
            THROW(Distillery, "assert(vd1.i==vd2.i)");
        }
#else
        assert(*reinterpret_cast<uint32_t*>(&float_val) ==
               *reinterpret_cast<uint32_t*>(&float_val_r));
        assert(*reinterpret_cast<uint64_t*>(&double_val) ==
               *reinterpret_cast<uint64_t*>(&double_val_r));
#endif
        assert(0 == strcmp(char_star_val, char_star_val_r));
        assert(string_val == string_val_r);
        assert(UnicodeString_val == UnicodeString_val_r);
        assert(void_star_val == void_star_val_r);

        // testing if binary characters and STL strings go through serialization
        // correctly
        string binstr;
        binstr += static_cast<char>('\0');
        binstr += static_cast<char>('\t');
        binstr += static_cast<char>('\n');
        binstr += "henrique";
        binstr += static_cast<char>('\0');
        binstr += static_cast<char>('\0');
        cout << "binstr '" << binstr << "' has " << binstr.size() << " characters" << endl;
        NativeByteBuffer binstrbuf;
        binstrbuf.addSTLString(binstr);
        string frombufbinstr;
        binstrbuf.getSTLString(frombufbinstr);
        assert(binstr == frombufbinstr);
        const char* frombufbinstrptr = frombufbinstr.c_str();
        if (frombufbinstrptr[0] != '\0' || frombufbinstrptr[1] != '\t' ||
            frombufbinstrptr[2] != '\n' || frombufbinstrptr[3] != 'h') {
            throw "serialized and deserialized version do not seem to match";
        }

        {
            NativeByteBuffer largeBuffer1;
            largeBuffer1.setICursor(std::numeric_limits<uint32_t>::max());
            bool failed = false;
            try {
                largeBuffer1.addUInt64(10);
            } catch (const DistilleryException& ex) {
                failed = true;
                cout << "exception caught as expected: " << ex.getExplanation() << endl;
            }
            if (!failed) {
                THROW(Distillery, "did not catch expected exception");
            }
        }

#if __LP64__
        {
            NativeByteBuffer largeBuffer2;
            largeBuffer2.setICursor(std::numeric_limits<uint32_t>::max() - sizeof(uint32_t));
            bool failed = false;
            try {
                largeBuffer2.addUInt32(3);
            } catch (const DistilleryException& ex) {
                failed = true;
                cout << "exception caught " << ex.getExplanation() << endl;
            }
            if (failed) {
                THROW(Distillery, "unexpected exception was caught");
            }
        }
#endif

        cout << endl << "All NativeByteBuffer tests succeeded" << endl;

        return 0;
    }
};

MAIN_APP(nativebuftest);
