package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
public class StandortCluster {
  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Standorttyp
   */
  @Path("/items[at0040]/value|value")
  private String standorttypValue;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Standortbeschreibung
   */
  @Path("/items[at0046]/value|value")
  private String standortbeschreibungValue;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Standortschlüssel
   */
  @Path("/items[at0048]/value|defining_code")
  private StandortschlusselDefiningCode standortschlusselDefiningCode;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Campus
   */
  @Path("/items[at0024]/value|value")
  private String campusValue;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Gebäudegruppe
   */
  @Path("/items[at0025]/value|value")
  private String gebaudegruppeValue;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Ebene
   */
  @Path("/items[at0028]/value|value")
  private String ebeneValue;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Stationskennung
   */
  @Path("/items[at0027 and name/value='Stationskennung']/value|value")
  private String stationskennungValue;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Zimmerkennung
   */
  @Path("/items[at0029 and name/value='Zimmerkennung']/value|value")
  private String zimmerkennungValue;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Bettplatzkennung
   */
  @Path("/items[at0034 and name/value='Bettplatzkennung']/value|value")
  private String bettplatzkennungValue;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett
   */
  @Path("/items[openEHR-EHR-CLUSTER.device.v1 and name/value='Details zum Bett']")
  private DetailsZumBettCluster detailsZumBett;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStandorttypValue(String standorttypValue) {
     this.standorttypValue = standorttypValue;
  }

  public String getStandorttypValue() {
     return this.standorttypValue ;
  }

  public void setStandortbeschreibungValue(String standortbeschreibungValue) {
     this.standortbeschreibungValue = standortbeschreibungValue;
  }

  public String getStandortbeschreibungValue() {
     return this.standortbeschreibungValue ;
  }

  public void setStandortschlusselDefiningCode(
      StandortschlusselDefiningCode standortschlusselDefiningCode) {
     this.standortschlusselDefiningCode = standortschlusselDefiningCode;
  }

  public StandortschlusselDefiningCode getStandortschlusselDefiningCode() {
     return this.standortschlusselDefiningCode ;
  }

  public void setCampusValue(String campusValue) {
     this.campusValue = campusValue;
  }

  public String getCampusValue() {
     return this.campusValue ;
  }

  public void setGebaudegruppeValue(String gebaudegruppeValue) {
     this.gebaudegruppeValue = gebaudegruppeValue;
  }

  public String getGebaudegruppeValue() {
     return this.gebaudegruppeValue ;
  }

  public void setEbeneValue(String ebeneValue) {
     this.ebeneValue = ebeneValue;
  }

  public String getEbeneValue() {
     return this.ebeneValue ;
  }

  public void setStationskennungValue(String stationskennungValue) {
     this.stationskennungValue = stationskennungValue;
  }

  public String getStationskennungValue() {
     return this.stationskennungValue ;
  }

  public void setZimmerkennungValue(String zimmerkennungValue) {
     this.zimmerkennungValue = zimmerkennungValue;
  }

  public String getZimmerkennungValue() {
     return this.zimmerkennungValue ;
  }

  public void setBettplatzkennungValue(String bettplatzkennungValue) {
     this.bettplatzkennungValue = bettplatzkennungValue;
  }

  public String getBettplatzkennungValue() {
     return this.bettplatzkennungValue ;
  }

  public void setDetailsZumBett(DetailsZumBettCluster detailsZumBett) {
     this.detailsZumBett = detailsZumBett;
  }

  public DetailsZumBettCluster getDetailsZumBett() {
     return this.detailsZumBett ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
