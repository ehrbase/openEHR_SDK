package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AgitationIndicatorDefiningCode implements EnumValueSet {
  RESTLESS("Restless", "Restless.", "local", "at0103"),

  ANXIOUS("Anxious", "Anxious.", "local", "at0104");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AgitationIndicatorDefiningCode(String value, String description, String terminologyId,
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
