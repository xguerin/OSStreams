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

package com.ibm.streams.operator.internal.jni;

import com.ibm.streams.operator.internal.metrics.ReadOnlyMetric;

final class JNICustomMetric extends ReadOnlyMetric {

  private final JNICustomMetricLinkage jniMetric;

  JNICustomMetric(JNIBridge bridge, long metricPtr, String name, Kind kind, String description) {
    super(name, kind, description);
    this.jniMetric = new JNICustomMetricLinkage(bridge, metricPtr);
  }

  @Override
  public long getValue() {
    return jniMetric.getValue();
  }
}
