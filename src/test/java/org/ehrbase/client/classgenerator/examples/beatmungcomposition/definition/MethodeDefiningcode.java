package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MethodeDefiningcode implements EnumValueSet {
  SAUERSTOFFMASKE("Sauerstoffmaske", "Sauerstoffmaske", "local", "at0069"),

  HIGHFLOWTRACHEALKANULE("High-Flow Trachealkanüle", "Eine Trachealkanüle zur Verabreichung mit hohem Durchfluss.", "local", "at0.106"),

  CPAPMASKE("CPAP (Maske)", "CPAP (Maske)", "local", "at0066"),

  VENTURIMASKE("Venturimaske", "Venturimaske", "local", "at0071"),

  OHIOMASKE("Ohio Maske", "Ohio Maske", "local", "at0072"),

  INKUBATOR("Inkubator", "Inkubator", "local", "at0073"),

  CPAPNASAL("CPAP (Nasal)", "CPAP (Nasal)", "local", "at0067"),

  TRACHEALTUBUS("Trachealtubus", "Trachealtubus", "local", "at0.100"),

  TTUBUS("T-Tubus", "T-Tubus", "local", "at0075"),

  NASENKANULE("Nasenkanüle", "Nasenkanüle", "local", "at0070"),

  HIGHFLOWNASENKANULE("High-Flow-Nasenkanüle", "Es wird eine Nasenkanüle verwendet, der für die Versorgung mit hohem Durchfluss ausgelegt ist.", "local", "at0.105"),

  SAUERSTOFFZELT("Sauerstoffzelt", "Sauerstoffzelt", "local", "at0074"),

  NIVNASALMASKE("NIV Nasalmaske", "Eng anliegende Nasalmaske zur Verwendung während der nicht-assistierten Beatmung.", "local", "at0.104"),

  TRACHEALKANULE("Trachealkanüle", "Trachealkanüle", "local", "at0.99"),

  VOLLMASKE("Vollmaske", "Eng anliegende Vollmaske.", "local", "at0068");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MethodeDefiningcode(String value, String description, String terminologyId, String code) {
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
