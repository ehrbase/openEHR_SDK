package org.ehrbase.client.classgenerator.examples.allergiescomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum CriticalityDefiningcode implements EnumValueSet {
  INDETERMINATE("Indeterminate", "Unable to assess with information available.", "local", "at0124"),

  HIGH("High", "Exposure to substance may result in critical organ system damage or life threatening consequence. Future exposure to the identified 'Substance' should be considered an absolute contra-indication in normal clinical circumstances.", "local", "at0103"),

  LOW("Low", "Exposure to substance unlikely to result in critical system organ damage or life threatening consequence. Future exposure to the identified 'Substance' should be considered a relative contra-indication in normal clinical circumstances.", "local", "at0102");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  CriticalityDefiningcode(String value, String description, String terminologyId, String code) {
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
