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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Java class for binaryType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="binaryType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ibm.com/xmlns/prod/streams/spl/operator/instance}expnBase">
 *       &lt;sequence>
 *         &lt;element name="lhs" type="{http://www.ibm.com/xmlns/prod/streams/spl/operator/instance}expressionTreeType"/>
 *         &lt;element name="rhs" type="{http://www.ibm.com/xmlns/prod/streams/spl/operator/instance}expressionTreeType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="op" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "binaryType",
    propOrder = {"lhs", "rhs"})
@XmlSeeAlso({ConditionalType.class})
public class BinaryType extends ExpnBase {

  @XmlElement(required = true)
  protected ExpressionTreeType lhs;

  @XmlElement(required = true)
  protected ExpressionTreeType rhs;

  @XmlAttribute(name = "op", required = true)
  protected String op;

  /**
   * Gets the value of the lhs property.
   *
   * @return possible object is {@link ExpressionTreeType }
   */
  public ExpressionTreeType getLhs() {
    return lhs;
  }

  /**
   * Sets the value of the lhs property.
   *
   * @param value allowed object is {@link ExpressionTreeType }
   */
  public void setLhs(ExpressionTreeType value) {
    this.lhs = value;
  }

  /**
   * Gets the value of the rhs property.
   *
   * @return possible object is {@link ExpressionTreeType }
   */
  public ExpressionTreeType getRhs() {
    return rhs;
  }

  /**
   * Sets the value of the rhs property.
   *
   * @param value allowed object is {@link ExpressionTreeType }
   */
  public void setRhs(ExpressionTreeType value) {
    this.rhs = value;
  }

  /**
   * Gets the value of the op property.
   *
   * @return possible object is {@link String }
   */
  public String getOp() {
    return op;
  }

  /**
   * Sets the value of the op property.
   *
   * @param value allowed object is {@link String }
   */
  public void setOp(String value) {
    this.op = value;
  }
}
