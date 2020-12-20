package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ValueDefiningCode4 implements EnumValueSet {
  ABDOMINAL_DISTENSION_NAUSEA_BLEEDING(
      "Abdominal distension/nausea/bleeding",
      "Abdominal distension/nausea/bleeding.",
      "local",
      "at0109"),

  NO_PROGRESS("No progress", "No progress.", "local", "at0108");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ValueDefiningCode4(String value, String description, String terminologyId, String code) {
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
