package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum RiskFactorDefiningCode2 implements EnumValueSet {
  CONTACT_WITH_CONFIRMED_COVID19_CASE(
      "Contact with confirmed Covid-19 case",
      "Contact with confirmed Covid-19 case within 14 days before symptom onset.",
      "local",
      "at0.9");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  RiskFactorDefiningCode2(String value, String description, String terminologyId, String code) {
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
