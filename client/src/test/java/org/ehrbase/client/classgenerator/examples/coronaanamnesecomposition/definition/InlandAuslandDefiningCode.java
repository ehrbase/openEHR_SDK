package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum InlandAuslandDefiningCode implements EnumValueSet {
  INLANDSREISE(
      "Inlandsreise",
      "Die Person ist innerhalb des angegebenen Zeitraums im Inland gereist.",
      "local",
      "at0028"),

  INLANDS_UND_AUSLANDSREISE(
      "Inlands- und Auslandsreise",
      "Die Person ist innerhalb des festgelegten Zeitraums sowohl im Inland als auch international gereist.",
      "local",
      "at0030"),

  AUSLANDREISE(
      "Auslandreise",
      "Die Person ist innerhalb des angegebenen Zeitraums international gereist.",
      "local",
      "at0029");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  InlandAuslandDefiningCode(String value, String description, String terminologyId, String code) {
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
