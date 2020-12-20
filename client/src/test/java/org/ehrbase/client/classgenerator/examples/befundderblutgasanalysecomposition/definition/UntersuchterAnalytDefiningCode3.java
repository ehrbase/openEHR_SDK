package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum UntersuchterAnalytDefiningCode3 implements EnumValueSet {
  PH_OF_VENOUS_BLOOD("pH of Venous blood", "pH of Venous blood", "LOINC", "2746-6"),

  PH_OF_CAPILLARY_BLOOD("pH of Capillary blood", "pH of Capillary blood", "LOINC", "2745-8"),

  PH_OF_ARTERIAL_BLOOD("pH of Arterial blood", "pH of Arterial blood", "LOINC", "2744-1"),

  PH_OF_MIXED_VENOUS_BLOOD(
      "pH of Mixed venous blood", "pH of Mixed venous blood", "LOINC", "19213-8"),

  PH_OF_SERUM_OR_PLASMA("pH of Serum or Plasma", "pH of Serum or Plasma", "LOINC", "2753-2"),

  PH_OF_BLOOD("pH of Blood", "pH of Blood", "LOINC", "11558-4");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  UntersuchterAnalytDefiningCode3(
      String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public String getValue() {
    return this.value;
  }

  public String getDescription() {
    return this.description;
  }

  public String getTerminologyId() {
    return this.terminologyId;
  }

  public String getCode() {
    return this.code;
  }
}
