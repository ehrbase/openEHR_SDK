package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum Definingcode2 implements EnumValueSet {
  AGE_OVER75("Age over 75", "Patient is over 75.", "local", "at0007"),

  IMPAIRED_IMMUNITY_E_G_DIABETES_STEROIDS_CHEMOTHERAPY("Impaired immunity (e.g diabetes, steroids, chemotherapy)", "Patient has impaired immunity.", "local", "at0008"),

  BROKEN_SKIN("Indwelling lines / IVDU / broken skin", "Patient has indwelling lines, IVDU or broken skin.", "local", "at0010"),

  INVASIVE_PROCEDURE("Recent trauma / surgery / invasive procedure", "Patient has had recent trauma, surgery or an invasive procedure.", "local", "at0009");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  Definingcode2(String value, String description, String terminologyId, String code) {
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
