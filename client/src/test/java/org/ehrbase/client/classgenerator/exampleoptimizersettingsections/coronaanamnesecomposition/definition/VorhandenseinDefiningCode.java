package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum VorhandenseinDefiningCode implements EnumValueSet {
  NICHT_VORHANDEN(
      "Nicht vorhanden",
      "*The specific exposure is or has been absent at or during the event.(en)",
      "local",
      "at0049"),

  VORHANDEN(
      "Vorhanden",
      "*The specific exposure is or has been present at or during the event.(en)",
      "local",
      "at0047");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  VorhandenseinDefiningCode(String value, String description, String terminologyId, String code) {
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
