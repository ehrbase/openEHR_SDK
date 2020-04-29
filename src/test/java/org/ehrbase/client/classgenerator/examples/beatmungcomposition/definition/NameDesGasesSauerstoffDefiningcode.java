package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum NameDesGasesSauerstoffDefiningcode implements EnumValueSet {
  SAUERSTOFF("Sauerstoff", "Das gelieferte Gas ist Sauerstoff.", "local", "at0.96");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  NameDesGasesSauerstoffDefiningcode(String value, String description, String terminologyId,
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
