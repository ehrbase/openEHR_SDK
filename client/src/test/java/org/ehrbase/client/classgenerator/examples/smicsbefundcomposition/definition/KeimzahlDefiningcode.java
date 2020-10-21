package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KeimzahlDefiningcode implements EnumValueSet {
  UNKNOWN("unknown", "unknown", "openehr", "253"),

  NO_INFORMATION("no information", "no information", "openehr", "271"),

  MASKED("masked", "masked", "openehr", "272"),

  NOT_APPLICABLE("not applicable", "not applicable", "openehr", "273");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  KeimzahlDefiningcode(String value, String description, String terminologyId, String code) {
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
