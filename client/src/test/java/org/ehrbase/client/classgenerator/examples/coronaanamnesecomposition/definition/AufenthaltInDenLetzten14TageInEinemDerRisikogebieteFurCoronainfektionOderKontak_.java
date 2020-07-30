package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_ implements EnumValueSet {
  NEIN("Nein", "Der Patient ist in letzter Zeit nicht gereist.", "local", "at0113"),

  JA("Ja", "Der Patient ist vor kurzem gereist.", "local", "at0112");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_(String value,
                                                                                   String description, String terminologyId, String code) {
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
