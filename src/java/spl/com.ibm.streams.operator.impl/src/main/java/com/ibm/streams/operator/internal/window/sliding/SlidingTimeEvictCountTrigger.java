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

package com.ibm.streams.operator.internal.window.sliding;

import com.ibm.streams.operator.internal.window.WindowHandler;

/**
 * Sliding window with count based eviction and trigger.
 *
 * @param <T> Tuple type.
 */
final class SlidingTimeEvictCountTrigger<T, P> extends SlidingTimeEvict<T, P> {

  private static final long serialVersionUID = 8847436675035358610L;
  private final CountTrigger trigger;

  SlidingTimeEvictCountTrigger(WindowHandler<T> windowHandler, P partition) {
    super(windowHandler, partition);
    trigger = this.new CountTrigger();
  }

  /** Trigger an event after any eviction and insertion. */
  @Override
  protected void insert(T tuple) throws Exception {

    super.insert(tuple);

    trigger.checkTrigger();
  }
}
