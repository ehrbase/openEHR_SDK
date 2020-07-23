package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BodyExposureDefiningcode implements EnumValueSet {
  BEDDING("Reduced clothing/bedding", "The person is covered by a lesser amount of clothing or bedding than deemed\n"
          + "                        appropriate for the environmental circumstances.\n"
          + "                    ", "local", "at0032"),

  NAKED("Naked", "No clothing, bedding or covering.", "local", "at0031");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BodyExposureDefiningcode(String value, String description, String terminologyId, String code) {
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
