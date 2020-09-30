package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SymptomSignNameDefiningcode2 implements EnumValueSet {
  N29857009("29857009", "29857009", "SNOMED-CT", "29857009"),

  N267036007("267036007", "267036007", "SNOMED-CT", "267036007");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SymptomSignNameDefiningcode2(String value, String description, String terminologyId,
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
