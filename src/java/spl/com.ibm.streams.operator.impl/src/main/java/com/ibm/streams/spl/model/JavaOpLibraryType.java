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
 * Java class for javaOpLibraryType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="javaOpLibraryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.ibm.com/xmlns/prod/streams/spl/common}descriptionType"/>
 *         &lt;element name="managedLibrary" type="{http://www.ibm.com/xmlns/prod/streams/spl/common}javaOpManagedLibraryType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "javaOpLibraryType",
    namespace = "http://www.ibm.com/xmlns/prod/streams/spl/common",
    propOrder = {"description", "managedLibrary"})
public class JavaOpLibraryType {

  @XmlElement(required = true)
  protected DescriptionType description;

  @XmlElement(required = true)
  protected JavaOpManagedLibraryType managedLibrary;

  /**
   * Gets the value of the description property.
   *
   * @return possible object is {@link DescriptionType }
   */
  public DescriptionType getDescription() {
    return description;
  }

  /**
   * Sets the value of the description property.
   *
   * @param value allowed object is {@link DescriptionType }
   */
  public void setDescription(DescriptionType value) {
    this.description = value;
  }

  /**
   * Gets the value of the managedLibrary property.
   *
   * @return possible object is {@link JavaOpManagedLibraryType }
   */
  public JavaOpManagedLibraryType getManagedLibrary() {
    return managedLibrary;
  }

  /**
   * Sets the value of the managedLibrary property.
   *
   * @param value allowed object is {@link JavaOpManagedLibraryType }
   */
  public void setManagedLibrary(JavaOpManagedLibraryType value) {
    this.managedLibrary = value;
  }
}
