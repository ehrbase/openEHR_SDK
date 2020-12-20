package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BreathingIndicatorDefiningCode implements EnumValueSet {
  UNABLE_TO_SPEAK_FULL_SENTENCES(
      "Unable to speak full sentences", "Unable to speak in full sentences.", "local", "at0069"),

  NOISY_BREATHING("Noisy breathing", "Noisy breathing.", "local", "at0067"),

  SHORT_OF_BREATH("Short of breath", "Short of breath.", "local", "at0068"),

  USE_ACCESSORY_MUSCLES("Use accessory muscles", "Use accessory muscles.", "local", "at0070");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BreathingIndicatorDefiningCode(
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
