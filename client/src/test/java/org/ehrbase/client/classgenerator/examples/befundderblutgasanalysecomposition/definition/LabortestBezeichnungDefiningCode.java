package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LabortestBezeichnungDefiningCode implements EnumValueSet {
  GAS_PANEL_CAPILLARY_BLOOD(
      "Gas panel - Capillary blood", "Gas panel - Capillary blood", "LOINC", "24337-8"),

  GAS_PANEL_ARTERIAL_BLOOD(
      "Gas panel - Arterial blood", "Gas panel - Arterial blood", "LOINC", "24336-0"),

  GAS_PANEL_BLOOD("Gas panel - Blood", "Gas panel - Blood", "LOINC", "24338-6");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LabortestBezeichnungDefiningCode(
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
