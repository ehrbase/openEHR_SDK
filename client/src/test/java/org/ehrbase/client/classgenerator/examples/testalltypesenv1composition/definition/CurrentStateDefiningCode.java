package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum CurrentStateDefiningCode implements EnumValueSet {
  COMPLETED("completed", "completed", "openehr", "532"),

  PLANNED("planned", "planned", "openehr", "526"),

  ACTIVE("active", "active", "openehr", "245");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  CurrentStateDefiningCode(String value, String description, String terminologyId, String code) {
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
