package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ValueDefiningCode6 implements EnumValueSet {
  DOESN_T_LOOK_GOOD("Doesn't look good", "Doesn't look good.", "local", "at0113"),

  CHANGE_IN_BEHAVIOUR("Change in behaviour", "Change in behaviour.", "local", "at0112");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ValueDefiningCode6(String value, String description, String terminologyId, String code) {
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
