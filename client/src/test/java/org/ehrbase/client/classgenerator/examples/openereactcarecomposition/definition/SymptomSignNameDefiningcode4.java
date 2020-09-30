package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SymptomSignNameDefiningcode4 implements EnumValueSet {
  N74474003("74474003", "74474003", "SNOMED-CT", "74474003"),

  N16932000("16932000", "16932000", "SNOMED-CT", "16932000"),

  N62315008("62315008", "62315008", "SNOMED-CT", "62315008"),

  N21522001("21522001", "21522001", "SNOMED-CT", "21522001");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SymptomSignNameDefiningcode4(String value, String description, String terminologyId,
                               String code) {
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
