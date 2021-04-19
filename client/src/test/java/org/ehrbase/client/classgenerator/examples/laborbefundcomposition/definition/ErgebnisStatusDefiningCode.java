package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ErgebnisStatusDefiningCode implements EnumValueSet {
  ERFASST("Erfasst", "Der Test ist im Laborinformationssystem erfasst, aber noch kein Resultat verfügbar.", "local", "at0015"),

  ENDBEFUND_KORRIGIERT("Endbefund, korrigiert", "Der Endbefund wurde erneut modifiziert, ist vollständig und wurde durch den verantwortlichen Pathologen verifiziert. Dies ist eine Unterkategorie von \"Endbefund, geändert\".", "local", "at0019"),

  ENDBEFUND("Endbefund", "Das Testresultat ist vollständig und durch eine autorisierte Person verifiziert.", "local", "at0018"),

  STORNIERT("Storniert", "Das Testresultat ist nicht verfügbar, weil der Test nicht (vollständig) durchgeführt oder abgebrochen wurde.", "local", "at0023"),

  ENDBEFUND_ERGAENZT("Endbefund, ergänzt", "Nach Abschluss wurde der Bericht durch Hinzufügen neuer Inhalte abgeändert. Der vorhandenen Inhalte sind unverändert. Dies ist eine Unterkategorie von \"Endbefund, geändert\".", "local", "at0021"),

  ENDBEFUND_WIDERRUFEN("Endbefund, widerrufen", "Das Testresultat wurde nach Endbefundung zurückgezogen.", "local", "at0022"),

  ENDBEFUND_GEAENDERT("Endbefund, geändert", "Der Endbefund wurde erneut modifiziert, ist vollständig und wurde durch den verantwortlichen Pathologen verifiziert. Des Weiteren haben sich die Ergebnisdaten hierdurch verändert.", "local", "at0020"),

  UNVOLLSTAENDIG("Unvollständig", "Das Testresultat ist ein Anfangs- oder Interimswert, vorläufig oder nicht verifiziert/validiert.", "local", "at0016"),

  VORLAEUFIG("Vorläufig", "Erste, verifizierte Resultate sind vorhanden, der Test ist aber noch nicht abgeschlossen (Sub-Kategorie von 'Unvollständig').", "local", "at0017");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ErgebnisStatusDefiningCode(String value, String description, String terminologyId, String code) {
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
