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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ibm.streams.instance.sam.exception.SAMInternalErrorException;
import java.lang.reflect.Type;

public class PrimitiveOutputPortSerializer extends PrimitivePortSerializer
    implements JsonSerializer<PrimitiveOutputPort> {

  public PrimitiveOutputPortSerializer(SPLLogicalModel model) {
    super(model);
  }

  @Override
  public JsonElement serialize(
      PrimitiveOutputPort port, Type type, JsonSerializationContext context) {
    JsonElement jsonElement = super.serialize(port, type, context, true);
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    try {
      jsonObject.add("streamName", context.serialize(port.getStreamName()));
      if (!port.getStreamName().equals(port.getLogicalStreamName())) {
        jsonObject.add("logicalStreamName", context.serialize(port.getLogicalStreamName()));
      }
    } catch (SAMInternalErrorException e) {
      // Should never happen in practice
      e.printStackTrace();
    }
    return jsonElement;
  }
}
