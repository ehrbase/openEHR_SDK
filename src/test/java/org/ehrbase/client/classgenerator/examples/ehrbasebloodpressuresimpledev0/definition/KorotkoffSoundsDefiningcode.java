package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KorotkoffSoundsDefiningcode implements EnumValueSet {
    FOURTHSOUND("Fourth sound", "The fourth Korotkoff sound is identified as an abrupt muffling of sounds", "local", "at1011"),

    FIFTHSOUND("Fifth sound", "The fifth Korotkoff sound is identified by absence of sounds as the cuff pressure drops below the diastolic blood pressure", "local", "at1012");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    KorotkoffSoundsDefiningcode(String value, String description, String terminologyId, String code) {
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
