package org.ehrbase.client.classgenerator.examples.shareddefinition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum DiagnosisNameDefiningcode implements EnumValueSet {
  CODE840546002("840546002", "840546002", "SNOMED-CT", "840546002"),

  CODE840539006("840539006", "840539006", "SNOMED-CT", "840539006"),

  CODE840544004("840544004", "840544004", "SNOMED-CT", "840544004");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  DiagnosisNameDefiningcode(String value, String description, String terminologyId, String code) {
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
