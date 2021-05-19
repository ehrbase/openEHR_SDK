package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LabortestBezeichnungDefiningCode implements EnumValueSet {
  SEROLOGIC_TEST_PROCEDURE("Serologic test (procedure)", "", "SNOMED CT", "68793005");

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
