package org.ehrbase.client.classgenerator.examples.shareddefinition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum HealthRiskDefiningcode implements EnumValueSet {
  CODE840546002("840546002", "840546002", "SNOMED-CT", "840546002");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  HealthRiskDefiningcode(String value, String description, String terminologyId, String code) {
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
