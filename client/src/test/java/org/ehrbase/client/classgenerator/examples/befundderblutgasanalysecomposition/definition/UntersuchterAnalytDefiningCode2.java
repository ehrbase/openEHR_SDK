package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum UntersuchterAnalytDefiningCode2 implements EnumValueSet {
  OXYGEN_PARTIAL_PRESSURE_IN_BLOOD(
      "Oxygen [Partial pressure] in Blood",
      "Oxygen [Partial pressure] in Blood",
      "LOINC",
      "11556-8"),

  OXYGEN_PARTIAL_PRESSURE_IN_ARTERIAL_BLOOD(
      "Oxygen [Partial pressure] in Arterial blood",
      "Oxygen [Partial pressure] in Arterial blood",
      "LOINC",
      "2703-7"),

  OXYGEN_PARTIAL_PRESSURE_IN_CAPILLARY_BLOOD(
      "Oxygen [Partial pressure] in Capillary blood",
      "Oxygen [Partial pressure] in Capillary blood",
      "LOINC",
      "2704-5");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  UntersuchterAnalytDefiningCode2(
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
