package org.ehrbase.kopertemperature.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LokalisationDerMessungDefiningcode implements EnumValueSet {
  MUND("Mund", "Messung der Temperatur im Mund.", "local", "at0022"),

  ACHSELHOHLE("Achselhöhle", "Messung der Temperatur an der Haut der Achselhöhle mit seitlich angelegtem Arm.", "local", "at0024"),

  INTRAVASKULAR("Intravaskulär", "Messung der Temperatur innerhalb des vaskulären Systems.", "local", "at0028"),

  HAUT("Haut", "Messung der Temperatur an freiliegender Haut.", "local", "at0043"),

  OESOPHAGUS("Oesophagus", "Messung der Temperatur innerhalb des Oesophagus.", "local", "at0054"),

  OHRENKANAL("Ohrenkanal", "Messung der Temperatur innerhalb des äußeren Gehörgangs.", "local", "at0023"),

  REKTUM("Rektum", "Messung der Temperatur innerhalb des Rektums.", "local", "at0025"),

  NASOPHARYNX("Nasopharynx", "Messung der Temperatur innerhalb des Nasopharynxs (Nasenrachens).", "local", "at0026"),

  VAGINA("Vagina", "Messung der Temperatur innerhalb der Vagina.", "local", "at0051"),

  SCHLAFE("Schläfe", "Messung der Temperatur an der Schläfe, über der oberflächlichen Schläfenarterie.", "local", "at0060"),

  STIRN("Stirn", "Messung der Temperatur auf der Stirn.", "local", "at0061"),

  INGUINALEHAUTFALTE("Inguinale Hautfalte", "Messung der Temperatur in der inguinalen Hautfalte zwischen Bein und Abdominalwand.", "local", "at0055"),

  HARNBLASE("Harnblase", "Messung der Temperatur in der Harnblase.", "local", "at0027");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LokalisationDerMessungDefiningcode(String value, String description, String terminologyId,
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
