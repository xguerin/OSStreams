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

package com.ibm.streams.control.internal.server.consistent;

import static com.ibm.streams.control.internal.server.consistent.ConsistentRegionSetup.MAIN_COMPOSITE;

import com.api.json.JSONArray;
import com.api.json.JSONObject;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConsistentRegion {

  private static class OperatorInstance {
    private final String instanceName;
    private final BigInteger peIndex;
    private final long operatorIndex;
    private final boolean isOperatorTrigger;
    private final boolean isStartRegion;
    private final boolean isEndRegion;
    private final boolean hasControlPort;

    // Indicates if the operator has completed a cut or a restore
    private boolean cutOrRestoreComplete = false;

    private OperatorInstance(
        String instanceName,
        BigInteger peIndex,
        long operatorIndex,
        boolean isOperatorTrigger,
        boolean isStartRegion,
        boolean isEndRegion,
        boolean hasControlPort) {
      this.instanceName = instanceName;
      this.peIndex = peIndex;
      this.operatorIndex = operatorIndex;
      this.isOperatorTrigger = isOperatorTrigger;
      this.isStartRegion = isStartRegion;
      this.isEndRegion = isEndRegion;
      this.hasControlPort = hasControlPort;
    }

    public boolean mustReportCompletion() {
      return (isEndRegion || hasControlPort);
    }

    public void setCutRestoreComplete(boolean value) {
      cutOrRestoreComplete = value;
    }

    public boolean getCutOrRestoreComplete() {
      return cutOrRestoreComplete;
    }

    public boolean isOperatorTrigger() {
      return isOperatorTrigger;
    }

    public String getInstanceName() {
      return instanceName;
    }

    public boolean isStartOfRegion() {
      return isStartRegion;
    }

    public boolean isEndOfRegion() {
      return isEndRegion;
    }
  }

  public enum Trigger {
    /** Region drain is started periodically. */
    PERIODIC,

    /** Region drain is started by the start operator. */
    OPERATOR_DRIVEN,
  }

  // Constants for annotations generated by the compiler and transformer - main composite
  private static final String CC_REGION = "consistentRegion";
  private static final String CC_TRIGGER = "trigger";
  private static final String CC_PERIODIC = "periodic";
  private static final String CC_OPERATOR_DRIVEN = "operatorDriven";
  private static final String CC_PERIOD = "period";
  private static final String CC_DRAIN_TIMEOUT = "drainTimeout";
  private static final String CC_RESET_TIMEOUT = "resetTimeout";
  private static final String CC_MAX_RESET_ATTEMPTS = "maxConsecutiveResetAttempts";

  // Constants for logical and physical views of an application
  private static final String INPUT_PORTS = "inputPorts";
  private static final String ANNOTATION_LIST = "list";
  private static final String IS_CONTROL_PORT = "isControl";
  private static final String DEFINITION_INDEX = "definitionIndex";

  // Operators that do not exist in the physical model and should be ignored when
  // building the consistent region
  private static final String SPLITTER_OPERATOR = "spl.utility::Splitter";
  private static final String MERGER_OPERATOR = "spl.utility::Merger";

  /*
   * Region information derived from ObjectName, and static for the life of the bean.
   */
  private transient int index;

  /*
   *  Region information derived from PADL, and static for the life of the bean.
   */
  private transient int logicalIndex = -1;
  private transient Trigger trigger;
  private transient double period = 0.0;
  private transient double drainTimeout = 0.0;
  private transient double resetTimeout = 0.0;
  private transient long maxConsecutiveResetAttempts = 1;

  private final Map<String, OperatorInstance> operators = new HashMap<String, OperatorInstance>();
  // Set of PEs in the region, derived from the PADL
  private final transient Set<BigInteger> pesInRegion = new HashSet<BigInteger>();
  // Number of start operators
  private transient int numStartOperators = 0;

  private BigInteger jobId;

  public ConsistentRegion(BigInteger jobId) {
    super();
    this.jobId = jobId;
  }

  public ConsistentRegion() {}

  public void constructRegion(
      int index,
      Map<String, BigInteger> primOperatorToPeIdMap,
      Map<Integer, String> operDefIndexToOperKindMap,
      String logicalViewS)
      throws Exception {
    this.index = index;
    JSONObject logicalView = JSONObject.parse(logicalViewS);
    JSONObject mainComposite = JSONObject.parse(logicalView.get(MAIN_COMPOSITE).toString());
    parseCompositeOperator(mainComposite, primOperatorToPeIdMap, operDefIndexToOperKindMap);
    configureRegion(mainComposite);
  }

  /**
   * Parse the consistentRegionEntry annotation for a primitive operator. Creating primitive
   * operator object according to the annotation configurations.
   *
   * @param annotation annotation JSON Object
   * @param primitiveOp primitive operator JSON Object
   */
  private void parseConsistentRegionEntry(
      JSONObject annotation, JSONObject primitiveOp, Map<String, BigInteger> primOperatorToPeIdMap)
      throws Exception {
    int annotLogicalIndex = -1;
    boolean isOperatorTrigger = false;
    boolean isStartRegion = false;
    boolean isEndRegion = false;

    JSONArray inputPorts = null;
    int numInputPorts = 0;
    if (primitiveOp.get(INPUT_PORTS) != null) {
      inputPorts = JSONArray.parse(primitiveOp.get(INPUT_PORTS).toString());
      numInputPorts = inputPorts.size();
    }

    List<Boolean> autonomousIPorts =
        new ArrayList<Boolean>(Collections.nCopies(numInputPorts, false));

    JSONObject crEntry =
        JSONObject.parse(annotation.get(ConsistentRegionSetup.CC_KEY_VALUES).toString());

    Integer annotIndex = Integer.parseInt(crEntry.get(ConsistentRegionSetup.CC_INDEX).toString());
    if (annotIndex != index) {
      return;
    }

    annotLogicalIndex =
        Integer.parseInt(crEntry.get(ConsistentRegionSetup.CC_LOGICAL_INDEX).toString());

    if (crEntry.get(ConsistentRegionSetup.CC_IS_TRIGGER_OPERATOR) != null) {
      isOperatorTrigger =
          Boolean.parseBoolean(
              crEntry.get(ConsistentRegionSetup.CC_IS_TRIGGER_OPERATOR).toString());
    }

    if (crEntry.get(ConsistentRegionSetup.CC_IS_START_REGION) != null) {
      isStartRegion =
          Boolean.parseBoolean(crEntry.get(ConsistentRegionSetup.CC_IS_START_REGION).toString());
      numStartOperators++;
    }

    if (crEntry.get(ConsistentRegionSetup.CC_IS_END_REGION) != null) {
      isEndRegion =
          Boolean.parseBoolean(crEntry.get(ConsistentRegionSetup.CC_IS_END_REGION).toString());
    }

    Object objAutoIPorts = crEntry.get(ConsistentRegionSetup.CC_AUTONOMOUS_INPUT_PORT);
    if (objAutoIPorts != null) {
      JSONObject aIPorts = JSONObject.parse(objAutoIPorts.toString());
      JSONArray ports = JSONArray.parse(aIPorts.get(ANNOTATION_LIST).toString());
      for (Object port : ports) {
        int portIndex = Integer.parseInt(port.toString());
        autonomousIPorts.set(portIndex, Boolean.TRUE);
      }
    }

    assert (annotLogicalIndex >= 0);

    if (logicalIndex == -1) {
      logicalIndex = annotLogicalIndex;
    } else {
      assert (annotLogicalIndex == logicalIndex);
    }

    /// CANDIDATE FOR DELETION
    boolean hasConsistentControlPort =
        operatorHasConsistentControlPort(primitiveOp, isStartRegion, autonomousIPorts);

    String opName = primitiveOp.get(ConsistentRegionSetup.OPERATOR_NAME).toString();
    if (operators.containsKey(opName)) {
      throw new AssertionError("Operator map already contains " + opName);
    }

    long opIndex = Long.parseLong(primitiveOp.get(ConsistentRegionSetup.OPERATOR_INDEX).toString());
    BigInteger peId = primOperatorToPeIdMap.get(opName);
    if (peId == null) {
      throw new AssertionError("PE for operator " + opName + " not found");
    }

    operators.put(
        opName,
        new OperatorInstance(
            opName,
            peId,
            opIndex,
            isOperatorTrigger,
            isStartRegion,
            isEndRegion,
            hasConsistentControlPort));
    pesInRegion.add(peId);
  }

  /**
   * Parse primitive operator, creating primitive a operator object if it belongs to the the
   * consistent region.
   *
   * @param primitiveOp JSON object of the primitive operator to be parsed
   */
  private void parsePrimitiveOperator(
      JSONObject primitiveOp,
      Map<String, BigInteger> primOperatorToPeIdMap,
      Map<Integer, String> operDefIndexToOperKindMap)
      throws Exception {
    if (primitiveOp.get(ConsistentRegionSetup.ANNOTATIONS) == null) {
      return;
    }

    Integer defIndex = Integer.valueOf(primitiveOp.get(DEFINITION_INDEX).toString());
    String opKind = operDefIndexToOperKindMap.get(defIndex);
    if (opKind == null) {
      throw new AssertionError("Operator kind not found for index " + defIndex);
    }

    if (opKind.equals(SPLITTER_OPERATOR) || opKind.equals(MERGER_OPERATOR)) {
      return;
    }

    JSONArray annotations =
        JSONArray.parse(primitiveOp.get(ConsistentRegionSetup.ANNOTATIONS).toString());

    for (Object annot : annotations) {
      JSONObject annotation = JSONObject.parse(annot.toString());
      String tag = annotation.get(ConsistentRegionSetup.CC_TAG).toString();
      if (tag.compareTo(ConsistentRegionSetup.CC_REGION_ENTRY) == 0) {
        //            if (annotation.get(ConsistentRegionSetup.CC_REGION_ENTRY) != null) {
        parseConsistentRegionEntry(annotation, primitiveOp, primOperatorToPeIdMap);
      }
    }
  }

  /**
   * Parse composite operator, creating primitive operators objects for the consistent region.
   *
   * @param compositeOp JSON object of the composite operator to be parsed
   */
  private void parseCompositeOperator(
      JSONObject compositeOp,
      Map<String, BigInteger> primOperatorToPeIdMap,
      Map<Integer, String> operDefIndexToOperKindMap)
      throws Exception {

    if (compositeOp.get(ConsistentRegionSetup.PRIMITIVE_OPERATORS) != null) {
      JSONArray allPrimitiveOps =
          JSONArray.parse(compositeOp.get(ConsistentRegionSetup.PRIMITIVE_OPERATORS).toString());
      for (Object primOp : allPrimitiveOps) {
        JSONObject primitiveOp = JSONObject.parse(primOp.toString());
        parsePrimitiveOperator(primitiveOp, primOperatorToPeIdMap, operDefIndexToOperKindMap);
      }
    }
    if (compositeOp.get(ConsistentRegionSetup.COMPOSITE_OPERATORS) != null) {
      JSONArray allCompositeOps =
          JSONArray.parse(compositeOp.get(ConsistentRegionSetup.COMPOSITE_OPERATORS).toString());
      for (Object composite : allCompositeOps) {
        JSONObject innerCompositeOp = JSONObject.parse(composite.toString());
        parseCompositeOperator(innerCompositeOp, primOperatorToPeIdMap, operDefIndexToOperKindMap);
      }
    }
  }

  /**
   * Checks if the operator has any consistent control input port. The Drain and Reset process only
   * terminate after such operators notify that they have processed the markers.
   *
   * @param primitiveOp JSON representation of a primitive operator
   * @param isStartRegion if the operator is the start of a consistent region
   * @param autonomousIPorts list of input ports that are autonomous
   * @return true if the operator has any consistent control input port, false otherwise.
   */
  private boolean operatorHasConsistentControlPort(
      JSONObject primitiveOp, boolean isStartRegion, List<Boolean> autonomousIPorts)
      throws Exception {
    // All input ports of an operator that start the region are oblivious to Cut and Restore markers
    if (isStartRegion) {
      return false;
    }
    JSONArray inputPorts = JSONArray.parse(primitiveOp.get(INPUT_PORTS).toString());
    assert (autonomousIPorts.size() == inputPorts.size());

    int i = 0;
    for (Object iport : inputPorts) {
      JSONObject inputPort = JSONObject.parse(iport.toString());
      boolean isControl = false;
      if (inputPort.get(IS_CONTROL_PORT) != null) {
        isControl = Boolean.parseBoolean(inputPort.get(IS_CONTROL_PORT).toString());
      }
      if (isControl && !autonomousIPorts.get(i)) {
        return true;
      }
      i++;
    }
    return false;
  }

  /**
   * Parses composite section of the application to set the appropriate configuration the current
   * ConsistentRegion.
   */
  private void configureRegion(JSONObject logicalView) throws Exception {
    JSONArray annotations =
        JSONArray.parse(logicalView.get(ConsistentRegionSetup.ANNOTATIONS).toString());

    for (Object annot : annotations) {
      JSONObject annotation = JSONObject.parse(annot.toString());
      String tag = annotation.get(ConsistentRegionSetup.CC_TAG).toString();
      if (tag.compareTo(CC_REGION) != 0) {
        continue;
      }
      JSONObject cRegion =
          JSONObject.parse(annotation.get(ConsistentRegionSetup.CC_KEY_VALUES).toString());

      // Ensure this configuration corresponds to the correct region
      // Index in the composite is the logical index, as the composite
      // section is not expanded. Here we parse the configuration only
      // if the logicalIndex (computed while parsing the physical operator
      // instance section) matches the composite index.
      Integer annotIndex = Integer.parseInt(cRegion.get(ConsistentRegionSetup.CC_INDEX).toString());
      if (annotIndex != logicalIndex) {
        continue;
      }

      Trigger annotTrigger = Trigger.PERIODIC;
      String triggerVal = cRegion.get(CC_TRIGGER).toString();
      if (triggerVal.equals(CC_OPERATOR_DRIVEN)) {
        annotTrigger = Trigger.OPERATOR_DRIVEN;
      }

      double annotPeriod = 0.0;
      if (annotTrigger == Trigger.PERIODIC) {
        annotPeriod = Double.parseDouble(cRegion.get(CC_PERIOD).toString());
      }

      double annotDrainTimeout = Double.parseDouble(cRegion.get(CC_DRAIN_TIMEOUT).toString());

      double annotResetTimeout = Double.parseDouble(cRegion.get(CC_RESET_TIMEOUT).toString());

      long annotMaxConsecutiveResetAttempts =
          Long.parseLong(cRegion.get(CC_MAX_RESET_ATTEMPTS).toString());

      assert (annotIndex != -1
          && annotDrainTimeout != 0.0
          && annotResetTimeout != 0.0
          && annotMaxConsecutiveResetAttempts > 0);

      trigger = annotTrigger;
      period = annotPeriod;
      drainTimeout = annotDrainTimeout;
      resetTimeout = annotResetTimeout;
      maxConsecutiveResetAttempts = annotMaxConsecutiveResetAttempts;
    }
  }

  public BigInteger getJobId() {
    return jobId;
  }

  public int getIndex() {
    return index;
  }

  public int getLogicalIndex() {
    return logicalIndex;
  }

  public Trigger getTrigger() {
    return trigger;
  }

  public double getPeriod() {
    return period;
  }

  public double getDrainTimeout() {
    return drainTimeout;
  }

  public double getResetTimeout() {
    return resetTimeout;
  }

  public long getMaxConsecutiveResetAttempts() {
    return maxConsecutiveResetAttempts;
  }

  public int getNumStartOperators() {
    return numStartOperators;
  }

  public String getTriggerString() {
    if (trigger == Trigger.PERIODIC) {
      return "periodic";
    } else {
      return "operator driven";
    }
  }

  public Set<BigInteger> getPesInRegion() {
    return pesInRegion;
  }

  public Map<String, Boolean> getOperatorsToStartRegionMap() {
    Map<String, Boolean> operatorsToStartRegionMap = new HashMap<String, Boolean>();
    for (Map.Entry<String, OperatorInstance> entry : operators.entrySet()) {
      operatorsToStartRegionMap.put(entry.getKey(), entry.getValue().isStartOfRegion());
    }
    return operatorsToStartRegionMap;
  }

  public Map<String, Boolean> getOperatorsToTriggerMap() {
    Map<String, Boolean> operatorsToTriggerMap = new HashMap<String, Boolean>();
    for (Map.Entry<String, OperatorInstance> entry : operators.entrySet()) {
      operatorsToTriggerMap.put(entry.getKey(), entry.getValue().isOperatorTrigger());
    }
    return operatorsToTriggerMap;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("Region index: " + index + " logicalIndex: " + logicalIndex + "\n");
    str.append("\tConfiguration:" + "\n");
    str.append(
        "\t\tPeriod: "
            + period
            + " DrainTimeout: "
            + drainTimeout
            + " ResetTimeout: "
            + resetTimeout
            + " MaxRestoreAttempt: "
            + maxConsecutiveResetAttempts
            + "\n");
    str.append("\tOperators: " + "\n");
    for (OperatorInstance opInst : operators.values()) {
      str.append(
          "\t\tOperator: "
              + opInst.instanceName
              + " Start: "
              + opInst.isStartRegion
              + " End: "
              + opInst.isEndRegion
              + " Control: "
              + opInst.hasControlPort
              + " PE ID: "
              + opInst.peIndex
              + " Op index: "
              + opInst.operatorIndex
              + "\n");
    }

    return str.toString();
  }
}
