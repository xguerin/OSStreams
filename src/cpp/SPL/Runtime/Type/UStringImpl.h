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

#ifndef SPL_RUNTIME_TYPE_USTRING_IMPL_H
#define SPL_RUNTIME_TYPE_USTRING_IMPL_H

#include <unicode/unistr.h>

namespace SPL {
struct ustringImpl : public icu::UnicodeString
{
    ustringImpl(const icu::UnicodeString& str)
      : icu::UnicodeString(str)
    {}
    ustringImpl(const ustringImpl& str)
      : icu::UnicodeString(str)
    {}
};
};

#endif // SPL_RUNTIME_TYPE_USTRING_IMPL_H
