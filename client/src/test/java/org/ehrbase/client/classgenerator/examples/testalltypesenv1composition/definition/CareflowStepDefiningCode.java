package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum CareflowStepDefiningCode implements EnumValueSet {
  PLANNED("Planned", "*", "openehr", "at0003"),

  ACTIVE("Active", "*", "openehr", "at0004"),

  COMPLETED("Completed", "*", "openehr", "at0005");

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
