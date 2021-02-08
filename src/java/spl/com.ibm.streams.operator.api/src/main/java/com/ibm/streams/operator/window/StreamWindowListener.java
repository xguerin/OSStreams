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

package com.ibm.streams.operator.window;

import java.util.EventListener;

/**
 * Handle StreamWindowEvents being generated by a StreamWindow.
 *
 * <p>If an implementation of this class also implements {@link
 * com.ibm.streams.operator.state.StateHandler} then when it is {@link
 * StreamWindow#registerListener(StreamWindowListener, boolean) registered as a window listener} it
 * is also {@link
 * com.ibm.streams.operator.OperatorContext#registerStateHandler(com.ibm.streams.operator.state.StateHandler)
 * registered as an operator's state handler}
 *
 * <p>{@link StatefulWindowListener} provides a abstract implementation of this interface that
 * correctly maintains window state including support for partitioned windows and per-partition
 * state, and persistence of that state. Operators may extend {@code StatefulWindowListener} to
 * implement their specific event handling without having to worry about per-partition state
 * managment.
 *
 * <p>Correct ordering of calls to<code>handleEvent()</code> is guaranteed by {@link StreamWindow}.
 * Calls to <code>
 * handleEvent()</code> are synchronous but may be invoked by different threads. Each previous
 * <code>handleEvent()
 * </code> <i>happens-before</i> any <code>handleEvent()</code> call to guarantee changes made in
 * any previous <code>
 * handleEvent()</code> are visible to the current call.
 *
 * @param <T> Type of the tuple being handled.
 */
public interface StreamWindowListener<T> extends EventListener {

  /**
   * Handle a event from a StreamWindow.
   *
   * @param event Windowing event.
   * @throws Exception Exception processing an event.
   */
  public void handleEvent(StreamWindowEvent<T> event) throws Exception;
}
