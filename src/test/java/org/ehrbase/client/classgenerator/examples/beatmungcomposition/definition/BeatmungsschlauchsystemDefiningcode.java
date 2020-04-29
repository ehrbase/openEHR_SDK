package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BeatmungsschlauchsystemDefiningcode implements EnumValueSet {
  ZWEISCHLAUCHSYSTEM("Zweischlauchsystem", "Zweischlauchsystem", "local", "at0077"),

  ZWEIFACHVERKABELT("Zweifach verkabelt", "Zweifach verkabelt", "local", "at0.102"),

  EINFACHVERKABELT("Einfach verkabelt", "Einfach verkabelt", "local", "at0.101"),

  EINSCHLAUCHSYSTEM("Einschlauchsystem", "Einschlauchsystem", "local", "at0076");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BeatmungsschlauchsystemDefiningcode(String value, String description, String terminologyId,
      String code) {
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
