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

package com.ibm.streams.operator.internal.model.processors;

import com.ibm.streams.operator.metrics.Metric;
import com.ibm.streams.spl.messages.general.StreamsSPLJavaMessagesKey.Key;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({
  "com.ibm.streams.operator.model.CustomMetric",
})
public class CustomMetricVerifier extends AbstractModelProcessor {

  protected void validateElement(TypeElement annotation, Element e) {
    assert e.getKind() == ElementKind.METHOD;

    ExecutableElement method = (ExecutableElement) e;

    validatePublic(annotation, method);
    validateSetter(annotation, method);

    if (!isTypeClassType(method.getParameters().get(0).asType(), Metric.class))
      simpleError(annotation, method, Key.CMV_SETTER);
  }
}
