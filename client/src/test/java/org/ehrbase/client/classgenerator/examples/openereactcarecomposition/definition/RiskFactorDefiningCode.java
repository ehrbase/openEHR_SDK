package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum RiskFactorDefiningCode implements EnumValueSet {
  POTENTIAL_CONTACT_EXPOSURE_BASED_ON_LOCATION(
      "Potential contact exposure based on location",
      "Potential contact exposure based on location.",
      "local",
      "at0.14");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  RiskFactorDefiningCode(String value, String description, String terminologyId, String code) {
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
