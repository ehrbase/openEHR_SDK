package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum UntersuchterAnalytDefiningCode4 implements EnumValueSet {
  OXYGEN_SATURATION_IN_BLOOD(
      "Oxygen saturation in Blood", "Oxygen saturation in Blood", "LOINC", "20564-1"),

  OXYGEN_SATURATION_IN_ARTERIAL_BLOOD(
      "Oxygen saturation in Arterial blood",
      "Oxygen saturation in Arterial blood",
      "LOINC",
      "2708-6");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  UntersuchterAnalytDefiningCode4(
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
