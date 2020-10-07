package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum UntersuchterAnalytDefiningcode2 implements EnumValueSet {
  OXYGEN_PARTIAL_PRESSURE_IN_ARTERIAL_BLOOD("Oxygen [Partial pressure] in Arterial blood", null, "LOINC", "2703-7"),

  OXYGEN_PARTIAL_PRESSURE_IN_BLOOD("Oxygen [Partial pressure] in Blood", null, "LOINC", "11556-8"),

  OXYGEN_PARTIAL_PRESSURE_IN_CAPILLARY_BLOOD("Oxygen [Partial pressure] in Capillary blood", null, "LOINC", "2704-5");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  UntersuchterAnalytDefiningcode2(String value, String description, String terminologyId,
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
