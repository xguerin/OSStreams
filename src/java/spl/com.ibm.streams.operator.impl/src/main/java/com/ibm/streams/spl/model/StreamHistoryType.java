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
import javax.xml.bind.annotation.XmlType;

/**
 * Java class for streamHistoryType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="streamHistoryType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ibm.com/xmlns/prod/streams/spl/operator/instance}expnBase">
 *       &lt;sequence>
 *         &lt;element name="stream" type="{http://www.ibm.com/xmlns/prod/streams/spl/operator/instance}streamType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="depth" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "streamHistoryType",
    propOrder = {"stream"})
public class StreamHistoryType extends ExpnBase {

  @XmlElement(required = true)
  protected StreamType stream;

  @XmlAttribute(name = "depth", required = true)
  protected long depth;

  /**
   * Gets the value of the stream property.
   *
   * @return possible object is {@link StreamType }
   */
  public StreamType getStream() {
    return stream;
  }

  /**
   * Sets the value of the stream property.
   *
   * @param value allowed object is {@link StreamType }
   */
  public void setStream(StreamType value) {
    this.stream = value;
  }

  /** Gets the value of the depth property. */
  public long getDepth() {
    return depth;
  }

  /** Sets the value of the depth property. */
  public void setDepth(long value) {
    this.depth = value;
  }
}
