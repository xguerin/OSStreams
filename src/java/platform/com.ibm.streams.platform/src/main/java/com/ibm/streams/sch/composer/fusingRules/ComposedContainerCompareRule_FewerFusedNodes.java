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

package com.ibm.streams.sch.composer.fusingRules;

import com.ibm.streams.sch.composer.ComposedContainer;

public class ComposedContainerCompareRule_FewerFusedNodes implements ComposedContainerCompareRule {

  @Override
  public int compare(ComposedContainer o1, ComposedContainer o2) {
    //		Bwf.p("o1["+o1.numOfFusableUnits()+"] o2["+o2.numOfFusableUnits()+"]");
    return (o1.getNumOfFusedNodes() - o2.getNumOfFusedNodes());
  }

  public String toString() {
    return "<CCCR: Fewer num of fused Nodes>";
  }
}
