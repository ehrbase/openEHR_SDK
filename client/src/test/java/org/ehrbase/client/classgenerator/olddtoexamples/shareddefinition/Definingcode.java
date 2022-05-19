package org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum Definingcode implements EnumValueSet {
  N840544004("840544004", "840544004", "SNOMED-CT", "840544004"),

  N840539006("840539006", "840539006", "SNOMED-CT", "840539006"),

  N840546002("840546002", "840546002", "SNOMED-CT", "840546002");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  Definingcode(String value, String description, String terminologyId, String code) {
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
