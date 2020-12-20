package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum PresenceOfAnyExposureEnDefiningCode implements EnumValueSet {
  N_ABSENT_EN(
      "*Absent (en)",
      "*Exposure is or has been absent at or during the event. (en)",
      "local",
      "at0059"),

  N_UNKNOWN_EN(
      "*Unknown (en)",
      "*It is not known whether any exposure is or has been present or absent at or during the event. (en)",
      "local",
      "at0060"),

  N_PRESENT_EN(
      "*Present (en)",
      "*Exposure is or has been present at or during the event. (en)",
      "local",
      "at0058");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  PresenceOfAnyExposureEnDefiningCode(
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
