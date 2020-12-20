package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ValueDefiningCode7 implements EnumValueSet {
  SKIN_JOINT_WOUND(
      "Skin / joint/ wound",
      "The likely source of infection is skin, joint or wound.",
      "local",
      "at0026"),

  URINE("Urine", "The likely source of infection is urine.", "local", "at0014"),

  INDWELLING_DEVICE(
      "Indwelling device",
      "The likely source of infection is an indwelling device.",
      "local",
      "at0027"),

  RESPIRATORY("Respiratory", "The likely source of infection is respiratory.", "local", "at0012"),

  SURGICAL("Surgical", "The likely source of infection is surgical.", "local", "at0015"),

  BRAIN("Brain", "The likely source of infection is brain.", "local", "at0013");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ValueDefiningCode7(String value, String description, String terminologyId, String code) {
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
