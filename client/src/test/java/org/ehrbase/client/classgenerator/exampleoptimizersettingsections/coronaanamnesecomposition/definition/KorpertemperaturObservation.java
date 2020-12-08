package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.body_temperature.v2")
public class KorpertemperaturObservation implements EntryEntity {
  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/Temperatur
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double temperaturMagnitude;

  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/Temperatur
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String temperaturUnits;

  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/Störfaktoren
   */
  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0066]")
  private List<KorpertemperaturStorfaktorenElement> storfaktoren;

  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/Umgebungsbedingungen
   */
  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0056]")
  private List<Cluster> umgebungsbedingungen;

  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/Betätigung
   */
  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0057]")
  private Cluster betatigung;

  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/time
   */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Bericht/Symptome/Körpertemperatur/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Bericht/Symptome/Körpertemperatur/Strukturierte Lokalisation der Messung
   */
  @Path("/protocol[at0020]/items[at0064]")
  private List<Cluster> strukturierteLokalisationDerMessung;

  /**
   * Bericht/Symptome/Körpertemperatur/Gerät
   */
  @Path("/protocol[at0020]/items[at0059]")
  private Cluster gerat;

  /**
   * Bericht/Symptome/Körpertemperatur/Erweiterung
   */
  @Path("/protocol[at0020]/items[at0062]")
  private List<Cluster> erweiterung;

  /**
   * Bericht/Symptome/Körpertemperatur/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Bericht/Symptome/Körpertemperatur/language
   */
  @Path("/language")
  private Language language;

  /**
   * Bericht/Symptome/Körpertemperatur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setTemperaturMagnitude(Double temperaturMagnitude) {
     this.temperaturMagnitude = temperaturMagnitude;
  }

  public Double getTemperaturMagnitude() {
     return this.temperaturMagnitude ;
  }

  public void setTemperaturUnits(String temperaturUnits) {
     this.temperaturUnits = temperaturUnits;
  }

  public String getTemperaturUnits() {
     return this.temperaturUnits ;
  }

  public void setStorfaktoren(List<KorpertemperaturStorfaktorenElement> storfaktoren) {
     this.storfaktoren = storfaktoren;
  }

  public List<KorpertemperaturStorfaktorenElement> getStorfaktoren() {
     return this.storfaktoren ;
  }

  public void setUmgebungsbedingungen(List<Cluster> umgebungsbedingungen) {
     this.umgebungsbedingungen = umgebungsbedingungen;
  }

  public List<Cluster> getUmgebungsbedingungen() {
     return this.umgebungsbedingungen ;
  }

  public void setBetatigung(Cluster betatigung) {
     this.betatigung = betatigung;
  }

  public Cluster getBetatigung() {
     return this.betatigung ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setStrukturierteLokalisationDerMessung(
      List<Cluster> strukturierteLokalisationDerMessung) {
     this.strukturierteLokalisationDerMessung = strukturierteLokalisationDerMessung;
  }

  public List<Cluster> getStrukturierteLokalisationDerMessung() {
     return this.strukturierteLokalisationDerMessung ;
  }

  public void setGerat(Cluster gerat) {
     this.gerat = gerat;
  }

  public Cluster getGerat() {
     return this.gerat ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
