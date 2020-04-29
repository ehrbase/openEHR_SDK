package org.ehrbase.diagnose.shareddefinition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum DerDiagnoseDefiningcode implements EnumValueSet {
  U072("U07.2", "U07.2", "ICD-10-GM-2020", "U07.2"),

  B342("B34.2", "B34.2", "ICD-10-GM-2020", "B34.2"),

  U071("U07.1", "U07.1", "ICD-10-GM-2020", "U07.1"),

  B972("B97.2", "B97.2", "ICD-10-GM-2020", "B97.2");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  DerDiagnoseDefiningcode(String value, String description, String terminologyId, String code) {
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
