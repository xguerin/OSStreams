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
// Generated on: 2017.05.17 at 12:18:58 PM EDT
//

package com.ibm.streams.spl.model.tupletype;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the com.ibm.streams.spl.model.tupletype package.
 *
 * <p>An ObjectFactory allows you to programatically construct new instances of the Java
 * representation for XML content. The Java representation of XML content can consist of schema
 * derived interfaces and classes representing the binding of schema type definitions, element
 * declarations and model groups. Factory methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  private static final QName _Tuple_QNAME =
      new QName("http://www.ibm.com/xmlns/prod/streams/spl/tupleType", "tuple");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: com.ibm.streams.spl.model.tupletype
   */
  public ObjectFactory() {}

  /** Create an instance of {@link TupleType } */
  public TupleType createTupleType() {
    return new TupleType();
  }

  /** Create an instance of {@link TypeType } */
  public TypeType createTypeType() {
    return new TypeType();
  }

  /** Create an instance of {@link OptionalType } */
  public OptionalType createOptionalType() {
    return new OptionalType();
  }

  /** Create an instance of {@link MapType } */
  public MapType createMapType() {
    return new MapType();
  }

  /** Create an instance of {@link AttrType } */
  public AttrType createAttrType() {
    return new AttrType();
  }

  /** Create an instance of {@link ListOrSetType } */
  public ListOrSetType createListOrSetType() {
    return new ListOrSetType();
  }

  /** Create an instance of {@link JAXBElement }{@code <}{@link TupleType }{@code >}} */
  @XmlElementDecl(namespace = "http://www.ibm.com/xmlns/prod/streams/spl/tupleType", name = "tuple")
  public JAXBElement<TupleType> createTuple(TupleType value) {
    return new JAXBElement<TupleType>(_Tuple_QNAME, TupleType.class, null, value);
  }
}
