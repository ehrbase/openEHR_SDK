package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MreKlasseDefiningCode implements EnumValueSet {
  N3MRGN(
      "3MRGN",
      "Multiresistente Gramnegative Bakterien (Resistenz gegen 3 der 4 Antibiotikagruppen)",
      "local",
      "at0065"),

  N2MRGN(
      "2MRGN",
      "Multiresistente Gramnegative Bakterien (Resistenz gegen 2 der 4 Antibiotikagruppen)",
      "local",
      "at0048"),

  VRE("VRE", "Vancomycin-resistente Enterokokken", "local", "at0055"),

  MRSA(
      "MRSA",
      "Methicillin-resistenter Staphylococcus aureus (und CA- community-acquired, LA – lifestock acquired, HA – hospital acquired)",
      "local",
      "at0019"),

  LVRE("LVRE", "Linezolid-Vancomycin-resistenter Enterokokken", "local", "at0056"),

  N4MRGN(
      "4MRGN",
      "Multiresistente Gramnegative Bakterien (Resistenz gegen 4 der 4 Antibiotikagruppen)",
      "local",
      "at0066"),

  LRE("LRE", "Linezolid-resistente Enterokokken", "local", "at0067");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MreKlasseDefiningCode(String value, String description, String terminologyId, String code) {
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
