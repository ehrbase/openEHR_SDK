package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ValueDefiningCode2 implements EnumValueSet {
  LETHARGIC("Lethargic", "Lethargic.", "local", "at0101"),

  CONFUSED("Confused", "Confused.", "local", "at0102");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ValueDefiningCode2(String value, String description, String terminologyId, String code) {
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
