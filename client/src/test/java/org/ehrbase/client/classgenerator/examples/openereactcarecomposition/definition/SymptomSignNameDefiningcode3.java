package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SymptomSignNameDefiningcode3 implements EnumValueSet {
  N49727002("49727002", "49727002", "SNOMED-CT", "49727002"),

  N68962001("68962001", "68962001", "SNOMED-CT", "68962001"),

  N84229001("84229001", "84229001", "SNOMED-CT", "84229001"),

  N64531003("64531003", "64531003", "SNOMED-CT", "64531003"),

  N16001004("16001004", "16001004", "SNOMED-CT", "16001004"),

  N162397003("162397003", "162397003", "SNOMED-CT", "162397003"),

  N272028008("272028008", "272028008", "SNOMED-CT", "272028008"),

  N386661006("386661006", "386661006", "SNOMED-CT", "386661006"),

  N57676002("57676002", "57676002", "SNOMED-CT", "57676002");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SymptomSignNameDefiningcode3(String value, String description, String terminologyId,
                               String code) {
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
