package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum PositionDefiningCode implements EnumValueSet {
  STANDING("Standing", "Standing at the time of blood pressure measurement", "local", "at1000"),

  LYING("Lying", "Lying flat at the time of blood pressure measurement", "local", "at1003"),

  RECLINING("Reclining", "Reclining at the time of blood pressure measurement", "local", "at1002"),

  TRENDELENBURG(
      "Trendelenburg",
      "Lying flat on the back (supine position) with the feet higher than the head\n"
          + "                        at the time of blood pressure measurement\n"
          + "                    ",
      "local",
      "at1013"),

  LEFT_LATERAL(
      "Left Lateral",
      "Lying on the left side at the time of blood pressure measurement",
      "local",
      "at1014"),

  SITTING(
      "Sitting",
      "Sitting (for example on bed or chair) at the time of blood pressure\n"
          + "                        measurement\n"
          + "                    ",
      "local",
      "at1001");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  PositionDefiningCode(String value, String description, String terminologyId, String code) {
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
