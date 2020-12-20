package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ClinicalRiskCategoryDefiningCode implements EnumValueSet {
  MEDIUM("Medium", "Key threshold for urgent response.", "local", "at0059"),

  LOW("Low", "Ward-based response.", "local", "at0057"),

  LOW_MEDIUM("Low-medium", "Urgent ward-based response.", "local", "at0058"),

  HIGH("High", "Urgent or emergency response.", "local", "at0060");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ClinicalRiskCategoryDefiningCode(
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
