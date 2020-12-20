package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.health_risk.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.113032300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BewertungDesGesundheitsrisikosEvaluation implements EntryEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Gesundheitsrisiko
   * Description: Identifizierung der potenziellen zukünftigen Krankheit, des Zustands oder des
   * Gesundheitsproblems, für die das Risiko bewertet wird.
   */
  @Path("/data[at0001]/items[at0002]/value|value")
  private String gesundheitsrisikoValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Spezifischer
   * Risikofaktor/Risikofaktor Description: Identifizierung des Risikofaktors über einen Namen.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0013]/value|value")
  private String bewertungDesGesundheitsrisikosRisikofaktorValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Spezifischer
   * Risikofaktor/Vorhandensein Description: Vorhandensein des Risikofaktors.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0017]/value|defining_code")
  private VorhandenseinDefiningCode2 bewertungDesGesundheitsrisikosVorhandenseinDefiningCode;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Spezifischer
   * Risikofaktor/Details Description: Strukturierte Informationen zu anderen Aspekten der
   * Risikofaktorenbewertung. Comment: Zum Beispiel: Prävalenz des Risikofaktors bei
   * Familienmitgliedern.
   */
  @Path("/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0027]")
  private List<Cluster> bewertungDesGesundheitsrisikosDetails;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Andere
   * Risikofaktoren/Risikofaktor Description: Identifizierung des Risikofaktors über einen Namen.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0013]/value|value")
  private String bewertungDesGesundheitsrisikosRisikofaktorValue2;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Andere
   * Risikofaktoren/Vorhandensein Description: Vorhandensein des Risikofaktors.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0017]/value|defining_code")
  private VorhandenseinDefiningCode2 bewertungDesGesundheitsrisikosVorhandenseinDefiningCode2;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Andere Risikofaktoren/Details
   * Description: Strukturierte Informationen zu anderen Aspekten der Risikofaktorenbewertung.
   * Comment: Zum Beispiel: Prävalenz des Risikofaktors bei Familienmitgliedern.
   */
  @Path("/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0027]")
  private List<Cluster> bewertungDesGesundheitsrisikosDetails2;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Risikobewertung Description:
   * Bewertung des Gesundheitsrisikos.
   */
  @Path("/data[at0001]/items[at0003]/value|value")
  private String risikobewertungValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Letzte Aktualisierung
   * Description: Das Datum an dem die Bewertung des Gesundheitsrisikos zuletzt aktualisiert wurde.
   */
  @Path("/protocol[at0010]/items[at0024]/value|value")
  private TemporalAccessor letzteAktualisierungValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Bewertungsmethode
   * Description: Identifikation des Algorithmus oder der Leitlinie, welche für die Risikobewertung
   * verwendet wurde.
   */
  @Path("/protocol[at0010]/items[at0025]/value|value")
  private String bewertungsmethodeValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Erweiterung Description:
   * Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere
   * Referenzmodelle/Formalismen. Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche
   * Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0010]/items[at0011]")
  private List<Cluster> erweiterung;

  /** Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/language */
  @Path("/language")
  private Language language;

  /** Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setGesundheitsrisikoValue(String gesundheitsrisikoValue) {
    this.gesundheitsrisikoValue = gesundheitsrisikoValue;
  }

  public String getGesundheitsrisikoValue() {
    return this.gesundheitsrisikoValue;
  }

  public void setBewertungDesGesundheitsrisikosRisikofaktorValue(
      String bewertungDesGesundheitsrisikosRisikofaktorValue) {
    this.bewertungDesGesundheitsrisikosRisikofaktorValue =
        bewertungDesGesundheitsrisikosRisikofaktorValue;
  }

  public String getBewertungDesGesundheitsrisikosRisikofaktorValue() {
    return this.bewertungDesGesundheitsrisikosRisikofaktorValue;
  }

  public void setBewertungDesGesundheitsrisikosVorhandenseinDefiningCode(
      VorhandenseinDefiningCode2 bewertungDesGesundheitsrisikosVorhandenseinDefiningCode) {
    this.bewertungDesGesundheitsrisikosVorhandenseinDefiningCode =
        bewertungDesGesundheitsrisikosVorhandenseinDefiningCode;
  }

  public VorhandenseinDefiningCode2 getBewertungDesGesundheitsrisikosVorhandenseinDefiningCode() {
    return this.bewertungDesGesundheitsrisikosVorhandenseinDefiningCode;
  }

  public void setBewertungDesGesundheitsrisikosDetails(
      List<Cluster> bewertungDesGesundheitsrisikosDetails) {
    this.bewertungDesGesundheitsrisikosDetails = bewertungDesGesundheitsrisikosDetails;
  }

  public List<Cluster> getBewertungDesGesundheitsrisikosDetails() {
    return this.bewertungDesGesundheitsrisikosDetails;
  }

  public void setBewertungDesGesundheitsrisikosRisikofaktorValue2(
      String bewertungDesGesundheitsrisikosRisikofaktorValue2) {
    this.bewertungDesGesundheitsrisikosRisikofaktorValue2 =
        bewertungDesGesundheitsrisikosRisikofaktorValue2;
  }

  public String getBewertungDesGesundheitsrisikosRisikofaktorValue2() {
    return this.bewertungDesGesundheitsrisikosRisikofaktorValue2;
  }

  public void setBewertungDesGesundheitsrisikosVorhandenseinDefiningCode2(
      VorhandenseinDefiningCode2 bewertungDesGesundheitsrisikosVorhandenseinDefiningCode2) {
    this.bewertungDesGesundheitsrisikosVorhandenseinDefiningCode2 =
        bewertungDesGesundheitsrisikosVorhandenseinDefiningCode2;
  }

  public VorhandenseinDefiningCode2 getBewertungDesGesundheitsrisikosVorhandenseinDefiningCode2() {
    return this.bewertungDesGesundheitsrisikosVorhandenseinDefiningCode2;
  }

  public void setBewertungDesGesundheitsrisikosDetails2(
      List<Cluster> bewertungDesGesundheitsrisikosDetails2) {
    this.bewertungDesGesundheitsrisikosDetails2 = bewertungDesGesundheitsrisikosDetails2;
  }

  public List<Cluster> getBewertungDesGesundheitsrisikosDetails2() {
    return this.bewertungDesGesundheitsrisikosDetails2;
  }

  public void setRisikobewertungValue(String risikobewertungValue) {
    this.risikobewertungValue = risikobewertungValue;
  }

  public String getRisikobewertungValue() {
    return this.risikobewertungValue;
  }

  public void setLetzteAktualisierungValue(TemporalAccessor letzteAktualisierungValue) {
    this.letzteAktualisierungValue = letzteAktualisierungValue;
  }

  public TemporalAccessor getLetzteAktualisierungValue() {
    return this.letzteAktualisierungValue;
  }

  public void setBewertungsmethodeValue(String bewertungsmethodeValue) {
    this.bewertungsmethodeValue = bewertungsmethodeValue;
  }

  public String getBewertungsmethodeValue() {
    return this.bewertungsmethodeValue;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
    this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
    return this.erweiterung;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
