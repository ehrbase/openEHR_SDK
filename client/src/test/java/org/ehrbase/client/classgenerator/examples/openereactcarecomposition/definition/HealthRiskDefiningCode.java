package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum HealthRiskDefiningCode implements EnumValueSet {
  COVID19_RISK_ASSESSMENT(
      "COVID-19 Risk assessment", "Assessment of risk of COVID-19 infection.", "local", "at0.1");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  HealthRiskDefiningCode(String value, String description, String terminologyId, String code) {
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
