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

package com.ibm.streams.instance.sam.model.logical.spl.v4000;

import com.ibm.streams.instance.sam.model.logical.spl.CompositeInputPort;
import com.ibm.streams.instance.sam.model.logical.spl.Connection;
import com.ibm.streams.instance.sam.model.logical.spl.PortKind;
import com.ibm.streams.platform.services.v4000.CompositeOperInstancePortType;
import com.ibm.streams.platform.services.v4000.OperInstancePortConnectionType;
import com.ibm.streams.platform.services.v4000.PortKindType;

public class V4000CompositeInputPort extends CompositeInputPort {

  public V4000CompositeInputPort(
      CompositeOperInstancePortType compositeOperInstancePortType,
      V4000CompositeOperator owningComposite) {
    super(compositeOperInstancePortType.getIndex(), owningComposite);

    if (compositeOperInstancePortType.getIncomingConnections() != null) {
      for (OperInstancePortConnectionType operInstancePortConnectionType :
          compositeOperInstancePortType.getIncomingConnections().getConnection()) {
        //                LogicalConnection mc = new
        // LogicalConnection(operInstancePortConnectionType);
        Connection mc =
            new Connection(
                operInstancePortConnectionType.getOperIndex(),
                operInstancePortConnectionType.getPortIndex(),
                operInstancePortConnectionType.getPortKind() == PortKindType.INPUT
                    ? PortKind.Input
                    : PortKind.Output);
        incomingConnections.add(mc);
      }
    }

    if (compositeOperInstancePortType.getOutgoingConnections() != null) {
      for (OperInstancePortConnectionType operInstancePortConnectionType :
          compositeOperInstancePortType.getOutgoingConnections().getConnection()) {
        //                LogicalConnection mc = new
        // LogicalConnection(operInstancePortConnectionType);
        Connection mc =
            new Connection(
                operInstancePortConnectionType.getOperIndex(),
                operInstancePortConnectionType.getPortIndex(),
                operInstancePortConnectionType.getPortKind() == PortKindType.INPUT
                    ? PortKind.Input
                    : PortKind.Output);
        outgoingConnections.add(mc);
      }
    }
  }
}
