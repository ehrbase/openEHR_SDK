package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AmberFlagAcuteDefiningCode implements EnumValueSet {
  ACUTE_DETERIORATION_IN_FUNCTIONAL_ABILITY(
      "Acute deterioration in functional ability",
      "Acute deterioration in functional ability",
      "local",
      "at0091"),

  HEART_RATE91130_OR_NEW_DYSRHYTHMIA(
      "Heart rate 91-130 or new dysrhythmia",
      "Heart rate 91-130 or new dysrhythmia",
      "local",
      "at0095"),

  CLINICAL_SIGNS_OF_WOUND_INFECTION(
      "Clinical signs of wound infection", "Clinical signs of wound infection", "local", "at0097"),

  SYSTOLIC_BP91100_MMHG("Systolic BP 91-100 mmHg", "Systolic BP 91-100 mmHg", "local", "at0094"),

  TEMPERATURE36_C("Temperature <36°C", "Temperature <36°C", "local", "at0096"),

  RELATIVES_CONCERNED_ABOUT_MENTAL_STATUS_REQUIRED(
      "Relatives concerned about mental status REQUIRED:",
      "Relatives concerned about mental status REQUIRED:",
      "local",
      "at0090"),

  RESPIRATORY_RATE2124("Respiratory rate 21-24", "Respiratory rate 21-24", "local", "at0093"),

  TRAUMA_SURGERY_PROCEDURE_IN_LAST8_WEEKS(
      "Trauma / surgery / procedure in last 8 weeks",
      "Trauma / surgery / procedure in last 8 weeks",
      "local",
      "at0092");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AmberFlagAcuteDefiningCode(String value, String description, String terminologyId, String code) {
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
