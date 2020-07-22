package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum VorhandenDefiningcode implements EnumValueSet {
  VORHANDEN("Vorhanden", "Das spezifische Symptom oder Anzeichen ist vorhanden.", "local", "at0023"),

  NICHT_VORHANDEN("Nicht vorhanden", "Das spezifische Symptom oder Anzeichen ist nicht vorhanden.", "local", "at0024");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  VorhandenDefiningcode(String value, String description, String terminologyId, String code) {
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
