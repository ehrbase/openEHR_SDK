package org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KlinischerZustandDesPatientenDefiningCode implements EnumValueSet {
  IDENTISCHER_ZUSTAND(
      "Identischer Zustand",
      "Der Gesundheitszustand des Patienten ist identisch, wie bei der Aufnahme.",
      "local",
      "at0005"),

  UNBESTIMMT("Unbestimmt", "Unbestimmt.", "local", "at0008"),

  GEHEILT("Geheilt", "Der Patient ist geheilt.", "local", "at0003"),

  SONSTIGE("Sonstige", "Sonstige", "local", "at0068"),

  SCHLECHTER(
      "Schlechter",
      "Der Gesundheitszustand des Patienten ist schlechter, als bei der Aufnahme.",
      "local",
      "at0006"),

  VERBESSERT(
      "Verbessert", "Der Gesundheitszustand des Patienten hat sich verbessert.", "local", "at0004"),

  VERSTORBEN(
      "Verstorben", "Der Patient verstarb während des Krankenhausaufenthaltes.", "local", "at0007");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KlinischerZustandDesPatientenDefiningCode(
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
