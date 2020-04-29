package org.ehrbase.kopertemperature.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KorperexpositionDefiningcode implements EnumValueSet {
  BETTZEUG("Verminderte Kleidung/Bettzeug", "Die Person wird bedeckt von einer geringeren Menge an Kleidung oder Bettzeug als für die Umgebungsbedingungen angemessen erscheint.", "local", "at0032"),

  NACKT("Nackt", "Keine Kleidung, Bettzeug oder andere Bedeckung.", "local", "at0031");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KorperexpositionDefiningcode(String value, String description, String terminologyId,
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
