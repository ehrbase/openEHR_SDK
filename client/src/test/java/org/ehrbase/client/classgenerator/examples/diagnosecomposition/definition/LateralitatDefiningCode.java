package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LateralitatDefiningCode implements EnumValueSet {
  LINKS_UND_RECHTS("Links und Rechts", "Beide Körperseiten.", "local", "at0086"),

  LINKS("Links", "Die linke Körperseite.", "local", "at0003"),

  RECHTS("Rechts", "Die rechte Körperseite.", "local", "at0004");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LateralitatDefiningCode(String value, String description, String terminologyId, String code) {
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
