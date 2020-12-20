package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.124029600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class DienstleistungAktuelleAktivitatActivity implements LocatableEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Name der Dienstleistung
   * Description: Der Name der einzelnen beantragten Dienstleistung oder Aktivität.
   */
  @Path("/description[at0009]/items[at0121]/value|value")
  private String nameDerDienstleistungValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Grund für die Anforderung
   * Description: Ein kurzer Satz, der den Grund für die Anforderung beschreibt. Comment: Eine
   * Kodierung des "Grund für die Anforderung" mit einem Kodierungssystem ist wünschenswert, sofern
   * vorhanden. Dieses Datenelement erlaubt mehrfaches Vorkommen, so dass der Benutzer bei Bedarf
   * eine Mehrfachantwort aufzeichnen kann. Zum Beispiel: 'Diabetes-Komplikationen behandeln'.
   */
  @Path("/description[at0009]/items[at0062]")
  private List<DienstleistungGrundFurDieAnforderungElement> grundFurDieAnforderung;

  /**
   * Path: Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Komplexe Zeitplanung
   * Description: Einzelheiten über eine komplexe Dienstanforderung, die eine Abfolge von
   * Zeitangaben erfordert. Comment: Zum Beispiel: stündliche Vitalparameterbeobachtungen für 4
   * Stunden, dann 4 stündliche Beobachtungen für 20 Stunden" oder "jeden dritten Mittwoch für 3
   * Besuche".
   */
  @Path("/description[at0009]/items[at0151]")
  private List<Cluster> komplexeZeitplanung;

  /**
   * Path: Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Spezifische Details
   * Description: Zusätzliche Details über die angeforderte Dienstleistung. Comment: Zum Beispiel:
   * Probendetails für einen Antrag auf einen Labortest oder anatomischer Ort für einen Antrag auf
   * ein Verfahren.
   */
  @Path("/description[at0009]/items[at0132]")
  private List<Cluster> spezifischeDetails;

  /**
   * Path: Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Unterstützende Informationen
   * Description: Digitales Dokument, Bild, Video oder Diagramm als zusätzliche Information zur
   * Unterstützung oder Information der Anforderung.
   */
  @Path("/description[at0009]/items[at0149]")
  private List<Cluster> unterstutzendeInformationen;

  /**
   * Path: Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Anforderungen an Patienten
   * Description: Sprach-, Transport- oder andere persönliche Anforderungen, um die Beteiligung oder
   * Teilnahme des Patienten an der Erbringung der Dienstleistung zu unterstützen.
   */
  @Path("/description[at0009]/items[at0116]")
  private List<Cluster> anforderungenAnPatienten;

  /** Path: Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/timing */
  @Path("/timing")
  private DvParsable timing;

  /** Path: Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameDerDienstleistungValue(String nameDerDienstleistungValue) {
    this.nameDerDienstleistungValue = nameDerDienstleistungValue;
  }

  public String getNameDerDienstleistungValue() {
    return this.nameDerDienstleistungValue;
  }

  public void setGrundFurDieAnforderung(
      List<DienstleistungGrundFurDieAnforderungElement> grundFurDieAnforderung) {
    this.grundFurDieAnforderung = grundFurDieAnforderung;
  }

  public List<DienstleistungGrundFurDieAnforderungElement> getGrundFurDieAnforderung() {
    return this.grundFurDieAnforderung;
  }

  public void setKomplexeZeitplanung(List<Cluster> komplexeZeitplanung) {
    this.komplexeZeitplanung = komplexeZeitplanung;
  }

  public List<Cluster> getKomplexeZeitplanung() {
    return this.komplexeZeitplanung;
  }

  public void setSpezifischeDetails(List<Cluster> spezifischeDetails) {
    this.spezifischeDetails = spezifischeDetails;
  }

  public List<Cluster> getSpezifischeDetails() {
    return this.spezifischeDetails;
  }

  public void setUnterstutzendeInformationen(List<Cluster> unterstutzendeInformationen) {
    this.unterstutzendeInformationen = unterstutzendeInformationen;
  }

  public List<Cluster> getUnterstutzendeInformationen() {
    return this.unterstutzendeInformationen;
  }

  public void setAnforderungenAnPatienten(List<Cluster> anforderungenAnPatienten) {
    this.anforderungenAnPatienten = anforderungenAnPatienten;
  }

  public List<Cluster> getAnforderungenAnPatienten() {
    return this.anforderungenAnPatienten;
  }

  public void setTiming(DvParsable timing) {
    this.timing = timing;
  }

  public DvParsable getTiming() {
    return this.timing;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
