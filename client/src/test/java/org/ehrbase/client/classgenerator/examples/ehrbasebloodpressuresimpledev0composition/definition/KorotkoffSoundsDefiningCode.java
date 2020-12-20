package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KorotkoffSoundsDefiningCode implements EnumValueSet {
  FIFTH_SOUND(
      "Fifth sound",
      "The fifth Korotkoff sound is identified by absence of sounds as the cuff\n"
          + "                        pressure drops below the diastolic blood pressure\n"
          + "                    ",
      "local",
      "at1012"),

  FOURTH_SOUND(
      "Fourth sound",
      "The fourth Korotkoff sound is identified as an abrupt muffling of sounds\n"
          + "                    ",
      "local",
      "at1011");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KorotkoffSoundsDefiningCode(String value, String description, String terminologyId, String code) {
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
