package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ValueDefiningCode5 implements EnumValueSet {
  FEELING_OF_IMPENDING_DOOM(
      "Feeling of impending doom", "Feeling of impending doom.", "local", "at0111"),

  NOT_FEELING_WELL("Not feeling well", "Not feeling well.", "local", "at0110");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ValueDefiningCode5(String value, String description, String terminologyId, String code) {
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
