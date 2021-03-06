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

/**
 * <copyright> </copyright>
 *
 * <p>$Id$
 */
package com.ibm.xmlns.prod.streams.spl.function.impl;

import com.ibm.xmlns.prod.streams.spl.function.FunctionPackage;
import com.ibm.xmlns.prod.streams.spl.function.PrototypeType;
import java.math.BigInteger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Prototype Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link com.ibm.xmlns.prod.streams.spl.function.impl.PrototypeTypeImpl#getValue
 *       <em>Value</em>}
 *   <li>{@link com.ibm.xmlns.prod.streams.spl.function.impl.PrototypeTypeImpl#getColumn
 *       <em>Column</em>}
 *   <li>{@link com.ibm.xmlns.prod.streams.spl.function.impl.PrototypeTypeImpl#getCppName <em>Cpp
 *       Name</em>}
 *   <li>{@link com.ibm.xmlns.prod.streams.spl.function.impl.PrototypeTypeImpl#getLine
 *       <em>Line</em>}
 * </ul>
 *
 * @generated
 */
public class PrototypeTypeImpl extends EObjectImpl implements PrototypeType {
  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final String VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected String value = VALUE_EDEFAULT;

  /**
   * The default value of the '{@link #getColumn() <em>Column</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getColumn()
   * @generated
   * @ordered
   */
  protected static final BigInteger COLUMN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getColumn() <em>Column</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getColumn()
   * @generated
   * @ordered
   */
  protected BigInteger column = COLUMN_EDEFAULT;

  /**
   * The default value of the '{@link #getCppName() <em>Cpp Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getCppName()
   * @generated
   * @ordered
   */
  protected static final String CPP_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCppName() <em>Cpp Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getCppName()
   * @generated
   * @ordered
   */
  protected String cppName = CPP_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getLine() <em>Line</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getLine()
   * @generated
   * @ordered
   */
  protected static final BigInteger LINE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLine() <em>Line</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getLine()
   * @generated
   * @ordered
   */
  protected BigInteger line = LINE_EDEFAULT;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected PrototypeTypeImpl() {
    super();
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return FunctionPackage.Literals.PROTOTYPE_TYPE;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public String getValue() {
    return value;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setValue(String newValue) {
    String oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this, Notification.SET, FunctionPackage.PROTOTYPE_TYPE__VALUE, oldValue, value));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public BigInteger getColumn() {
    return column;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setColumn(BigInteger newColumn) {
    BigInteger oldColumn = column;
    column = newColumn;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this, Notification.SET, FunctionPackage.PROTOTYPE_TYPE__COLUMN, oldColumn, column));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public String getCppName() {
    return cppName;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setCppName(String newCppName) {
    String oldCppName = cppName;
    cppName = newCppName;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              FunctionPackage.PROTOTYPE_TYPE__CPP_NAME,
              oldCppName,
              cppName));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public BigInteger getLine() {
    return line;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setLine(BigInteger newLine) {
    BigInteger oldLine = line;
    line = newLine;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this, Notification.SET, FunctionPackage.PROTOTYPE_TYPE__LINE, oldLine, line));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case FunctionPackage.PROTOTYPE_TYPE__VALUE:
        return getValue();
      case FunctionPackage.PROTOTYPE_TYPE__COLUMN:
        return getColumn();
      case FunctionPackage.PROTOTYPE_TYPE__CPP_NAME:
        return getCppName();
      case FunctionPackage.PROTOTYPE_TYPE__LINE:
        return getLine();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case FunctionPackage.PROTOTYPE_TYPE__VALUE:
        setValue((String) newValue);
        return;
      case FunctionPackage.PROTOTYPE_TYPE__COLUMN:
        setColumn((BigInteger) newValue);
        return;
      case FunctionPackage.PROTOTYPE_TYPE__CPP_NAME:
        setCppName((String) newValue);
        return;
      case FunctionPackage.PROTOTYPE_TYPE__LINE:
        setLine((BigInteger) newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case FunctionPackage.PROTOTYPE_TYPE__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
      case FunctionPackage.PROTOTYPE_TYPE__COLUMN:
        setColumn(COLUMN_EDEFAULT);
        return;
      case FunctionPackage.PROTOTYPE_TYPE__CPP_NAME:
        setCppName(CPP_NAME_EDEFAULT);
        return;
      case FunctionPackage.PROTOTYPE_TYPE__LINE:
        setLine(LINE_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case FunctionPackage.PROTOTYPE_TYPE__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      case FunctionPackage.PROTOTYPE_TYPE__COLUMN:
        return COLUMN_EDEFAULT == null ? column != null : !COLUMN_EDEFAULT.equals(column);
      case FunctionPackage.PROTOTYPE_TYPE__CPP_NAME:
        return CPP_NAME_EDEFAULT == null ? cppName != null : !CPP_NAME_EDEFAULT.equals(cppName);
      case FunctionPackage.PROTOTYPE_TYPE__LINE:
        return LINE_EDEFAULT == null ? line != null : !LINE_EDEFAULT.equals(line);
    }
    return super.eIsSet(featureID);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (value: ");
    result.append(value);
    result.append(", column: ");
    result.append(column);
    result.append(", cppName: ");
    result.append(cppName);
    result.append(", line: ");
    result.append(line);
    result.append(')');
    return result.toString();
  }
} // PrototypeTypeImpl
