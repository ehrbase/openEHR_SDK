package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MechanismusDefiningcode implements EnumValueSet {
  MIN("Druck < 30 l/min", "Druckgerät < 15 l/min.", "local", "at0.110");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MechanismusDefiningcode(String value, String description, String terminologyId, String code) {
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
