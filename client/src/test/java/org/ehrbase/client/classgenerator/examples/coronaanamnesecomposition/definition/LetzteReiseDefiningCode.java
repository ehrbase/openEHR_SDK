package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LetzteReiseDefiningCode implements EnumValueSet {
  NEIN(
      "Nein",
      "Die Person ist nicht innerhalb des angegebenen Zeitraums gereist.",
      "local",
      "at0005"),

  UNBEKANNT(
      "Unbekannt",
      "Es ist nicht bekannt, ob die Person innerhalb des angegebenen Zeitinraumss gereist ist.",
      "local",
      "at0027"),

  JA_NATIONAL(
      "Ja - national",
      "Die Person ist innerhalb des angegebenen Zeitraums national gereist.",
      "local",
      "at0006");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LetzteReiseDefiningCode(String value, String description, String terminologyId, String code) {
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
