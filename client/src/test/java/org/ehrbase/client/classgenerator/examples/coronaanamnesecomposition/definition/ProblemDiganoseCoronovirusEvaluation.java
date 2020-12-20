package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.623062200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ProblemDiganoseCoronovirusEvaluation implements EntryEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Name des Problems/ der Diagnose
   * Description: Namentliche Identifikation des Problems oder der Diagnose.
   */
  @Path("/data[at0001]/items[at0002]/value|value")
  private String nameDesProblemsDerDiagnoseValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Anatomische Stelle (strukturiert)
   * Description: Eine strukturierte anatomische Lokalisation des Problems oder der Diagnose.
   * Comment: Verwenden Sie diesen SLOT, um die Archetypen CLUSTER.anatomical_location oder
   * CLUSTER.relative_location einzufügen, wenn die Anforderungen für die Aufnahme der anatomischen
   * Position zur Laufzeit der Anwendung bestimmt werden oder komplexere Modellierungen wie z.B.
   * relative Positionen erforderlich sind. Ist die anatomische Lokalisation über präkoordinierte
   * Codes im Namen des Problems/Diagnose enthalten, wird die Verwendung dieses SLOT überflüssig.
   */
  @Path("/data[at0001]/items[at0039]")
  private List<Cluster> anatomischeStelleStrukturiert;

  /**
   * Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Spezifische Angaben Description:
   * Zusätzlich benötigte Angaben, welche als eindeutige Merkmale des Problem/der Diagnose erfasst
   * werden sollten. Comment: Hier können strukturierte Angaben über die Einstufung oder das Stadium
   * der Diagnose enthalten sein; diagnostische Kriterien, Klassifizierungskriterien oder formale
   * Bewertungen des Schweregrades wie z.B. "Common Terminology Criteria for Adverse Events".
   */
  @Path("/data[at0001]/items[at0043]")
  private List<Cluster> spezifischeAngaben;

  /**
   * Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Status Description: Kontextuelles
   * oder zeitliches Attribut für ein spezifisches Problem oder eine spezifische Diagnose.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v1 and name/value='Status']")
  private StatusCluster status;

  /**
   * Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Kommentar Description: Ergänzende
   * Beschreibung des Problems oder der Diagnose, die nicht anderweitig erfasst wurde.
   */
  @Path("/data[at0001]/items[at0069]/value|value")
  private String kommentarValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Zuletzt aktualisiert Description:
   * Datum der letzten Aktualisierung der Diagnose bzw. des Problems.
   */
  @Path("/protocol[at0032]/items[at0070]/value|value")
  private TemporalAccessor zuletztAktualisiertValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Erweiterung Description:
   * Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere
   * Referenzmodelle/Formalismen. Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche
   * Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0032]/items[at0071]")
  private List<Cluster> erweiterung;

  /** Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/language */
  @Path("/language")
  private Language language;

  /** Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/value */
  @Path("/data[at0001]/items[at0073]/value")
  @Choice
  private ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice diagnostischeSicherheit;

  public void setNameDesProblemsDerDiagnoseValue(String nameDesProblemsDerDiagnoseValue) {
    this.nameDesProblemsDerDiagnoseValue = nameDesProblemsDerDiagnoseValue;
  }

  public String getNameDesProblemsDerDiagnoseValue() {
    return this.nameDesProblemsDerDiagnoseValue;
  }

  public void setAnatomischeStelleStrukturiert(List<Cluster> anatomischeStelleStrukturiert) {
    this.anatomischeStelleStrukturiert = anatomischeStelleStrukturiert;
  }

  public List<Cluster> getAnatomischeStelleStrukturiert() {
    return this.anatomischeStelleStrukturiert;
  }

  public void setSpezifischeAngaben(List<Cluster> spezifischeAngaben) {
    this.spezifischeAngaben = spezifischeAngaben;
  }

  public List<Cluster> getSpezifischeAngaben() {
    return this.spezifischeAngaben;
  }

  public void setStatus(StatusCluster status) {
    this.status = status;
  }

  public StatusCluster getStatus() {
    return this.status;
  }

  public void setKommentarValue(String kommentarValue) {
    this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
    return this.kommentarValue;
  }

  public void setZuletztAktualisiertValue(TemporalAccessor zuletztAktualisiertValue) {
    this.zuletztAktualisiertValue = zuletztAktualisiertValue;
  }

  public TemporalAccessor getZuletztAktualisiertValue() {
    return this.zuletztAktualisiertValue;
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

  public void setDiagnostischeSicherheit(
      ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice diagnostischeSicherheit) {
    this.diagnostischeSicherheit = diagnostischeSicherheit;
  }

  public ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice getDiagnostischeSicherheit() {
    return this.diagnostischeSicherheit;
  }
}
