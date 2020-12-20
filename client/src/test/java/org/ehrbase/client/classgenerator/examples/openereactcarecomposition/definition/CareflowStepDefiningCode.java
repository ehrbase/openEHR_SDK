package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum CareflowStepDefiningCode implements EnumValueSet {
  SERVICE_PLANNED(
      "Service planned", "Service request to healthcare provider is planned.", "openehr", "at0002");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  CareflowStepDefiningCode(String value, String description, String terminologyId, String code) {
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
