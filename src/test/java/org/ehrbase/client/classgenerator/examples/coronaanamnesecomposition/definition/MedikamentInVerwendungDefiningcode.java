package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MedikamentInVerwendungDefiningcode implements EnumValueSet {
  UNBEKANNT("Unbekannt", "Es ist unbekannt, ob die Person das Medikament zum Zeitpunkt des Ergebnis oder währendessen verwendet oder es verwendet hat.", "local", "at0033"),

  INVERWENDUNG("In Verwendung", "Die Person verwendet das Medikament zum Zeitpunkt des Ergebnis oder währendessen oder hat es verwendet.", "local", "at0031"),

  NICHTINVERWENDUNG("Nicht in Verwendung", "Die Person verwendet das Medikament zum Zeitpunkt des Ergebnis oder währendessen nicht oder hat es nicht verwendet.", "local", "at0032");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MedikamentInVerwendungDefiningcode(String value, String description, String terminologyId,
      String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public String getValue() {
     return this.value ;
  }

  public String getDescription() {
     return this.description ;
  }

  public String getTerminologyId() {
     return this.terminologyId ;
  }

  public String getCode() {
     return this.code ;
  }
}
