package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StatusDefiningCode implements EnumValueSet {
  WAITLIST("waitlist", "*", "local", "at0005"),

  CANCELLED("cancelled", "*", "local", "at0009"),

  ENTERED_IN_ERROR("entered-in-error", "*", "local", "at0010"),

  ACTIVE("active", "*", "local", "at0006"),

  PLANNED("planned", "*", "local", "at0004"),

  ONHOLD("onhold", "*", "local", "at0007"),

  FINISHED("finished", "*", "local", "at0008");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  StatusDefiningCode(String value, String description, String terminologyId, String code) {
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
