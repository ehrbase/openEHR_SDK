package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum RedFlagAcuteDefiningCode implements EnumValueSet {
  RESPIRATORY_RATE25_PER_MINUTE(
      "Respiratory rate ≥ 25 per minute", "Respiratory rate ≥ 25 per minute", "local", "at0077"),

  SYSTOLIC_BP90_MMHG_OR_DROP_OF40_FROM_NORMAL(
      "Systolic BP ≤ 90 mmHg (or drop of >40 from normal)",
      "Systolic BP ≤ 90 mmHg (or drop of >40 from normal)",
      "local",
      "at0075"),

  NEEDS_O2_TO_KEEP_SPO29288_IN_COPD(
      "Needs O2 to keep SpO2 ≥ 92% (88% in COPD)",
      "Needs O2 to keep SpO2 ≥ 92% (88% in COPD)", "local", "at0078"),

  RECENT_CHEMOTHERAPY("Recent chemotherapy", "Recent chemotherapy", "local", "at0081"),

  NOT_PASSED_URINE_IN18_HOURS05ML_KG_HR_IF_CATHETERISED(
      "Not passed urine in 18 hours (<0.5ml/kg/hr if catheterised)",
      "Not passed urine in 18 hours (<0.5ml/kg/hr if catheterised)",
      "local",
      "at0082"),

  HEART_RATE130_PER_MINUTE(
      "Heart rate ≥ 130 per minute", "Heart rate ≥ 130 per minute", "local", "at0076"),

  LACTATE2_MMOL_L("Lactate ≥ 2 mmol/l", "Lactate ≥ 2 mmol/l", "local", "at0080"),

  OBJECTIVE_EVIDENCE_OF_NEW_OR_ALTERED_MENTAL_STATE(
      "Objective evidence of new or altered mental state",
      "Objective evidence of new or altered mental state",
      "local",
      "at0074"),

  NON_BLANCHING_RASH_MOTTLED_ASHEN_CYANOTIC(
      "Non-blanching rash / mottled / ashen / cyanotic",
      "Non-blanching rash / mottled / ashen / cyanotic",
      "local",
      "at0079");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  RedFlagAcuteDefiningCode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public String getValue() {
    return this.value;
  }

  public String getDescription() {
    return this.description;
  }

  public String getTerminologyId() {
    return this.terminologyId;
  }

  public String getCode() {
    return this.code;
  }
}
