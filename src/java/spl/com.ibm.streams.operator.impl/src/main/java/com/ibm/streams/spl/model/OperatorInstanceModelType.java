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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation,
// v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2017.05.17 at 12:19:00 PM EDT
//

package com.ibm.streams.spl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Java class for operatorInstanceModelType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="operatorInstanceModelType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="context" type="{http://www.ibm.com/xmlns/prod/streams/spl/operator/instance}contextType"/>
 *         &lt;element name="parameters" type="{http://www.ibm.com/xmlns/prod/streams/spl/operator/instance}parametersType"/>
 *         &lt;element name="inputPorts" type="{http://www.ibm.com/xmlns/prod/streams/spl/operator/instance}inputPortsType"/>
 *         &lt;element name="outputPorts" type="{http://www.ibm.com/xmlns/prod/streams/spl/operator/instance}outputPortsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "operatorInstanceModelType",
    propOrder = {"context", "parameters", "inputPorts", "outputPorts"})
public class OperatorInstanceModelType {

  @XmlElement(required = true)
  protected ContextType context;

  @XmlElement(required = true)
  protected ParametersType parameters;

  @XmlElement(required = true)
  protected InputPortsType inputPorts;

  @XmlElement(required = true)
  protected OutputPortsType outputPorts;

  /**
   * Gets the value of the context property.
   *
   * @return possible object is {@link ContextType }
   */
  public ContextType getContext() {
    return context;
  }

  /**
   * Sets the value of the context property.
   *
   * @param value allowed object is {@link ContextType }
   */
  public void setContext(ContextType value) {
    this.context = value;
  }

  /**
   * Gets the value of the parameters property.
   *
   * @return possible object is {@link ParametersType }
   */
  public ParametersType getParameters() {
    return parameters;
  }

  /**
   * Sets the value of the parameters property.
   *
   * @param value allowed object is {@link ParametersType }
   */
  public void setParameters(ParametersType value) {
    this.parameters = value;
  }

  /**
   * Gets the value of the inputPorts property.
   *
   * @return possible object is {@link InputPortsType }
   */
  public InputPortsType getInputPorts() {
    return inputPorts;
  }

  /**
   * Sets the value of the inputPorts property.
   *
   * @param value allowed object is {@link InputPortsType }
   */
  public void setInputPorts(InputPortsType value) {
    this.inputPorts = value;
  }

  /**
   * Gets the value of the outputPorts property.
   *
   * @return possible object is {@link OutputPortsType }
   */
  public OutputPortsType getOutputPorts() {
    return outputPorts;
  }

  /**
   * Sets the value of the outputPorts property.
   *
   * @param value allowed object is {@link OutputPortsType }
   */
  public void setOutputPorts(OutputPortsType value) {
    this.outputPorts = value;
  }
}
