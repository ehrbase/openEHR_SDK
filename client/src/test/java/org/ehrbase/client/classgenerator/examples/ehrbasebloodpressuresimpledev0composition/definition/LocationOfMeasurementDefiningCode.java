package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LocationOfMeasurementDefiningCode implements EnumValueSet {
  LEFT_THIGH("Left thigh", "The left thigh of the person", "local", "at0028"),

  INTRA_ARTERIAL(
      "Intra-arterial", "Blood pressure monitored via an intra-arterial line", "local", "at0032"),

  FINGER("Finger", "A finger of the person", "local", "at1026"),

  RIGHT_ANKLE("Right ankle", "The right ankle of the person", "local", "at1031"),

  LEFT_WRIST("Left wrist", "The left wrist of the person", "local", "at1021"),

  RIGHT_WRIST("Right wrist", "The right wrist of the person", "local", "at1020"),

  LEFT_ARM("Left arm", "The left arm of the person", "local", "at0026"),

  LEFT_ANKLE("Left ankle", "The left ankle of the person", "local", "at1032"),

  RIGHT_THIGH("Right thigh", "The right thigh of the person", "local", "at0027"),

  RIGHT_ARM("Right arm", "The right arm of the person", "local", "at0025");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LocationOfMeasurementDefiningCode(
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
