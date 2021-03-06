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
package com.ibm.xmlns.prod.streams.spl.source.code.impl;

import com.ibm.xmlns.prod.streams.spl.source.code.CodePackage;
import com.ibm.xmlns.prod.streams.spl.source.code.FunctionHeadType;
import com.ibm.xmlns.prod.streams.spl.source.code.FunctionModifiersType;
import com.ibm.xmlns.prod.streams.spl.source.code.FunctionParametersType;
import com.ibm.xmlns.prod.streams.spl.source.code.SplDocType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Head Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link com.ibm.xmlns.prod.streams.spl.source.code.impl.FunctionHeadTypeImpl#getSplDoc
 *       <em>Spl Doc</em>}
 *   <li>{@link com.ibm.xmlns.prod.streams.spl.source.code.impl.FunctionHeadTypeImpl#getModifiers
 *       <em>Modifiers</em>}
 *   <li>{@link com.ibm.xmlns.prod.streams.spl.source.code.impl.FunctionHeadTypeImpl#getParameters
 *       <em>Parameters</em>}
 *   <li>{@link com.ibm.xmlns.prod.streams.spl.source.code.impl.FunctionHeadTypeImpl#getName
 *       <em>Name</em>}
 *   <li>{@link com.ibm.xmlns.prod.streams.spl.source.code.impl.FunctionHeadTypeImpl#getReturnType
 *       <em>Return Type</em>}
 * </ul>
 *
 * @generated
 */
public class FunctionHeadTypeImpl extends SourceLocationTypeImpl implements FunctionHeadType {
  /**
   * The cached value of the '{@link #getSplDoc() <em>Spl Doc</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getSplDoc()
   * @generated
   * @ordered
   */
  protected SplDocType splDoc;

  /**
   * The cached value of the '{@link #getModifiers() <em>Modifiers</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getModifiers()
   * @generated
   * @ordered
   */
  protected FunctionModifiersType modifiers;

  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected FunctionParametersType parameters;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getReturnType()
   * @generated
   * @ordered
   */
  protected static final String RETURN_TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getReturnType()
   * @generated
   * @ordered
   */
  protected String returnType = RETURN_TYPE_EDEFAULT;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected FunctionHeadTypeImpl() {
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
    return CodePackage.Literals.FUNCTION_HEAD_TYPE;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public SplDocType getSplDoc() {
    return splDoc;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetSplDoc(SplDocType newSplDoc, NotificationChain msgs) {
    SplDocType oldSplDoc = splDoc;
    splDoc = newSplDoc;
    if (eNotificationRequired()) {
      ENotificationImpl notification =
          new ENotificationImpl(
              this,
              Notification.SET,
              CodePackage.FUNCTION_HEAD_TYPE__SPL_DOC,
              oldSplDoc,
              newSplDoc);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setSplDoc(SplDocType newSplDoc) {
    if (newSplDoc != splDoc) {
      NotificationChain msgs = null;
      if (splDoc != null)
        msgs =
            ((InternalEObject) splDoc)
                .eInverseRemove(
                    this,
                    EOPPOSITE_FEATURE_BASE - CodePackage.FUNCTION_HEAD_TYPE__SPL_DOC,
                    null,
                    msgs);
      if (newSplDoc != null)
        msgs =
            ((InternalEObject) newSplDoc)
                .eInverseAdd(
                    this,
                    EOPPOSITE_FEATURE_BASE - CodePackage.FUNCTION_HEAD_TYPE__SPL_DOC,
                    null,
                    msgs);
      msgs = basicSetSplDoc(newSplDoc, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              CodePackage.FUNCTION_HEAD_TYPE__SPL_DOC,
              newSplDoc,
              newSplDoc));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public FunctionModifiersType getModifiers() {
    return modifiers;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetModifiers(
      FunctionModifiersType newModifiers, NotificationChain msgs) {
    FunctionModifiersType oldModifiers = modifiers;
    modifiers = newModifiers;
    if (eNotificationRequired()) {
      ENotificationImpl notification =
          new ENotificationImpl(
              this,
              Notification.SET,
              CodePackage.FUNCTION_HEAD_TYPE__MODIFIERS,
              oldModifiers,
              newModifiers);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setModifiers(FunctionModifiersType newModifiers) {
    if (newModifiers != modifiers) {
      NotificationChain msgs = null;
      if (modifiers != null)
        msgs =
            ((InternalEObject) modifiers)
                .eInverseRemove(
                    this,
                    EOPPOSITE_FEATURE_BASE - CodePackage.FUNCTION_HEAD_TYPE__MODIFIERS,
                    null,
                    msgs);
      if (newModifiers != null)
        msgs =
            ((InternalEObject) newModifiers)
                .eInverseAdd(
                    this,
                    EOPPOSITE_FEATURE_BASE - CodePackage.FUNCTION_HEAD_TYPE__MODIFIERS,
                    null,
                    msgs);
      msgs = basicSetModifiers(newModifiers, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              CodePackage.FUNCTION_HEAD_TYPE__MODIFIERS,
              newModifiers,
              newModifiers));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public FunctionParametersType getParameters() {
    return parameters;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetParameters(
      FunctionParametersType newParameters, NotificationChain msgs) {
    FunctionParametersType oldParameters = parameters;
    parameters = newParameters;
    if (eNotificationRequired()) {
      ENotificationImpl notification =
          new ENotificationImpl(
              this,
              Notification.SET,
              CodePackage.FUNCTION_HEAD_TYPE__PARAMETERS,
              oldParameters,
              newParameters);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setParameters(FunctionParametersType newParameters) {
    if (newParameters != parameters) {
      NotificationChain msgs = null;
      if (parameters != null)
        msgs =
            ((InternalEObject) parameters)
                .eInverseRemove(
                    this,
                    EOPPOSITE_FEATURE_BASE - CodePackage.FUNCTION_HEAD_TYPE__PARAMETERS,
                    null,
                    msgs);
      if (newParameters != null)
        msgs =
            ((InternalEObject) newParameters)
                .eInverseAdd(
                    this,
                    EOPPOSITE_FEATURE_BASE - CodePackage.FUNCTION_HEAD_TYPE__PARAMETERS,
                    null,
                    msgs);
      msgs = basicSetParameters(newParameters, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              CodePackage.FUNCTION_HEAD_TYPE__PARAMETERS,
              newParameters,
              newParameters));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public String getName() {
    return name;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setName(String newName) {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this, Notification.SET, CodePackage.FUNCTION_HEAD_TYPE__NAME, oldName, name));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public String getReturnType() {
    return returnType;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setReturnType(String newReturnType) {
    String oldReturnType = returnType;
    returnType = newReturnType;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this,
              Notification.SET,
              CodePackage.FUNCTION_HEAD_TYPE__RETURN_TYPE,
              oldReturnType,
              returnType));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(
      InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CodePackage.FUNCTION_HEAD_TYPE__SPL_DOC:
        return basicSetSplDoc(null, msgs);
      case CodePackage.FUNCTION_HEAD_TYPE__MODIFIERS:
        return basicSetModifiers(null, msgs);
      case CodePackage.FUNCTION_HEAD_TYPE__PARAMETERS:
        return basicSetParameters(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case CodePackage.FUNCTION_HEAD_TYPE__SPL_DOC:
        return getSplDoc();
      case CodePackage.FUNCTION_HEAD_TYPE__MODIFIERS:
        return getModifiers();
      case CodePackage.FUNCTION_HEAD_TYPE__PARAMETERS:
        return getParameters();
      case CodePackage.FUNCTION_HEAD_TYPE__NAME:
        return getName();
      case CodePackage.FUNCTION_HEAD_TYPE__RETURN_TYPE:
        return getReturnType();
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
      case CodePackage.FUNCTION_HEAD_TYPE__SPL_DOC:
        setSplDoc((SplDocType) newValue);
        return;
      case CodePackage.FUNCTION_HEAD_TYPE__MODIFIERS:
        setModifiers((FunctionModifiersType) newValue);
        return;
      case CodePackage.FUNCTION_HEAD_TYPE__PARAMETERS:
        setParameters((FunctionParametersType) newValue);
        return;
      case CodePackage.FUNCTION_HEAD_TYPE__NAME:
        setName((String) newValue);
        return;
      case CodePackage.FUNCTION_HEAD_TYPE__RETURN_TYPE:
        setReturnType((String) newValue);
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
      case CodePackage.FUNCTION_HEAD_TYPE__SPL_DOC:
        setSplDoc((SplDocType) null);
        return;
      case CodePackage.FUNCTION_HEAD_TYPE__MODIFIERS:
        setModifiers((FunctionModifiersType) null);
        return;
      case CodePackage.FUNCTION_HEAD_TYPE__PARAMETERS:
        setParameters((FunctionParametersType) null);
        return;
      case CodePackage.FUNCTION_HEAD_TYPE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CodePackage.FUNCTION_HEAD_TYPE__RETURN_TYPE:
        setReturnType(RETURN_TYPE_EDEFAULT);
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
      case CodePackage.FUNCTION_HEAD_TYPE__SPL_DOC:
        return splDoc != null;
      case CodePackage.FUNCTION_HEAD_TYPE__MODIFIERS:
        return modifiers != null;
      case CodePackage.FUNCTION_HEAD_TYPE__PARAMETERS:
        return parameters != null;
      case CodePackage.FUNCTION_HEAD_TYPE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CodePackage.FUNCTION_HEAD_TYPE__RETURN_TYPE:
        return RETURN_TYPE_EDEFAULT == null
            ? returnType != null
            : !RETURN_TYPE_EDEFAULT.equals(returnType);
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
    result.append(" (name: ");
    result.append(name);
    result.append(", returnType: ");
    result.append(returnType);
    result.append(')');
    return result.toString();
  }
} // FunctionHeadTypeImpl
