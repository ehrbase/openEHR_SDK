package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum Definingcode implements EnumValueSet {
  PS("PS", "Druckunterstützung", "local", "at0124"),

  ASSIST("ASB/Assist", "ASB/Assist", "local", "at0142"),

  ST("ST", "ST", "local", "at0139"),

  VS("VS", "VS", "local", "at0147"),

  APRV("CPAP/APRV", "CPAP/APRV", "local", "at0141"),

  CPAP("PS/CPAP", "PS/CPAP", "local", "at0140"),

  TC("PC/TC", "PC/TC", "local", "at0118"),

  ASB("ASB", "unterstützte Spontanatmung", "local", "at0135"),

  CV("CV", "CV", "local", "at0129"),

  TS("TS", "TS", "local", "at0122"),

  PC("PC", "druckkontrolliert", "local", "at0115"),

  CMV("CMV", "Kontrollierte maschinelle Beatmung", "local", "at0131"),

  VC("VC", "volumenkontrolliert", "local", "at0116");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  Definingcode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public String getValue() {
     return this.value ;
  }

  public String getDescription() {
     return this.description ;
  }

  public String getTerminologyId() {
     return this.terminologyId ;
  }

  public String getCode() {
     return this.code ;
  }
}
