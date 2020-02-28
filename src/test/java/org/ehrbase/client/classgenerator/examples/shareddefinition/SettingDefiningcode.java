package org.ehrbase.client.classgenerator.examples.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SettingDefiningcode implements EnumValueSet {
    PRIMARYNURSINGCARE("primary nursing care", "primary nursing care", "openehr", "229"),

    NURSINGHOMECARE("nursing home care", "nursing home care", "openehr", "237"),

    HOME("home", "home", "openehr", "225"),

    MIDWIFERYCARE("midwifery care", "midwifery care", "openehr", "231"),

    PRIMARYALLIEDHEALTHCARE("primary allied health care", "primary allied health care", "openehr", "230"),

    DENTALCARE("dental care", "dental care", "openehr", "236"),

    SECONDARYMEDICALCARE("secondary medical care", "secondary medical care", "openehr", "232"),

    EMERGENCYCARE("emergency care", "emergency care", "openehr", "227"),

    PRIMARYMEDICALCARE("primary medical care", "primary medical care", "openehr", "228"),

    SECONDARYNURSINGCARE("secondary nursing care", "secondary nursing care", "openehr", "233"),

    SECONDARYALLIEDHEALTHCARE("secondary allied health care", "secondary allied health care", "openehr", "234"),

    OTHERCARE("other care", "other care", "openehr", "238"),

    COMPLEMENTARYHEALTHCARE("complementary health care", "complementary health care", "openehr", "235");

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
