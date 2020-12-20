package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.laboratory_test_result.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.091498+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class LaborergebnisObservation implements EntryEntity {
  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Labortest-Bezeichnung Description:
   * Name der Laboruntersuchung, die an der/den Probe(n) durchgeführt wurde.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|defining_code")
  private LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Probendetail Description: Angaben
   * über die Beschaffenheit der analysierten Probe. Comment: Wenn der Probentyp mit einem Code in
   * der Testbezeichnung ausreichend spezifiziert ist, sind diese zusätzlichen Daten nicht
   * erforderlich. Die Verknüpfung von Ergebnissen mit bestimmten Proben kann sowohl in einem
   * CLUSTER.Probe als auch in den verschiedenen CLUSTER Archetypen mit Hilfe von Elementen mit der
   * Bezeichnung "Probe" dokumentiert werden.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0065]")
  private List<Cluster> probendetail;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Kohlendioxidpartialdruck
   * Description: Ergebnis eines Labortests für einen bestimmten Analytwert. Comment: Beispiele:
   * 'Natrium', 'Leukozytenzahl', 'T3'. Üblicherweise über eine externe Terminologie codiert.
   */
  @Path(
      "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Kohlendioxidpartialdruck']")
  private KohlendioxidpartialdruckCluster kohlendioxidpartialdruck;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffpartialdruck
   * Description: Ergebnis eines Labortests für einen bestimmten Analytwert. Comment: Beispiele:
   * 'Natrium', 'Leukozytenzahl', 'T3'. Üblicherweise über eine externe Terminologie codiert.
   */
  @Path(
      "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Sauerstoffpartialdruck']")
  private SauerstoffpartialdruckCluster sauerstoffpartialdruck;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert Description: Ergebnis
   * eines Labortests für einen bestimmten Analytwert. Comment: Beispiele: 'Natrium',
   * 'Leukozytenzahl', 'T3'. Üblicherweise über eine externe Terminologie codiert.
   */
  @Path(
      "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='pH-Wert']")
  private PhWertCluster phWert;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung Description:
   * Ergebnis eines Labortests für einen bestimmten Analytwert. Comment: Beispiele: 'Natrium',
   * 'Leukozytenzahl', 'T3'. Üblicherweise über eine externe Terminologie codiert.
   */
  @Path(
      "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Sauerstoffsättigung']")
  private SauerstoffsattigungCluster sauerstoffsattigung;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Strukturierte Testdiagnostik
   * Description: Eine strukturierte oder komplexe Diagnose für die Laboruntersuchung. Comment: Zum
   * Beispiel: Anatomische Pathologiediagnosen, die aus mehreren verschiedenen Schwerpunkten wie
   * Morphologie, Ätiologie und Funktion zusammengesetzt sind.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0122]")
  private List<Cluster> strukturierteTestdiagnostik;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Multimedia-Darstellung
   * Description: Bild, Video oder Diagramm zur Visualisierung des Testergebnisses. Comment: Mehrere
   * Formate sind erlaubt - diese sollten aber einen äquivalenten klinischen Inhalt darstellen.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0118]")
  private List<Cluster> multimediaDarstellung;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Strukturierte Erfassung der
   * Störfaktoren Description: Einzelheiten zu Problemen oder Umständen, die sich auf die genaue
   * Interpretation der Messung oder des Prüfergebnisses auswirken. Comment: Zum Beispiel: Letzte
   * normale Menstruationsperiode (LNMP).
   */
  @Path("/data[at0001]/events[at0002]/state[at0112]/items[at0114]")
  private List<Cluster> strukturierteErfassungDerStorfaktoren;

  /** Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Path: Befund der Blutgasanalyse/Laborergebnis/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Labor, welches den Untersuchungsauftrag annimmt
   * Description: Angaben zu dem Labor, das die Anfrage erhalten hat und die Hauptverantwortung für
   * die Verwaltung der Berichterstattung über den Test trägt, auch wenn andere Labore bestimmte
   * Aspekte ausführen. Comment: Dieser Slot gibt die Details des Labors an, dass die Anforderung
   * erhalten hat und die Gesamtverantwortung für die Berichterstellung des Tests trägt, selbst wenn
   * andere Labore bestimmte Aspekte ausführen.
   *
   * <p>Das Empfangslabor kann den Test entweder durchführen oder an ein anderes Labor verweisen.
   * Wenn ein anderes Labor für die Durchführung der Tests mit bestimmten Analyten zuständig ist,
   * ist zu erwarten, dass diese Details im SLOT 'Analyte result detail' innerhalb des Archetyps
   * CLUSTER.laboratory_test_analyte enthalten sind.
   */
  @Path("/protocol[at0004]/items[at0017]")
  private Cluster laborWelchesDenUntersuchungsauftragAnnimmt;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Test Details Description: Strukturierte Details
   * über die beim Labortest verwendete Methodik, das Gerät oder die Auswertung. Comment: Zum
   * Beispiel: "Details der ELISA/Nephelometrie".
   */
  @Path("/protocol[at0004]/items[at0110]")
  private List<Cluster> testDetails;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Erweiterung Description: Weitere Informationen,
   * die erforderlich sind, um lokale Inhalte abzubilden oder das Modell an andere Referenzmodelle
   * anzupassen. Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten, um
   * ein Mapping auf FHIR oder CIMI Modelle zu ermöglichen.
   */
  @Path("/protocol[at0004]/items[at0117]")
  private List<Cluster> erweiterung;

  /** Path: Befund der Blutgasanalyse/Laborergebnis/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Befund der Blutgasanalyse/Laborergebnis/language */
  @Path("/language")
  private Language language;

  /** Path: Befund der Blutgasanalyse/Laborergebnis/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setLabortestBezeichnungDefiningCode(
      LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode) {
    this.labortestBezeichnungDefiningCode = labortestBezeichnungDefiningCode;
  }

  public LabortestBezeichnungDefiningCode getLabortestBezeichnungDefiningCode() {
    return this.labortestBezeichnungDefiningCode;
  }

  public void setProbendetail(List<Cluster> probendetail) {
    this.probendetail = probendetail;
  }

  public List<Cluster> getProbendetail() {
    return this.probendetail;
  }

  public void setKohlendioxidpartialdruck(
      KohlendioxidpartialdruckCluster kohlendioxidpartialdruck) {
    this.kohlendioxidpartialdruck = kohlendioxidpartialdruck;
  }

  public KohlendioxidpartialdruckCluster getKohlendioxidpartialdruck() {
    return this.kohlendioxidpartialdruck;
  }

  public void setSauerstoffpartialdruck(SauerstoffpartialdruckCluster sauerstoffpartialdruck) {
    this.sauerstoffpartialdruck = sauerstoffpartialdruck;
  }

  public SauerstoffpartialdruckCluster getSauerstoffpartialdruck() {
    return this.sauerstoffpartialdruck;
  }

  public void setPhWert(PhWertCluster phWert) {
    this.phWert = phWert;
  }

  public PhWertCluster getPhWert() {
    return this.phWert;
  }

  public void setSauerstoffsattigung(SauerstoffsattigungCluster sauerstoffsattigung) {
    this.sauerstoffsattigung = sauerstoffsattigung;
  }

  public SauerstoffsattigungCluster getSauerstoffsattigung() {
    return this.sauerstoffsattigung;
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

  public void setLaborWelchesDenUntersuchungsauftragAnnimmt(
      Cluster laborWelchesDenUntersuchungsauftragAnnimmt) {
    this.laborWelchesDenUntersuchungsauftragAnnimmt = laborWelchesDenUntersuchungsauftragAnnimmt;
  }

  public Cluster getLaborWelchesDenUntersuchungsauftragAnnimmt() {
    return this.laborWelchesDenUntersuchungsauftragAnnimmt;
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
