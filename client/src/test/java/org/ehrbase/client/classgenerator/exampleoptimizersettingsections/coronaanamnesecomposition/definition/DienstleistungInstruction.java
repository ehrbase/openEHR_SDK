package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-INSTRUCTION.service_request.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:53.424285500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class DienstleistungInstruction implements EntryEntity {
  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität
   */
  @Path("/activities[at0001]")
  private List<DienstleistungAktuelleAktivitatActivity> aktuelleAktivitat;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Einsender
   */
  @Path("/protocol[at0008]/items[at0141]")
  private Cluster einsender;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Empfänger
   */
  @Path("/protocol[at0008]/items[at0142]")
  private Cluster empfanger;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Verteilerliste
   */
  @Path("/protocol[at0008]/items[at0128]")
  private List<Cluster> verteilerliste;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Erweiterung
   */
  @Path("/protocol[at0008]/items[at0112]")
  private List<Cluster> erweiterung;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/narrative
   */
  @Path("/narrative|value")
  private String narrativeValue;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/language
   */
  @Path("/language")
  private Language language;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/expiry_time
   */
  @Path("/expiry_time|value")
  private TemporalAccessor expiryTimeValue;

  public void setAktuelleAktivitat(
      List<DienstleistungAktuelleAktivitatActivity> aktuelleAktivitat) {
     this.aktuelleAktivitat = aktuelleAktivitat;
  }

  public List<DienstleistungAktuelleAktivitatActivity> getAktuelleAktivitat() {
     return this.aktuelleAktivitat ;
  }

  public void setEinsender(Cluster einsender) {
     this.einsender = einsender;
  }

  public Cluster getEinsender() {
     return this.einsender ;
  }

  public void setEmpfanger(Cluster empfanger) {
     this.empfanger = empfanger;
  }

  public Cluster getEmpfanger() {
     return this.empfanger ;
  }

  public void setVerteilerliste(List<Cluster> verteilerliste) {
     this.verteilerliste = verteilerliste;
  }

  public List<Cluster> getVerteilerliste() {
     return this.verteilerliste ;
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

  public void setNarrativeValue(String narrativeValue) {
     this.narrativeValue = narrativeValue;
  }

  public String getNarrativeValue() {
     return this.narrativeValue ;
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

  public void setExpiryTimeValue(TemporalAccessor expiryTimeValue) {
     this.expiryTimeValue = expiryTimeValue;
  }

  public TemporalAccessor getExpiryTimeValue() {
     return this.expiryTimeValue ;
  }
}
