package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.899031+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class StandortCluster implements LocatableEntity {
  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Standorttyp Description: Kategorisierung des
   * Standorttyps, z.B. Klinik, zu Hause.
   */
  @Path("/items[at0040]/value|value")
  private String standorttypValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Standortbeschreibung Description: Das Feld
   * enthält die Freitextbeschreibung des Standorts, z.B. Throax-, Herz- und Gefäßchirurgie.
   */
  @Path("/items[at0046]/value|value")
  private String standortbeschreibungValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Standortschlüssel Description: Kodierung des
   * Standortes, z.B. der Fachabteilungsschlüssel (z. B. 2000 Thoraxchirurgie).
   */
  @Path("/items[at0048]/value|defining_code")
  private StandortschlusselDefiningCode standortschlusselDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Campus Description: Eine Gruppe von Gebäuden
   * an anderen Orten, wie ein örtlich entfernter Campus, der außerhalb der Einrichtung liegt, aber
   * zur Institution gehört.
   */
  @Path("/items[at0024]/value|value")
  private String campusValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Gebäudegruppe Description: Ein Gebäude oder
   * Bauwerk. Dazu können Räume, Flure, Flügel, etc. gehören. Es hat möglicherweise keine Wände oder
   * ein Dach, wird aber dennoch als definierter/zugeordneter Raum angesehen.
   */
  @Path("/items[at0025]/value|value")
  private String gebaudegruppeValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Ebene Description: Die Ebene in einem
   * mehrstöckigen Gebäude/Bauwerk.
   */
  @Path("/items[at0028]/value|value")
  private String ebeneValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Stationskennung Description: Eine Station ist
   * Teil einer medizinischen Einrichtung, die Räume und andere Arten von Orten enthalten kann.
   */
  @Path("/items[at0027 and name/value='Stationskennung']/value|value")
  private String stationskennungValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Zimmerkennung Description: Ein Ort, der als
   * Zimmer deklariert wurde. Bei einigen Standorten kann das Zimmer im Flur liegen zB: Station XYZ
   * Flur 2. Hierbei liegt der Bettstellplatz 2 auf dem Flur der jeweiligen Station.
   */
  @Path("/items[at0029 and name/value='Zimmerkennung']/value|value")
  private String zimmerkennungValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Bettplatzkennung Description: Beschreibung
   * des Bettstellplatzes z.B. Bett stand neben dem Fenster oder neben der Tür.
   */
  @Path("/items[at0034 and name/value='Bettplatzkennung']/value|value")
  private String bettplatzkennungValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett Description: Ein Instrument,
   * ein Gerät, ein Implantat, ein Material oder ähnliches, das für die Bereitstellung von
   * Gesundheitsleistungen verwendet wird. In diesem Zusammenhang umfasst ein medizinisches Gerät
   * eine breite Palette von Geräten, die auf verschiedene physikalische, mechanische, thermische
   * oder ähnliche Weise wirken, schließt jedoch insbesondere Geräte aus, die auf medizinischem Wege
   * wirken, wie zum Beispiel pharmakologische, metabolische oder immunologische Methoden. Der
   * Geltungsbereich umfasst Einweggeräte sowie langlebige oder dauerhafte Geräte, die nachverfolgt,
   * gewartet oder regelmäßig kalibriert werden müssen, wobei zu berücksichtigen ist, dass für jeden
   * Gerätetyp bestimmte Datenaufzeichnungsanforderungen gelten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.device.v1 and name/value='Details zum Bett']")
  private DetailsZumBettCluster detailsZumBett;

  /** Path: Patientenaufenthalt/Versorgungsort/Standort/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStandorttypValue(String standorttypValue) {
    this.standorttypValue = standorttypValue;
  }

  public String getStandorttypValue() {
    return this.standorttypValue;
  }

  public void setStandortbeschreibungValue(String standortbeschreibungValue) {
    this.standortbeschreibungValue = standortbeschreibungValue;
  }

  public String getStandortbeschreibungValue() {
    return this.standortbeschreibungValue;
  }

  public void setStandortschlusselDefiningCode(
      StandortschlusselDefiningCode standortschlusselDefiningCode) {
    this.standortschlusselDefiningCode = standortschlusselDefiningCode;
  }

  public StandortschlusselDefiningCode getStandortschlusselDefiningCode() {
    return this.standortschlusselDefiningCode;
  }

  public void setCampusValue(String campusValue) {
    this.campusValue = campusValue;
  }

  public String getCampusValue() {
    return this.campusValue;
  }

  public void setGebaudegruppeValue(String gebaudegruppeValue) {
    this.gebaudegruppeValue = gebaudegruppeValue;
  }

  public String getGebaudegruppeValue() {
    return this.gebaudegruppeValue;
  }

  public void setEbeneValue(String ebeneValue) {
    this.ebeneValue = ebeneValue;
  }

  public String getEbeneValue() {
    return this.ebeneValue;
  }

  public void setStationskennungValue(String stationskennungValue) {
    this.stationskennungValue = stationskennungValue;
  }

  public String getStationskennungValue() {
    return this.stationskennungValue;
  }

  public void setZimmerkennungValue(String zimmerkennungValue) {
    this.zimmerkennungValue = zimmerkennungValue;
  }

  public String getZimmerkennungValue() {
    return this.zimmerkennungValue;
  }

  public void setBettplatzkennungValue(String bettplatzkennungValue) {
    this.bettplatzkennungValue = bettplatzkennungValue;
  }

  public String getBettplatzkennungValue() {
    return this.bettplatzkennungValue;
  }

  public void setDetailsZumBett(DetailsZumBettCluster detailsZumBett) {
    this.detailsZumBett = detailsZumBett;
  }

  public DetailsZumBettCluster getDetailsZumBett() {
    return this.detailsZumBett;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
