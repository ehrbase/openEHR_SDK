package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ArtDesAtemgasbefeuchtersDefiningcode implements EnumValueSet {
  GESCHLOSSENESSYSTEM("Geschlossenes System", "Geschlossenes System - Einweg-Atemgasbefeuchter (Wasser kann nicht hinzugefügt werden, ohne den Befeuchterbehälter zu ersetzen).", "local", "at0055"),

  OFFENESSYSTEM("Offenes System", "Offenes System - wiederverwendbarer Atemgasbefeuchter (Wasser kann manuell hinzugefügt werden).", "local", "at0054");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArtDesAtemgasbefeuchtersDefiningcode(String value, String description, String terminologyId,
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
