package org.ehrbase.client.classgenerator.examples.schwangerschaftsstatuscomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StatusDefiningCode2 implements EnumValueSet {
  SCHWANGER("Schwanger", "Die Person ist schwanger.", "local", "at0012"),

  NICHT_SCHWANGER("Nicht schwanger", "Die Person ist nicht schwanger.", "local", "at0013"),

  UNBEKANNT(
      "Unbekannt",
      "Es ist nicht bekannt, ob die Person schwanger ist oder nicht.",
      "local",
      "at0014");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  StatusDefiningCode2(String value, String description, String terminologyId, String code) {
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
