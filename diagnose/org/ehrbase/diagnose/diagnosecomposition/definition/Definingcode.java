package org.ehrbase.diagnose.diagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum Definingcode implements EnumValueSet {
  UMWELTEXPOSITION("Umweltexposition", "z.B. UV-Strahlung aussetzen, Feinstaub Aussetzung", "local", "at0016"),

  ERNAHRUNGSRELEVANTEFAKTOREN("Ernährungsrelevante Faktoren", "z. B. Eisenmangel, kohlenhydratreiche Ernährung", "local", "at0008"),

  METABOLISCHENDOKRINERHERKUNFT("Metabolisch-endokriner Herkunft", "z. B. Akromegalie, Diabetes", "local", "at0014"),

  NEUROPSYCHIATRISCHEHERKUNFT("Neuropsychiatrische Herkunft", "z. B. Alzheimer-Krankheit, Depression", "local", "at0015"),

  BIOLOGISCH("Biologisch", "z.B. Alter, Geschlecht", "local", "at0006"),

  ARBEITSRISIKOFAKTOREN("Arbeitsrisikofaktoren", "z.B. Asbestexposition", "local", "at0005"),

  SONSTIGES("Sonstiges", "Sonstige Ursachen, die mit vorgegebenen Werten nicht dargestellt werden können", "local", "at0018"),

  CHEMISCHEVERLETZUNG("Chemische Verletzung", "z. B. Gift, Medikament", "local", "at0007"),

  GENETISCHEHERKUNFT("Genetische Herkunft", "z. B Sichelzellenanämie", "local", "at0009"),

  KORPERLICHEVERLETZUNG("Körperliche Verletzung", "z.B. Kopftrauma, Hitzeerschöpfung, Verletzung durch Strahlenbelastung", "local", "at0012"),

  IMMUNOLOGISCHEHERKUNFT("Immunologische Herkunft", "z.B. systemische Lupus erythematodes, AIDS (als Grundursache für Kaposis Sarkom)", "local", "at0010"),

  INFEKTION("Infektion", "z.B. Hepatitis C", "local", "at0011"),

  LEBENSWANDELBEZOGENEFAKTOREN("Lebenswandel-bezogene Faktoren", "z. B. Rauchen, Alkoholismus", "local", "at0013");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  Definingcode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public String getValue() {
     return this.value ;
  }

  public String getDescription() {
     return this.description ;
  }

  public String getTerminologyId() {
     return this.terminologyId ;
  }

  public String getCode() {
     return this.code ;
  }
}
