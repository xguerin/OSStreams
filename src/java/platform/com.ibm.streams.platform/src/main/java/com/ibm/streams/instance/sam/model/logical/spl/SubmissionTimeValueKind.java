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

package com.ibm.streams.instance.sam.model.logical.spl;

public enum SubmissionTimeValueKind {
  Named,
  NamedList;

  public static SubmissionTimeValueKind getEnum(String s) {
    if (s.compareTo("named") == 0) {
      return SubmissionTimeValueKind.Named;
    } else {
      assert (s.compareTo("namedList") == 0);
      return SubmissionTimeValueKind.NamedList;
    }
  }
}
