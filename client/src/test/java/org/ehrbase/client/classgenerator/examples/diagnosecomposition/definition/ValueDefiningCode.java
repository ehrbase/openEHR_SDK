package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ValueDefiningCode implements EnumValueSet {
  LEBENSWANDEL_BEZOGENE_FAKTOREN(
      "Lebenswandel-bezogene Faktoren", "z. B. Rauchen, Alkoholismus", "local", "at0013"),

  GENETISCHE_HERKUNFT("Genetische Herkunft", "z. B Sichelzellenanämie", "local", "at0009"),

  KORPERLICHE_VERLETZUNG(
      "Körperliche Verletzung",
      "z.B. Kopftrauma, Hitzeerschöpfung, Verletzung durch Strahlenbelastung",
      "local",
      "at0012"),

  ARBEITSRISIKOFAKTOREN("Arbeitsrisikofaktoren", "z.B. Asbestexposition", "local", "at0005"),

  IMMUNOLOGISCHE_HERKUNFT(
      "Immunologische Herkunft",
      "z.B. systemische Lupus erythematodes, AIDS (als Grundursache für Kaposis Sarkom)",
      "local",
      "at0010"),

  BIOLOGISCH("Biologisch", "z.B. Alter, Geschlecht", "local", "at0006"),

  INFEKTION("Infektion", "z.B. Hepatitis C", "local", "at0011"),

  NEUROPSYCHIATRISCHE_HERKUNFT(
      "Neuropsychiatrische Herkunft", "z. B. Alzheimer-Krankheit, Depression", "local", "at0015"),

  METABOLISCH_ENDOKRINER_HERKUNFT(
      "Metabolisch-endokriner Herkunft", "z. B. Akromegalie, Diabetes", "local", "at0014"),

  UMWELTEXPOSITION(
      "Umweltexposition", "z.B. UV-Strahlung aussetzen, Feinstaub Aussetzung", "local", "at0016"),

  SONSTIGES(
      "Sonstiges",
      "Sonstige Ursachen, die mit vorgegebenen Werten nicht dargestellt werden können",
      "local",
      "at0018"),

  CHEMISCHE_VERLETZUNG("Chemische Verletzung", "z. B. Gift, Medikament", "local", "at0007"),

  ERNAHRUNGSRELEVANTE_FAKTOREN(
      "Ernährungsrelevante Faktoren",
      "z. B. Eisenmangel, kohlenhydratreiche Ernährung",
      "local",
      "at0008");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ValueDefiningCode(String value, String description, String terminologyId, String code) {
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
