package org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StateOfDressEnDefiningCode implements EnumValueSet {
  LEICHT_BEKLEIDET_UNTERWASCHE(
      "Leicht bekleidet / Unterwäsche",
      "Bekleidung, die nicht signifikant zum Gewicht beiträgt.",
      "local",
      "at0011"),

  UNBEKLEIDET("Unbekleidet", "Ohne Kleidung.", "local", "at0013"),

  N_FULLY_CLOTHED_WITHOUT_SHOES_EN(
      "*Fully clothed, without shoes (en)",
      "*Clothing which may add significantly to weight. (en)",
      "local",
      "at0028"),

  WINDEL("Windel", "Trägt Windel; kann signifikant zum Gewicht beitragen.", "local", "at0017"),

  VOLL_BEKLEIDET_MIT_SCHUHEN(
      "Voll bekleidet, mit Schuhen",
      "Bekleidung, die signifikant zum Gewicht beiträgt, mit Schuhen.",
      "local",
      "at0010");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  StateOfDressEnDefiningCode(String value, String description, String terminologyId, String code) {
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
