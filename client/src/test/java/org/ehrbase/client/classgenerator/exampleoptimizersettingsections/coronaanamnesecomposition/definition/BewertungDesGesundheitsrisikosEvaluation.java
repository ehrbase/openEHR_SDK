package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.health_risk.v1")
public class BewertungDesGesundheitsrisikosEvaluation {
  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Gesundheitsrisiko
   */
  @Path("/data[at0001]/items[at0002]/value|value")
  private String gesundheitsrisikoValue;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Spezifischer Risikofaktor/Risikofaktor
   */
  @Path("/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0013]/value|value")
  private String spezifischerRisikofaktorRisikofaktorValue;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Spezifischer Risikofaktor/Vorhandensein
   */
  @Path("/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0017]/value|defining_code")
  private VorhandenseinDefiningCode2 spezifischerRisikofaktorVorhandenseinDefiningCode;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Spezifischer Risikofaktor/Details
   */
  @Path("/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0027]")
  private List<Cluster> spezifischerRisikofaktorDetails;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Andere Risikofaktoren/Risikofaktor
   */
  @Path("/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0013]/value|value")
  private String andereRisikofaktorenRisikofaktorValue;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Andere Risikofaktoren/Vorhandensein
   */
  @Path("/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0017]/value|defining_code")
  private VorhandenseinDefiningCode2 andereRisikofaktorenVorhandenseinDefiningCode;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Andere Risikofaktoren/Details
   */
  @Path("/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0027]")
  private List<Cluster> andereRisikofaktorenDetails;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Risikobewertung
   */
  @Path("/data[at0001]/items[at0003]/value|value")
  private String risikobewertungValue;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Letzte Aktualisierung
   */
  @Path("/protocol[at0010]/items[at0024]/value|value")
  private TemporalAccessor letzteAktualisierungValue;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Bewertungsmethode
   */
  @Path("/protocol[at0010]/items[at0025]/value|value")
  private String bewertungsmethodeValue;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Erweiterung
   */
  @Path("/protocol[at0010]/items[at0011]")
  private List<Cluster> erweiterung;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/language
   */
  @Path("/language")
  private Language language;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setGesundheitsrisikoValue(String gesundheitsrisikoValue) {
     this.gesundheitsrisikoValue = gesundheitsrisikoValue;
  }

  public String getGesundheitsrisikoValue() {
     return this.gesundheitsrisikoValue ;
  }

  public void setSpezifischerRisikofaktorRisikofaktorValue(
      String spezifischerRisikofaktorRisikofaktorValue) {
     this.spezifischerRisikofaktorRisikofaktorValue = spezifischerRisikofaktorRisikofaktorValue;
  }

  public String getSpezifischerRisikofaktorRisikofaktorValue() {
     return this.spezifischerRisikofaktorRisikofaktorValue ;
  }

  public void setSpezifischerRisikofaktorVorhandenseinDefiningCode(
      VorhandenseinDefiningCode2 spezifischerRisikofaktorVorhandenseinDefiningCode) {
     this.spezifischerRisikofaktorVorhandenseinDefiningCode = spezifischerRisikofaktorVorhandenseinDefiningCode;
  }

  public VorhandenseinDefiningCode2 getSpezifischerRisikofaktorVorhandenseinDefiningCode() {
     return this.spezifischerRisikofaktorVorhandenseinDefiningCode ;
  }

  public void setSpezifischerRisikofaktorDetails(List<Cluster> spezifischerRisikofaktorDetails) {
     this.spezifischerRisikofaktorDetails = spezifischerRisikofaktorDetails;
  }

  public List<Cluster> getSpezifischerRisikofaktorDetails() {
     return this.spezifischerRisikofaktorDetails ;
  }

  public void setAndereRisikofaktorenRisikofaktorValue(
      String andereRisikofaktorenRisikofaktorValue) {
     this.andereRisikofaktorenRisikofaktorValue = andereRisikofaktorenRisikofaktorValue;
  }

  public String getAndereRisikofaktorenRisikofaktorValue() {
     return this.andereRisikofaktorenRisikofaktorValue ;
  }

  public void setAndereRisikofaktorenVorhandenseinDefiningCode(
      VorhandenseinDefiningCode2 andereRisikofaktorenVorhandenseinDefiningCode) {
     this.andereRisikofaktorenVorhandenseinDefiningCode = andereRisikofaktorenVorhandenseinDefiningCode;
  }

  public VorhandenseinDefiningCode2 getAndereRisikofaktorenVorhandenseinDefiningCode() {
     return this.andereRisikofaktorenVorhandenseinDefiningCode ;
  }

  public void setAndereRisikofaktorenDetails(List<Cluster> andereRisikofaktorenDetails) {
     this.andereRisikofaktorenDetails = andereRisikofaktorenDetails;
  }

  public List<Cluster> getAndereRisikofaktorenDetails() {
     return this.andereRisikofaktorenDetails ;
  }

  public void setRisikobewertungValue(String risikobewertungValue) {
     this.risikobewertungValue = risikobewertungValue;
  }

  public String getRisikobewertungValue() {
     return this.risikobewertungValue ;
  }

  public void setLetzteAktualisierungValue(TemporalAccessor letzteAktualisierungValue) {
     this.letzteAktualisierungValue = letzteAktualisierungValue;
  }

  public TemporalAccessor getLetzteAktualisierungValue() {
     return this.letzteAktualisierungValue ;
  }

  public void setBewertungsmethodeValue(String bewertungsmethodeValue) {
     this.bewertungsmethodeValue = bewertungsmethodeValue;
  }

  public String getBewertungsmethodeValue() {
     return this.bewertungsmethodeValue ;
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
