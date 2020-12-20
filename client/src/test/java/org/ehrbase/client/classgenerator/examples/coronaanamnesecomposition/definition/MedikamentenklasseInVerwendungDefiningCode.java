package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MedikamentenklasseInVerwendungDefiningCode implements EnumValueSet {
  IN_VERWENDUNG(
      "In Verwendung",
      "Die Person verwendet die Medikamentenklasse zum Zeitpunkt des Ergebnis oder währendessen oder hat sie verwendet.",
      "local",
      "at0028"),

  UNBEKANNT(
      "Unbekannt",
      "Es ist unbekannt, ob die Person die Medikamentenklasse zum Zeitpunkt des Ergebnis oder währendessen verwendet oder sie verwendet hat.",
      "local",
      "at0030"),

  NICHT_IN_VERWENDUNG(
      "Nicht in Verwendung",
      "Die Person verwendet die Medikamentenklasse zum Zeitpunkt des Ergebnis oder währendessen nicht oder hat sie nicht verwendet.",
      "local",
      "at0029");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MedikamentenklasseInVerwendungDefiningCode(
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
