package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum UntersuchterAnalytDefiningcode implements EnumValueSet {
  PH_OF_SERUM_OR_PLASMA("pH of Serum or Plasma", null, "LOINC", "2753-2"),

  PH_OF_CAPILLARY_BLOOD("pH of Capillary blood", null, "LOINC", "2745-8"),

  PH_OF_BLOOD("pH of Blood", null, "LOINC", "11558-4"),

  PH_OF_VENOUS_BLOOD("pH of Venous blood", null, "LOINC", "2746-6"),

  PH_OF_MIXED_VENOUS_BLOOD("pH of Mixed venous blood", null, "LOINC", "19213-8"),

  PH_OF_ARTERIAL_BLOOD("pH of Arterial blood", null, "LOINC", "2744-1");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  UntersuchterAnalytDefiningcode(String value, String description, String terminologyId,
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
