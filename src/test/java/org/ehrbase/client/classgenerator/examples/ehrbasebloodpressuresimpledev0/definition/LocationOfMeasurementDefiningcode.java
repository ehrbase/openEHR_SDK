package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LocationOfMeasurementDefiningcode implements EnumValueSet {
  LEFTARM("Left arm", "The left arm of the person", "local", "at0026"),

  RIGHTWRIST("Right wrist", "The right wrist of the person", "local", "at1020"),

  FINGER("Finger", "A finger of the person", "local", "at1026"),

  RIGHTANKLE("Right ankle", "The right ankle of the person", "local", "at1031"),

  LEFTTHIGH("Left thigh", "The left thigh of the person", "local", "at0028"),

  LEFTANKLE("Left ankle", "The left ankle of the person", "local", "at1032"),

  LEFTWRIST("Left wrist", "The left wrist of the person", "local", "at1021"),

  RIGHTTHIGH("Right thigh", "The right thigh of the person", "local", "at0027"),

  INTRAARTERIAL("Intra-arterial", "Blood pressure monitored via an intra-arterial line", "local", "at0032"),

  RIGHTARM("Right arm", "The right arm of the person", "local", "at0025");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LocationOfMeasurementDefiningcode(String value, String description, String terminologyId,
                                    String code) {
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
