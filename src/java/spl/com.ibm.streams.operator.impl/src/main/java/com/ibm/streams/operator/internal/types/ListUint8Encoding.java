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

package com.ibm.streams.operator.internal.types;

import com.ibm.streams.operator.internal.network.TupleSerialization.BLen;
import com.ibm.streams.operator.internal.network.TypeEncoding;
import com.ibm.streams.operator.internal.types.ListInt8.ByteList;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/** Uses ListInt8.ByteList for efficiency. See ListUint8 for details. */
class ListUint8Encoding implements TypeEncoding {

  private final BLen blen;

  ListUint8Encoding() {
    this(BLen.UNBOUNDED);
  }

  ListUint8Encoding(BLen blen) {
    this.blen = blen;
  }

  final BLen getBLen() {
    return blen;
  }

  @Override
  public Object readValue(ByteBuffer bytes) {
    return readList(bytes);
  }

  final List<Byte> readList(ByteBuffer bytes) {
    final int n = getBLen().getLength(bytes);
    if (n == 0) return Collections.emptyList();

    byte[] data = new byte[n];
    bytes.get(data);
    return new ByteList(data);
  }

  @Override
  public int getSerializedSize() {
    return VARIABLE_LENGTH;
  }

  @Override
  public long getMaxSerializedSize(Object value) {
    List<?> list = (List<?>) value;
    return getBLen().getSerializedSize() + list.size();
  }

  @Override
  public void writeValue(ByteBuffer bytes, Object value) {
    writeList(bytes, value);
  }

  final int writeList(ByteBuffer bytes, Object value) {
    final List<?> list = (List<?>) value;
    final int n = list.size();
    getBLen().putLength(bytes, n);
    if (list instanceof ByteList) {
      ((ByteList) list).putInternal(bytes);
    } else {
      for (int i = 0; i < n; i++) bytes.put((Byte) list.get(i));
    }
    return n;
  }
}
