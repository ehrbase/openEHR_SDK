package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

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
@Archetype("openEHR-EHR-EVALUATION.problem_diagnosis.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.343026900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ProblemDiagnoseEvaluation implements EntryEntity {
  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Name des Problems/ der Diagnose Description:
   * Namentliche Identifikation des Problems oder der Diagnose.
   */
  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private NameDesProblemsDerDiagnoseDefiningCode nameDesProblemsDerDiagnoseDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Freitextbeschreibung Description: Beschreibung des
   * Problems oder der Diagnose.
   */
  @Path("/data[at0001]/items[at0009 and name/value='Freitextbeschreibung']/value|value")
  private String freitextbeschreibungValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Lokalisation Description: Identifikation einer
   * einfachen Körperstelle zur Lokalisierung des Problems oder der Diagnose.
   */
  @Path("/data[at0001]/items[at0012 and name/value='Lokalisation']/value|value")
  private String lokalisationValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Anatomische Lokalisation Description: Eine physische
   * Stelle am oder innerhalb des menschlichen Körpers.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1]")
  private List<AnatomischeLokalisationCluster> anatomischeLokalisation;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Datum des Auftretens/der Erstdiagnose Description:
   * Geschätzte oder exakte Zeit (bzw. Datum), zu der die Krankheitsanzeichen oder Symptome zum
   * ersten mal beobachtet wurden.
   */
  @Path(
      "/data[at0001]/items[at0077 and name/value='Datum des Auftretens/der Erstdiagnose']/value|value")
  private TemporalAccessor datumDesAuftretensDerErstdiagnoseValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Feststellungsdatum Description: Geschätzte oder exakte
   * Zeit (bzw. Datum), zu der die Diagnose oder das Problem von einer medizinischen Fachkraft
   * festgestellt wurde.
   */
  @Path("/data[at0001]/items[at0003 and name/value='Feststellungsdatum']/value|value")
  private TemporalAccessor feststellungsdatumValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails Description: Diagnosebezogene
   * Informationen.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.diagnose_details.v0]")
  private DiagnosedetailsCluster diagnosedetails;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese Description: Die Ursachen, Gründe oder
   * Erklärung für das Entstehen eines bestimmten Problems/einer Diagnose, dessen/deren auslösenden
   * Faktoren und Entwicklung.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.etiology.v1]")
  private AtiopathogeneseCluster atiopathogenese;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Datum/Zeitpunkt der Genesung Description: Geschätzte
   * oder exakte Zeit (bzw. Datum), zu der von einer medizinischen Fachkraft die Genesung oder die
   * Remission des Problems oder der Diagnose festgestellt wurde.
   */
  @Path("/data[at0001]/items[at0030]/value|value")
  private TemporalAccessor datumZeitpunktDerGenesungValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Status Description: Strukturierte Angaben zu
   * standort-, domänen-, episoden- oder workflow-spezifischen Aspekten des diagnostischen
   * Prozesses. Comment: Verwenden Sie Status- oder Kontext-Merkmale mit Vorsicht, da sie in der
   * Praxis variabel eingesetzt werden und die Interoperabilität nicht gewährleistet werden kann,
   * sofern die Verwendung nicht mit der Nutzungsgemeinschaft klar abgestimmt wird. Beispiel:
   * aktiver Status - aktiv, inaktiv, genesen, in Remission; Entwicklungsstatus - initial,
   * interim/working, final; zeitlicher Status - aktuell, vergangen; Episodenstatus - erstmalig,
   * neu, laufend; Aufnahmestatus - Aufnahme, Entlassung; oder Prioritätsstatus - primär, sekundär.
   */
  @Path("/data[at0001]/items[at0046]")
  private List<Cluster> status;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnoseerläuterung Description: Ergänzende
   * Beschreibung des Problems oder der Diagnose, die nicht anderweitig erfasst wurde.
   */
  @Path("/data[at0001]/items[at0069 and name/value='Diagnoseerläuterung']/value|value")
  private String diagnoseerlauterungValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Letztes Dokumentationsdatum Description: Datum der
   * letzten Aktualisierung der Diagnose bzw. des Problems.
   */
  @Path("/protocol[at0032]/items[at0070 and name/value='Letztes Dokumentationsdatum']/value|value")
  private TemporalAccessor letztesDokumentationsdatumValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Erweiterung Description: Zusätzliche Informationen zur
   * Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen. Comment: Zum
   * Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an
   * FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0032]/items[at0071]")
  private List<Cluster> erweiterung;

  /** Path: COVID-19-Diagnose/Problem/Diagnose/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: COVID-19-Diagnose/Problem/Diagnose/language */
  @Path("/language")
  private Language language;

  /** Path: COVID-19-Diagnose/Problem/Diagnose/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: COVID-19-Diagnose/Problem/Diagnose/value */
  @Path("/data[at0001]/items[at0073]/value")
  @Choice
  private ProblemDiagnoseDiagnostischeSicherheitChoice diagnostischeSicherheit;

  /** Path: COVID-19-Diagnose/Problem/Diagnose/value */
  @Path("/data[at0001]/items[at0005]/value")
  @Choice
  private ProblemDiagnoseSchweregradChoice schweregrad;

  public void setNameDesProblemsDerDiagnoseDefiningCode(
      NameDesProblemsDerDiagnoseDefiningCode nameDesProblemsDerDiagnoseDefiningCode) {
    this.nameDesProblemsDerDiagnoseDefiningCode = nameDesProblemsDerDiagnoseDefiningCode;
  }

  public NameDesProblemsDerDiagnoseDefiningCode getNameDesProblemsDerDiagnoseDefiningCode() {
    return this.nameDesProblemsDerDiagnoseDefiningCode;
  }

  public void setFreitextbeschreibungValue(String freitextbeschreibungValue) {
    this.freitextbeschreibungValue = freitextbeschreibungValue;
  }

  public String getFreitextbeschreibungValue() {
    return this.freitextbeschreibungValue;
  }

  public void setLokalisationValue(String lokalisationValue) {
    this.lokalisationValue = lokalisationValue;
  }

  public String getLokalisationValue() {
    return this.lokalisationValue;
  }

  public void setAnatomischeLokalisation(
      List<AnatomischeLokalisationCluster> anatomischeLokalisation) {
    this.anatomischeLokalisation = anatomischeLokalisation;
  }

  public List<AnatomischeLokalisationCluster> getAnatomischeLokalisation() {
    return this.anatomischeLokalisation;
  }

  public void setDatumDesAuftretensDerErstdiagnoseValue(
      TemporalAccessor datumDesAuftretensDerErstdiagnoseValue) {
    this.datumDesAuftretensDerErstdiagnoseValue = datumDesAuftretensDerErstdiagnoseValue;
  }

  public TemporalAccessor getDatumDesAuftretensDerErstdiagnoseValue() {
    return this.datumDesAuftretensDerErstdiagnoseValue;
  }

  public void setFeststellungsdatumValue(TemporalAccessor feststellungsdatumValue) {
    this.feststellungsdatumValue = feststellungsdatumValue;
  }

  public TemporalAccessor getFeststellungsdatumValue() {
    return this.feststellungsdatumValue;
  }

  public void setDiagnosedetails(DiagnosedetailsCluster diagnosedetails) {
    this.diagnosedetails = diagnosedetails;
  }

  public DiagnosedetailsCluster getDiagnosedetails() {
    return this.diagnosedetails;
  }

  public void setAtiopathogenese(AtiopathogeneseCluster atiopathogenese) {
    this.atiopathogenese = atiopathogenese;
  }

  public AtiopathogeneseCluster getAtiopathogenese() {
    return this.atiopathogenese;
  }

  public void setDatumZeitpunktDerGenesungValue(TemporalAccessor datumZeitpunktDerGenesungValue) {
    this.datumZeitpunktDerGenesungValue = datumZeitpunktDerGenesungValue;
  }

  public TemporalAccessor getDatumZeitpunktDerGenesungValue() {
    return this.datumZeitpunktDerGenesungValue;
  }

  public void setStatus(List<Cluster> status) {
    this.status = status;
  }

  public List<Cluster> getStatus() {
    return this.status;
  }

  public void setDiagnoseerlauterungValue(String diagnoseerlauterungValue) {
    this.diagnoseerlauterungValue = diagnoseerlauterungValue;
  }

  public String getDiagnoseerlauterungValue() {
    return this.diagnoseerlauterungValue;
  }

  public void setLetztesDokumentationsdatumValue(TemporalAccessor letztesDokumentationsdatumValue) {
    this.letztesDokumentationsdatumValue = letztesDokumentationsdatumValue;
  }

  public TemporalAccessor getLetztesDokumentationsdatumValue() {
    return this.letztesDokumentationsdatumValue;
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
      ProblemDiagnoseDiagnostischeSicherheitChoice diagnostischeSicherheit) {
    this.diagnostischeSicherheit = diagnostischeSicherheit;
  }

  public ProblemDiagnoseDiagnostischeSicherheitChoice getDiagnostischeSicherheit() {
    return this.diagnostischeSicherheit;
  }

  public void setSchweregrad(ProblemDiagnoseSchweregradChoice schweregrad) {
    this.schweregrad = schweregrad;
  }

  public ProblemDiagnoseSchweregradChoice getSchweregrad() {
    return this.schweregrad;
  }
}
