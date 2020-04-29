package org.ehrbase.diagnose.diagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LateralitatDefiningcode implements EnumValueSet {
  RECHTS("Rechts", "Die rechte Körperseite.", "local", "at0004"),

  LINKS("Links", "Die linke Körperseite.", "local", "at0003"),

  LINKSUNDRECHTS("Links und Rechts", "Beide Körperseiten.", "local", "at0086");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LateralitatDefiningcode(String value, String description, String terminologyId, String code) {
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
