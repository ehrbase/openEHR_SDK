package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AnforderungDefiningCode implements EnumValueSet {
  SARS_COV2_COVID19_AB_PANEL_SERUM_OR_PLASMA_BY_IMMUNOASSAY(
      "SARS-CoV-2 (COVID-19) Ab panel - Serum or Plasma by Immunoassay", "", "LOINC", "94504-8");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AnforderungDefiningCode(String value, String description, String terminologyId, String code) {
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
