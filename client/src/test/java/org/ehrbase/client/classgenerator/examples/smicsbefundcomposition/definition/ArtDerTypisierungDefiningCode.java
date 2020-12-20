package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ArtDerTypisierungDefiningCode implements EnumValueSet {
  ANDERE("Andere", "*", "local", "at0007"),

  PULSFELDGELELEKTROPHORESE_PFGE("Pulsfeldgelelektrophorese (PFGE)", "*", "local", "at0003"),

  CORE_GENOM_MULTILOCUS_SEQUENCE_TYPING_CGMLST(
      "core genom Multilocus Sequence Typing (cgMLST)", "*", "local", "at0005"),

  MULTILOCUS_SEQUENCE_TYPING_MLST("Multilocus Sequence Typing (MLST)", "*", "local", "at0004"),

  SPA_TYPISIERUNG_STAPHYLOCOCCUS_AUREUS(
      "spa-Typisierung (Staphylococcus aureus)", "*", "local", "at0006");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ArtDerTypisierungDefiningCode(
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
