package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LabortestBezeichnungDefiningcode implements EnumValueSet {
  GAS_PANEL_BLOOD("Gas panel - Blood", null, "LOINC", "24338-6"),

  GAS_PANEL_CAPILLARY_BLOOD("Gas panel - Capillary blood", null, "LOINC", "24337-8"),

  GAS_PANEL_ARTERIAL_BLOOD("Gas panel - Arterial blood", null, "LOINC", "24336-0");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LabortestBezeichnungDefiningcode(String value, String description, String terminologyId,
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
