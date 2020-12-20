package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum RiskAssessmentDefiningCode implements EnumValueSet {
  LOW_RISK(
      "Low risk",
      "The risk of the a patient having a Covid-19 infection is assessed to be low.",
      "local",
      "at0.16"),

  HIGH_RISK(
      "High risk",
      "The risk of the a patient having a Covid-19 infection is assessed to be high.",
      "local",
      "at0.17");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  RiskAssessmentDefiningCode(String value, String description, String terminologyId, String code) {
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
