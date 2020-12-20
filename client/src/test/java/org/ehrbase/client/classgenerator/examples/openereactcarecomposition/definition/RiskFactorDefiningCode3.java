package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum RiskFactorDefiningCode3 implements EnumValueSet {
  OTHER_HOUSEHOLD_MEMBERS_ARE_ILL(
      "Other household members are ill",
      "The patient is in a house with other household members who are ill",
      "local",
      "at0.19");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  RiskFactorDefiningCode3(String value, String description, String terminologyId, String code) {
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
