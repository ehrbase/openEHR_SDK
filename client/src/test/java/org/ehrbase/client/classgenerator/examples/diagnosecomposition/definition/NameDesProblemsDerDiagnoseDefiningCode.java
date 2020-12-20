package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum NameDesProblemsDerDiagnoseDefiningCode implements EnumValueSet {
  COVID19_VIRUS_NACHGEWIESEN(
      "COVID-19, Virus nachgewiesen", "COVID-19, Virus nachgewiesen", "ICD-10-GM-2020", "U07.1"),

  INFEKTION_DURCH_KORONAVIREN_NICHT_NAHER_BEZEICHNETER_LOKALISATION(
      "Infektion durch Koronaviren nicht näher bezeichneter Lokalisation",
      "Infektion durch Koronaviren nicht näher bezeichneter Lokalisation",
      "ICD-10-GM-2020",
      "B34.2"),

  KORONAVIREN_ALS_URSACHE_VON_KRANKHEITEN_DIE_IN_ANDEREN_KAPITELN_KLASSIFIZIERT_SIND(
      "Koronaviren als Ursache von Krankheiten, die in anderen Kapiteln klassifiziert sind",
      "Koronaviren als Ursache von Krankheiten, die in anderen Kapiteln klassifiziert sind",
      "ICD-10-GM-2020",
      "B97.2"),

  COVID19_VIRUS_NICHT_NACHGEWIESEN(
      "COVID-19, Virus nicht nachgewiesen",
      "COVID-19, Virus nicht nachgewiesen",
      "ICD-10-GM-2020",
      "U07.2");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  NameDesProblemsDerDiagnoseDefiningCode(
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
