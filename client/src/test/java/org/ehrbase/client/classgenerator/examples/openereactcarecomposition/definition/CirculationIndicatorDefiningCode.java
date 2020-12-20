package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum CirculationIndicatorDefiningCode implements EnumValueSet {
  COLDNESS("Coldness", "Coldness.", "local", "at0098"),

  IMPAIRED_PERFUSION("Impaired perfusion", "Impaired perfusion.", "local", "at0099"),

  SWEATY_CLAMMY("Sweaty/clammy", "Sweaty/clammy.", "local", "at0097"),

  COLOUR_CHANGES_PALE_GREY(
      "Colour changes: pale, grey", "Colour changes: pale, grey.", "local", "at0096");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  CirculationIndicatorDefiningCode(
      String value, String description, String terminologyId, String code) {
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
