package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ActiveDefiningcode2 implements EnumValueSet {
  ACTIVE("Active", "*", "local", "at0004");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ActiveDefiningcode2(String value, String description, String terminologyId, String code) {
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
