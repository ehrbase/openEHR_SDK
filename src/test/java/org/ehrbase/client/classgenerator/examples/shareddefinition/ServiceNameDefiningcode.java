package org.ehrbase.client.classgenerator.examples.shareddefinition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ServiceNameDefiningcode implements EnumValueSet {
  CODE170499009("170499009", "170499009", "SNOMED-CT", "170499009"),

  CODE225368008("225368008", "225368008", "SNOMED-CT", "225368008");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ServiceNameDefiningcode(String value, String description, String terminologyId, String code) {
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
