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

package com.ibm.streams.operator.logging;

import java.util.logging.Level;

/**
 * Convenience class to provide SPL log levels through the {@code java.util.logging} facility. <br>
 * The SPL log levels in descending order are:
 *
 * <UL>
 *   <LI>{@link LogLevel#ERROR} (highest value)
 *   <LI>{@link LogLevel#WARN}
 *   <LI>{@link LogLevel#INFO} (lowest value)
 * </UL>
 *
 * <br>
 * When a message is logged to the SPL log facility its {@code java.util.logging.Level} is mapped to
 * the SPL log level as follows:
 *
 * <TABLE border="1" summary="This table describes the mapping of Java logging API log levels to SPL log levels">
 * <CAPTION><B><I>SPL log level mapping</I></B></CAPTION>
 * <TBODY>
 * <TR><TH scope="col">SPL Log Level</TH><TH scope="col">{@code java.util.logging.Level}</TH></TR>
 * <TR><TD></TD><TD></TD></TR>
 * <TR><TD rowspan="3">{@code error}</TD><TD>{@code Level.SEVERE}</TD></TR>
 * <TR><TD>{@link LogLevel#ERROR}</TD></TR>
 * <TR><TD>{@code >= 1000}</TD></TR>
 *
 * <TR><TD rowspan="3">{@code warn}</TD><TD>{@code Level.WARNING}</TD></TR>
 * <TR><TD>{@link LogLevel#WARN}</TD></TR>
 * <TR><TD>{@code >= 900 AND < 1000}</TD></TR>
 *
 * <TR><TD rowspan="4">{@code info}</TD><TD>{@code Level.INFO}</TD></TR>
 * <TR><TD>{@code Level.CONFIG}</TD></TR>
 * <TR><TD>{@link LogLevel#INFO}</TD></TR>
 * <TR><TD>{@code >= 700 AND < 900}</TD></TR>
 *
 * </TBODY>
 * </TABLE>
 *
 * @since InfoSphere&reg; Streams Version 3.0
 */
public final class LogLevel extends Level {

  /** */
  private static final long serialVersionUID = 5026015433483406189L;

  private LogLevel(String name, int level) {
    super(name, level);
  }

  /**
   * ERROR is a level indicating the operator cannot continue. All internal or unexpected failures
   * should be logged at this level.
   *
   * <p>This level is initialized to 1000 and is equal to {@code Level.SEVERE}.
   */
  public static final LogLevel ERROR = new LogLevel("ERROR", Level.SEVERE.intValue());

  /**
   * WARN is a level indicating a potential error or impending error. This level can also indicate a
   * progressive failure (for example, the potential leaking of resources).
   *
   * <p>This level is initialized to 900 and is equal to {@code Level.WARNING}.
   */
  public static final LogLevel WARN = new LogLevel("WARN", Level.WARNING.intValue());

  /**
   * INFO is level indicating general information outlining overall operator progress. Information
   * that helps to give context to a log, often when an operator is being initialized, handling
   * {@code allPortsReady()} or being shutdown.
   *
   * <p>This level is initialized to 700 and is equal to {@code Level.CONFIG}.
   */
  public static final LogLevel INFO = new LogLevel("INFO", Level.CONFIG.intValue());
}
