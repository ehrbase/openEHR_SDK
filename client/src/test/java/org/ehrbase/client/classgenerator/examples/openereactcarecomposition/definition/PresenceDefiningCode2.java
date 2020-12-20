package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum PresenceDefiningCode2 implements EnumValueSet {
  PRESENT("Present", "The risk factor has been identified for this individual.", "local", "at0018"),

  UNKNOWN("Unknown", "No information is available for this risk factor.", "local", "at0.15"),

  ABSENT(
      "Absent", "The risk factor has not been identified for this individual.", "local", "at0019");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  PresenceDefiningCode2(String value, String description, String terminologyId, String code) {
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
