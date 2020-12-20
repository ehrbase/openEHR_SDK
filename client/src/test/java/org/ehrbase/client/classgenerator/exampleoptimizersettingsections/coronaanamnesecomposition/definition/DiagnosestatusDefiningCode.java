package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum DiagnosestatusDefiningCode implements EnumValueSet {
  IN_BEARBEITUNG(
      "In Bearbeitung",
      "Eine vorübergehende Diagnose, bei welcher Testergebnisse und klinische Hinweise oder Empfehlungen noch ausstehen, welche aber bereits auf einer soliden klinischen Sicherheit beruht. Sobald weitere Testresultate und klinische Hinweise oder Empfehlungen zur Verfügung stehen, kann sich die Diagnose noch ändern.",
      "local",
      "at0017");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  DiagnosestatusDefiningCode(String value, String description, String terminologyId, String code) {
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
