package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AcvpuDefiningCode implements EnumValueSet {
  CONFUSION(
      "Confusion",
      "A new onset or worsening confusion, delirium or any other altered mentation.",
      "local",
      "at0015"),

  UNRESPONSIVE("Unresponsive", "No response to voice or pain stimuli.", "local", "at0008"),

  VOICE("Voice", "Any verbal, motor or eye response to a voice stimulus.", "local", "at0006"),

  PAIN("Pain", "Any verbal, motor or eye response to a pain stimulus.", "local", "at0007"),

  ALERT(
      "Alert",
      "Fully awake. Spontaneous opening of the eyes, responds to voice and have motor function.",
      "local",
      "at0005");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AcvpuDefiningCode(String value, String description, String terminologyId, String code) {
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
