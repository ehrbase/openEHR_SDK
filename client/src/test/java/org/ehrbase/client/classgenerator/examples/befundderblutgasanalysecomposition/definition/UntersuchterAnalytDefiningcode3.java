package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum UntersuchterAnalytDefiningcode3 implements EnumValueSet {
  CARBON_DIOXIDE_PARTIAL_PRESSURE_IN_CAPILLARY_BLOOD("Carbon dioxide [Partial pressure] in Capillary blood", null, "LOINC", "2020-6"),

  CARBON_DIOXIDE_PARTIAL_PRESSURE_IN_ARTERIAL_BLOOD("Carbon dioxide [Partial pressure] in Arterial blood", null, "LOINC", "2019-8"),

  CARBON_DIOXIDE_PARTIAL_PRESSURE_IN_BLOOD("Carbon dioxide [Partial pressure] in Blood", null, "LOINC", "11557-6");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  UntersuchterAnalytDefiningcode3(String value, String description, String terminologyId,
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
