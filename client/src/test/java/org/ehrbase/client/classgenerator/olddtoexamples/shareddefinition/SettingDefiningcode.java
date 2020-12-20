package org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SettingDefiningcode implements EnumValueSet {
  COMPLEMENTARY_HEALTH_CARE(
      "complementary health care", "complementary health care", "openehr", "235"),

  HOME("home", "home", "openehr", "225"),

  SECONDARY_NURSING_CARE("secondary nursing care", "secondary nursing care", "openehr", "233"),

  DENTAL_CARE("dental care", "dental care", "openehr", "236"),

  OTHER_CARE("other care", "other care", "openehr", "238"),

  NURSING_HOME_CARE("nursing home care", "nursing home care", "openehr", "237"),

  SECONDARY_ALLIED_HEALTH_CARE(
      "secondary allied health care", "secondary allied health care", "openehr", "234"),

  EMERGENCY_CARE("emergency care", "emergency care", "openehr", "227"),

  PRIMARY_NURSING_CARE("primary nursing care", "primary nursing care", "openehr", "229"),

  PRIMARY_MEDICAL_CARE("primary medical care", "primary medical care", "openehr", "228"),

  PRIMARY_ALLIED_HEALTH_CARE(
      "primary allied health care", "primary allied health care", "openehr", "230"),

  SECONDARY_MEDICAL_CARE("secondary medical care", "secondary medical care", "openehr", "232"),

  MIDWIFERY_CARE("midwifery care", "midwifery care", "openehr", "231");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SettingDefiningcode(String value, String description, String terminologyId, String code) {
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
