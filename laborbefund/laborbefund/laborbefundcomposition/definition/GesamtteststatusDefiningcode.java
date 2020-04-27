package laborbefund.laborbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum GesamtteststatusDefiningcode implements EnumValueSet {
  REGISTRIERT("Registriert", "Der Labortest wurde im Laborinformationssystem registriert, aber es ist derzeit noch nichts verfügbar.", "local", "at0107"),

  ABGEBROCHEN("Abgebrochen", "Das Ergebnis ist nicht verfügbar, weil der Test nicht gestartet oder nicht abgeschlossen wurde (manchmal auch als \"gescheitert\" bezeichnet).", "local", "at0074"),

  FINAL("Final", "Das Testergebnis ist vollständig und durch eine autorisierte Person bestätigt.", "local", "at0038");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  GesamtteststatusDefiningcode(String value, String description, String terminologyId,
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
