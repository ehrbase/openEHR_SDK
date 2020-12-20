package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum VorhandenDefiningCode implements EnumValueSet {
  NICHT_VORHANDEN(
      "Nicht vorhanden",
      "Das spezifische Symptom oder Anzeichen ist nicht vorhanden.",
      "local",
      "at0024"),

  VORHANDEN(
      "Vorhanden", "Das spezifische Symptom oder Anzeichen ist vorhanden.", "local", "at0023");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  VorhandenDefiningCode(String value, String description, String terminologyId, String code) {
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
