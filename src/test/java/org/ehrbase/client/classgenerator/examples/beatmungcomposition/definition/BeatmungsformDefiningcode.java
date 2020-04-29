package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BeatmungsformDefiningcode implements EnumValueSet {
  T("S/T", "Spontaneous timed breathing", "local", "at0084"),

  PCV("PCV", "druckkontrollierte maschinelle Beatmung", "local", "at0083"),

  IPPV("IPPV", "intermittierende Überdruckbeatmung", "local", "at0076"),

  SIMV("SIMV", "synchronisierte intermittierende maschinelle Beatmung", "local", "at0108"),

  CPAP("CPAP", "kontinuierlicher positiver Atemwegsdruck", "local", "at0070"),

  SIGH("Sigh", "Seufzer (Sigh)", "local", "at0097"),

  SPONTAN("Spontan", "Spontanatmung des Patienten", "local", "at0065"),

  PSV("PSV", "druckunterstützte Spontanbeatmung", "local", "at0080"),

  BILEVEL("BILEVEL", "BILEVEL", "local", "at0105"),

  PS("PS", "Druckunterstützung", "local", "at0106"),

  PTV("PTV", "Patient triggered ventilation", "local", "at0075"),

  CMV("CMV", "kontinuierliche maschinelle Beatmung", "local", "at0072"),

  HFO("HFO", "Hochfrequenzbeatmung", "local", "at0073"),

  BIPAP("BIPAP", "zweiphasische positive Atem-Druckunterstützung", "local", "at0079"),

  ASB("SIMV / ASB", "SIMV / ASB", "local", "at0152"),

  C("A/C", "Assist-control ventilation", "local", "at0104"),

  MMV("MMV", "maschinelles Minutenvolumen", "local", "at0077"),

  HFOCMV("HFO + CMV", "HFO + CMV", "local", "at0155");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BeatmungsformDefiningcode(String value, String description, String terminologyId, String code) {
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
