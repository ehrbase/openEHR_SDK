package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MaskengroEDefiningcode implements EnumValueSet {
  XS("XS", "XS", "local", "at0050"),

  L("L", "L", "local", "at0053"),

  KLEIN("Klein", "Klein", "local", "at0049"),

  MINI("Mini", "Mini", "local", "at0048"),

  M("M", "M", "local", "at0052"),

  S("S", "S", "local", "at0051");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MaskengroEDefiningcode(String value, String description, String terminologyId, String code) {
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
