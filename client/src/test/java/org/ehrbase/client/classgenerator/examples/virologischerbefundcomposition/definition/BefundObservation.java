package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
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
@Archetype("openEHR-EHR-OBSERVATION.laboratory_test_result.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.761035+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BefundObservation implements EntryEntity {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Labortest-Bezeichnung Description: Name der
   * Laboruntersuchung, die an der/den Probe(n) durchgeführt wurde.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value")
  private String labortestBezeichnungValue;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe Description: Eine physikalische Probe
   * zur Erforschung, Untersuchung oder Analyse, die von einer Person entnommen wurde oder die sich
   * auf die Person bezieht. Comment: Zum Beispiel: Gewebe, Körperflüssigkeit oder Lebensmittel.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.specimen.v1]")
  private List<ProbeCluster> probe;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur Description: Laborergebnis als
   * Panel/Profil von Einzelresultaten. Verbreitet im medizinischen Labor.
   */
  @Path(
      "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0 and name/value='Kultur']")
  private List<KulturCluster> kultur;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Strukturierte Testdiagnostik Description: Eine
   * strukturierte oder komplexe Diagnose für die Laboruntersuchung. Comment: Zum Beispiel:
   * Anatomische Pathologiediagnosen, die aus mehreren verschiedenen Schwerpunkten wie Morphologie,
   * Ätiologie und Funktion zusammengesetzt sind.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0122]")
  private List<Cluster> strukturierteTestdiagnostik;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Multimedia-Darstellung Description: Bild,
   * Video oder Diagramm zur Visualisierung des Testergebnisses. Comment: Mehrere Formate sind
   * erlaubt - diese sollten aber einen äquivalenten klinischen Inhalt darstellen.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0118]")
  private List<Cluster> multimediaDarstellung;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Strukturierte Erfassung der Störfaktoren
   * Description: Einzelheiten zu Problemen oder Umständen, die sich auf die genaue Interpretation
   * der Messung oder des Prüfergebnisses auswirken. Comment: Zum Beispiel: Letzte normale
   * Menstruationsperiode (LNMP).
   */
  @Path("/data[at0001]/events[at0002]/state[at0112]/items[at0114]")
  private List<Cluster> strukturierteErfassungDerStorfaktoren;

  /** Path: Virologischer Befund/Befund/Jedes Ereignis/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Path: Virologischer Befund/Befund/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Virologischer Befund/Befund/Empfängerstandort Description: Standort umfasst sowohl
   * beiläufige Orte (ein Ort, der für die medizinische Versorgung ohne vorherige Benennung oder
   * Genehmigung genutzt wird) als auch spezielle, offiziell benannte Orte. Die Standorte können
   * privat, öffentlich, mobil oder stationär sein.
   */
  @Path(
      "/protocol[at0004]/items[openEHR-EHR-CLUSTER.location.v1 and name/value='Empfängerstandort']")
  private EmpfangerstandortCluster empfangerstandort;

  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Anforderung Description: Name des
   * ursprünglich angeforderten Tests. Comment: Dieses Datenelement ist zu verwenden, wenn die
   * angeforderte Testung von der tatsächlich vom Labor durchgeführten Testung abweicht.
   */
  @Path("/protocol[at0004]/items[at0094]/items[at0106 and name/value='Anforderung']")
  private List<BefundAnforderungElement> anforderung;

  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Auftrags-ID des
   * anfordernden/einsendenden Systems Description: Lokale Auftrags-ID des anfordernden/einsendenden
   * Systems.
   */
  @Path("/protocol[at0004]/items[at0094]/items[at0062]/value")
  private DvIdentifier auftragsIdDesAnforderndenEinsendendenSystems;

  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Auftrags-ID (Empfänger)
   * Description: Lokale Auftrags-ID, die vom auftragsempfangendem System, gewöhnlich dem
   * Laborinformationssystem (LIS) zugewiesen wird.
   */
  @Path("/protocol[at0004]/items[at0094]/items[at0063]/value")
  private DvIdentifier auftragsIdEmpfanger;

  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Einsenderstandort Description:
   * Standort umfasst sowohl beiläufige Orte (ein Ort, der für die medizinische Versorgung ohne
   * vorherige Benennung oder Genehmigung genutzt wird) als auch spezielle, offiziell benannte Orte.
   * Die Standorte können privat, öffentlich, mobil oder stationär sein.
   */
  @Path(
      "/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1 and name/value='Einsenderstandort']")
  private EinsenderstandortCluster einsenderstandort;

  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Verteilerliste Description:
   * Details über weitere Kliniker oder Organisationen, die eine Kopie der Analyseergebnisse
   * benötigen. Comment: Die "Verteilerliste" dient nur zu Informationszwecken. Der Hauptempfänger
   * des Berichts ist die Person, die dazu bestimmt ist, auf die Information zu reagieren.
   */
  @Path("/protocol[at0004]/items[at0094]/items[at0035]")
  private List<Cluster> verteilerliste;

  /**
   * Path: Virologischer Befund/Befund/Test Details Description: Strukturierte Details über die beim
   * Labortest verwendete Methodik, das Gerät oder die Auswertung. Comment: Zum Beispiel: "Details
   * der ELISA/Nephelometrie".
   */
  @Path("/protocol[at0004]/items[at0110]")
  private List<Cluster> testDetails;

  /**
   * Path: Virologischer Befund/Befund/Erweiterung Description: Weitere Informationen, die
   * erforderlich sind, um lokale Inhalte abzubilden oder das Modell an andere Referenzmodelle
   * anzupassen. Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten, um
   * ein Mapping auf FHIR oder CIMI Modelle zu ermöglichen.
   */
  @Path("/protocol[at0004]/items[at0117]")
  private List<Cluster> erweiterung;

  /** Path: Virologischer Befund/Befund/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Virologischer Befund/Befund/language */
  @Path("/language")
  private Language language;

  /** Path: Virologischer Befund/Befund/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setLabortestBezeichnungValue(String labortestBezeichnungValue) {
    this.labortestBezeichnungValue = labortestBezeichnungValue;
  }

  public String getLabortestBezeichnungValue() {
    return this.labortestBezeichnungValue;
  }

  public void setProbe(List<ProbeCluster> probe) {
    this.probe = probe;
  }

  public List<ProbeCluster> getProbe() {
    return this.probe;
  }

  public void setKultur(List<KulturCluster> kultur) {
    this.kultur = kultur;
  }

  public List<KulturCluster> getKultur() {
    return this.kultur;
  }

  public void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik) {
    this.strukturierteTestdiagnostik = strukturierteTestdiagnostik;
  }

  public List<Cluster> getStrukturierteTestdiagnostik() {
    return this.strukturierteTestdiagnostik;
  }

  public void setMultimediaDarstellung(List<Cluster> multimediaDarstellung) {
    this.multimediaDarstellung = multimediaDarstellung;
  }

  public List<Cluster> getMultimediaDarstellung() {
    return this.multimediaDarstellung;
  }

  public void setStrukturierteErfassungDerStorfaktoren(
      List<Cluster> strukturierteErfassungDerStorfaktoren) {
    this.strukturierteErfassungDerStorfaktoren = strukturierteErfassungDerStorfaktoren;
  }

  public List<Cluster> getStrukturierteErfassungDerStorfaktoren() {
    return this.strukturierteErfassungDerStorfaktoren;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setOriginValue(TemporalAccessor originValue) {
    this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
    return this.originValue;
  }

  public void setEmpfangerstandort(EmpfangerstandortCluster empfangerstandort) {
    this.empfangerstandort = empfangerstandort;
  }

  public EmpfangerstandortCluster getEmpfangerstandort() {
    return this.empfangerstandort;
  }

  public void setAnforderung(List<BefundAnforderungElement> anforderung) {
    this.anforderung = anforderung;
  }

  public List<BefundAnforderungElement> getAnforderung() {
    return this.anforderung;
  }

  public void setAuftragsIdDesAnforderndenEinsendendenSystems(
      DvIdentifier auftragsIdDesAnforderndenEinsendendenSystems) {
    this.auftragsIdDesAnforderndenEinsendendenSystems =
        auftragsIdDesAnforderndenEinsendendenSystems;
  }

  public DvIdentifier getAuftragsIdDesAnforderndenEinsendendenSystems() {
    return this.auftragsIdDesAnforderndenEinsendendenSystems;
  }

  public void setAuftragsIdEmpfanger(DvIdentifier auftragsIdEmpfanger) {
    this.auftragsIdEmpfanger = auftragsIdEmpfanger;
  }

  public DvIdentifier getAuftragsIdEmpfanger() {
    return this.auftragsIdEmpfanger;
  }

  public void setEinsenderstandort(EinsenderstandortCluster einsenderstandort) {
    this.einsenderstandort = einsenderstandort;
  }

  public EinsenderstandortCluster getEinsenderstandort() {
    return this.einsenderstandort;
  }

  public void setVerteilerliste(List<Cluster> verteilerliste) {
    this.verteilerliste = verteilerliste;
  }

  public List<Cluster> getVerteilerliste() {
    return this.verteilerliste;
  }

  public void setTestDetails(List<Cluster> testDetails) {
    this.testDetails = testDetails;
  }

  public List<Cluster> getTestDetails() {
    return this.testDetails;
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
