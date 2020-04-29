package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MaskenbedingtesErythemDefiningcode implements EnumValueSet {
  ABWESEND("Abwesend", "Es gibt keine Hinweise auf ein maskenbedingtes Erythem.", "local", "at0021"),

  VORHANDEN("Vorhanden", "Es gibt Hinweise auf maskenbedingte Erytheme.", "local", "at0020");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MaskenbedingtesErythemDefiningcode(String value, String description, String terminologyId,
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
