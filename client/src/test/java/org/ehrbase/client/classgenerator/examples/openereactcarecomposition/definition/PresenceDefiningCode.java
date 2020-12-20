package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum PresenceDefiningCode implements EnumValueSet {
  UNKNOWN("Unknown", "It is not known if the symptom is present.", "local", "at0.4"),

  ABSENT("Absent", "The symptom is absent.", "local", "at0.3"),

  PRESENT("Present", "The symptom is present.", "local", "at0.2");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  PresenceDefiningCode(String value, String description, String terminologyId, String code) {
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
