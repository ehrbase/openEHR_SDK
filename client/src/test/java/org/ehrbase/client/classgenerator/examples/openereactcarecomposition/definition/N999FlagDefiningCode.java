package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum N999FlagDefiningCode implements EnumValueSet {
  N_I_FEEL_I_MIGHT_DIE(
      "‘I feel I might die’", "The patient stated ‘I feel I might die’.", "local", "at0041"),

  EXTREME_SHIVERING_OR_MUSCLE_PAIN(
      "Extreme shivering or muscle pain",
      "The patient has extreme shivering or muscle pain.",
      "local",
      "at0038"),

  SEVERE_BREATHLESSNESS(
      "Severe breathlessness", "The patient has severe breathlessness.", "local", "at0040"),

  SKIN_MOTTLED_ASHEN_BLUE_OR_VERY_PALE(
      "Skin mottled, ashen, blue or very pale",
      "The patient has skin which is mottled, ashen, blue or very pale.",
      "local",
      "at0042"),

  SLURRED_SPEECH_OR_CONFUSION(
      "Slurred speech or confusion",
      "The patient has slurred speech or confusion.",
      "local",
      "at0037"),

  PASSING_NO_URINE_IN_A_DAY(
      "Passing no urine (in a day)",
      "The patient is passing no urine (in a day).",
      "local",
      "at0039");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  N999FlagDefiningCode(String value, String description, String terminologyId, String code) {
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
