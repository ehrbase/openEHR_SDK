package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ArtDerBeatmungDefiningcode implements EnumValueSet {
  INVASIV("Invasiv", "Beatmung unter Verwendung eines invasiven Beatmungszugangs", "local", "at0145"),

  NICHTINVASIV("Nicht-invasiv", "Beatmung ohne Verwendung eines invasiven Beatmungszugangs", "local", "at0146");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArtDerBeatmungDefiningcode(String value, String description, String terminologyId, String code) {
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
