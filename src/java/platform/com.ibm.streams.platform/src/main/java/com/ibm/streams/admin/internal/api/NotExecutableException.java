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

package com.ibm.streams.admin.internal.api;

import com.ibm.streams.messages.MessageKey;

/** Signals the user does not have execute access to a directory. */
public class NotExecutableException extends StreamsException {

  /**
   * Construct an exception from msgKey and substitution variables
   *
   * @param key
   * @param subst
   */
  public NotExecutableException(MessageKey key, Object... subst) {
    super(key, subst);
  }
}
