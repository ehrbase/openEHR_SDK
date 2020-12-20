package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ValueDefiningCode3 implements EnumValueSet {
  NEW_PAIN("New pain", "New pain.", "local", "at0106"),

  INCREASING_OR_CONSISTING_PAIN(
      "Increasing or consisting pain", "Increasing or consisting pain.", "local", "at0107");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ValueDefiningCode3(String value, String description, String terminologyId, String code) {
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
