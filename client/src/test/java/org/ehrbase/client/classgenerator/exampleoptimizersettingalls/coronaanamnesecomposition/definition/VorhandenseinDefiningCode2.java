package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum VorhandenseinDefiningCode2 implements EnumValueSet {
  VORHANDEN("Vorhanden", "Der Risikofaktor wurde bei der Person identifiziert.", "local", "at0018"),

  UNBESTIMMT(
      "Unbestimmt",
      "Es ist nicht möglich festzustellen, ob der Risikofaktor vorhanden oder nicht vorhanden ist.",
      "local",
      "at0026"),

  NICHT_VORHANDEN(
      "Nicht vorhanden",
      "Der Risikofaktor wurde bei der Person nicht festgestellt.",
      "local",
      "at0019");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  VorhandenseinDefiningCode2(String value, String description, String terminologyId, String code) {
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
