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
// Generated on: 2017.05.17 at 12:18:59 PM EDT
//

package com.ibm.streams.spl.model.annotation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;

/**
 * Java class for listOrSetType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="listOrSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="value" type="{http://www.ibm.com/xmlns/prod/streams/spl/annotation}valueType"/>
 *         &lt;element name="list" type="{http://www.ibm.com/xmlns/prod/streams/spl/annotation}listOrSetType"/>
 *         &lt;element name="set" type="{http://www.ibm.com/xmlns/prod/streams/spl/annotation}listOrSetType"/>
 *         &lt;element name="map" type="{http://www.ibm.com/xmlns/prod/streams/spl/annotation}mapType"/>
 *         &lt;element name="tuple" type="{http://www.ibm.com/xmlns/prod/streams/spl/annotation}tupleType"/>
 *       &lt;/choice>
 *       &lt;attribute name="elementType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "listOrSetType",
    propOrder = {"valueOrListOrSet"})
public class ListOrSetType {

  @XmlElementRefs({
    @XmlElementRef(
        name = "tuple",
        namespace = "http://www.ibm.com/xmlns/prod/streams/spl/annotation",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "list",
        namespace = "http://www.ibm.com/xmlns/prod/streams/spl/annotation",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "value",
        namespace = "http://www.ibm.com/xmlns/prod/streams/spl/annotation",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "map",
        namespace = "http://www.ibm.com/xmlns/prod/streams/spl/annotation",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "set",
        namespace = "http://www.ibm.com/xmlns/prod/streams/spl/annotation",
        type = JAXBElement.class,
        required = false)
  })
  protected List<JAXBElement<?>> valueOrListOrSet;

  @XmlAttribute(name = "elementType", required = true)
  protected String elementType;

  /**
   * Gets the value of the valueOrListOrSet property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the valueOrListOrSet property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getValueOrListOrSet().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link JAXBElement }{@code <}{@link
   * TupleType }{@code >} {@link JAXBElement }{@code <}{@link ListOrSetType }{@code >} {@link
   * JAXBElement }{@code <}{@link MapType }{@code >} {@link JAXBElement }{@code <}{@link
   * ListOrSetType }{@code >} {@link JAXBElement }{@code <}{@link ValueType }{@code >}
   */
  public List<JAXBElement<?>> getValueOrListOrSet() {
    if (valueOrListOrSet == null) {
      valueOrListOrSet = new ArrayList<JAXBElement<?>>();
    }
    return this.valueOrListOrSet;
  }

  /**
   * Gets the value of the elementType property.
   *
   * @return possible object is {@link String }
   */
  public String getElementType() {
    return elementType;
  }

  /**
   * Sets the value of the elementType property.
   *
   * @param value allowed object is {@link String }
   */
  public void setElementType(String value) {
    this.elementType = value;
  }
}
