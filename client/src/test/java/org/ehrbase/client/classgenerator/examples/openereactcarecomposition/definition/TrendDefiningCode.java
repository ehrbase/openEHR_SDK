package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum TrendDefiningCode implements EnumValueSet {
  WORSENING(
      "Worsening",
      "The severity of the symptom or sign has worsened overall during this episode.",
      "local",
      "at0183"),

  IMPROVING(
      "Improving",
      "The severity of the symptom or sign has improved overall during this episode.",
      "local",
      "at0181"),

  UNCHANGED(
      "Unchanged",
      "The severity of the symptom or sign has not changed overall during this episode.",
      "local",
      "at0182");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  TrendDefiningCode(String value, String description, String terminologyId, String code) {
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
