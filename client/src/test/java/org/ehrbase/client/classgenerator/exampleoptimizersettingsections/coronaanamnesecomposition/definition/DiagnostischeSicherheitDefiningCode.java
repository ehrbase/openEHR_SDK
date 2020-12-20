package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum DiagnostischeSicherheitDefiningCode implements EnumValueSet {
  WAHRSCHEINLICH(
      "Wahrscheinlich",
      "Die Diagnose wurde mit einem hohen Maß an Sicherheit gestellt.",
      "local",
      "at0075"),

  VERMUTET(
      "Vermutet",
      "Die Diagnose wurde mit einem niedrigen Grad an Sicherheit gestellt.",
      "local",
      "at0074"),

  BESTAETIGT(
      "Bestätigt", "Die Diagnose wurde anhand anerkannter Kriterien bestätigt.", "local", "at0076");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  DiagnostischeSicherheitDefiningCode(
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
