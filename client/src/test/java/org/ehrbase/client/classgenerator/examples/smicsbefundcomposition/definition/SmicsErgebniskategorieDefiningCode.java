package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SmicsErgebniskategorieDefiningCode implements EnumValueSet {
  ANDERE("Andere", "*", "local", "at0028"),

  HAUFUNG("Häufung", "*", "local", "at0026"),

  AUSBRUCH("Ausbruch", "*", "local", "at0027");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SmicsErgebniskategorieDefiningCode(
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
