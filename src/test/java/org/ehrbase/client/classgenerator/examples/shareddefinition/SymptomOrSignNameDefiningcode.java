package org.ehrbase.client.classgenerator.examples.shareddefinition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SymptomOrSignNameDefiningcode implements EnumValueSet {
  CODE386661006("386661006", "386661006", "SNOMED_-CT", "386661006"),

  CODE315642008("315642008", "315642008", "SNOMED_-CT", "315642008"),

  CODE49727002("49727002", "49727002", "SNOMED_-CT", "49727002"),

  CODE267036007("267036007", "267036007", "SNOMED_-CT", "267036007"),

  CODE162397003("162397003", "162397003", "SNOMED_-CT", "162397003");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SymptomOrSignNameDefiningcode(String value, String description, String terminologyId,
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
