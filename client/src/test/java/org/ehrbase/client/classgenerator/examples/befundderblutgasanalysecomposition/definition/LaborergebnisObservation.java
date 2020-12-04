package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.laboratory_test_result.v1")
public class LaborergebnisObservation {
  /**
   * Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Labortest-Bezeichnung
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|defining_code")
  private LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Probendetail
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0065]")
  private List<Cluster> probendetail;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Kohlendioxidpartialdruck
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Kohlendioxidpartialdruck']")
  private KohlendioxidpartialdruckCluster kohlendioxidpartialdruck;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffpartialdruck
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Sauerstoffpartialdruck']")
  private SauerstoffpartialdruckCluster sauerstoffpartialdruck;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='pH-Wert']")
  private PhWertCluster phWert;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Sauerstoffsättigung']")
  private SauerstoffsattigungCluster sauerstoffsattigung;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Strukturierte Testdiagnostik
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0122]")
  private List<Cluster> strukturierteTestdiagnostik;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Multimedia-Darstellung
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0118]")
  private List<Cluster> multimediaDarstellung;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Strukturierte Erfassung der Störfaktoren
   */
  @Path("/data[at0001]/events[at0002]/state[at0112]/items[at0114]")
  private List<Cluster> strukturierteErfassungDerStorfaktoren;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Labor, welches den Untersuchungsauftrag annimmt
   */
  @Path("/protocol[at0004]/items[at0017]")
  private Cluster laborWelchesDenUntersuchungsauftragAnnimmt;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Test Details
   */
  @Path("/protocol[at0004]/items[at0110]")
  private List<Cluster> testDetails;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/Erweiterung
   */
  @Path("/protocol[at0004]/items[at0117]")
  private List<Cluster> erweiterung;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/language
   */
  @Path("/language")
  private Language language;

  /**
   * Befund der Blutgasanalyse/Laborergebnis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setLabortestBezeichnungDefiningCode(
      LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode) {
     this.labortestBezeichnungDefiningCode = labortestBezeichnungDefiningCode;
  }

  public LabortestBezeichnungDefiningCode getLabortestBezeichnungDefiningCode() {
     return this.labortestBezeichnungDefiningCode ;
  }

  public void setProbendetail(List<Cluster> probendetail) {
     this.probendetail = probendetail;
  }

  public List<Cluster> getProbendetail() {
     return this.probendetail ;
  }

  public void setKohlendioxidpartialdruck(
      KohlendioxidpartialdruckCluster kohlendioxidpartialdruck) {
     this.kohlendioxidpartialdruck = kohlendioxidpartialdruck;
  }

  public KohlendioxidpartialdruckCluster getKohlendioxidpartialdruck() {
     return this.kohlendioxidpartialdruck ;
  }

  public void setSauerstoffpartialdruck(SauerstoffpartialdruckCluster sauerstoffpartialdruck) {
     this.sauerstoffpartialdruck = sauerstoffpartialdruck;
  }

  public SauerstoffpartialdruckCluster getSauerstoffpartialdruck() {
     return this.sauerstoffpartialdruck ;
  }

  public void setPhWert(PhWertCluster phWert) {
     this.phWert = phWert;
  }

  public PhWertCluster getPhWert() {
     return this.phWert ;
  }

  public void setSauerstoffsattigung(SauerstoffsattigungCluster sauerstoffsattigung) {
     this.sauerstoffsattigung = sauerstoffsattigung;
  }

  public SauerstoffsattigungCluster getSauerstoffsattigung() {
     return this.sauerstoffsattigung ;
  }

  public void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik) {
     this.strukturierteTestdiagnostik = strukturierteTestdiagnostik;
  }

  public List<Cluster> getStrukturierteTestdiagnostik() {
     return this.strukturierteTestdiagnostik ;
  }

  public void setMultimediaDarstellung(List<Cluster> multimediaDarstellung) {
     this.multimediaDarstellung = multimediaDarstellung;
  }

  public List<Cluster> getMultimediaDarstellung() {
     return this.multimediaDarstellung ;
  }

  public void setStrukturierteErfassungDerStorfaktoren(
      List<Cluster> strukturierteErfassungDerStorfaktoren) {
     this.strukturierteErfassungDerStorfaktoren = strukturierteErfassungDerStorfaktoren;
  }

  public List<Cluster> getStrukturierteErfassungDerStorfaktoren() {
     return this.strukturierteErfassungDerStorfaktoren ;
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

  public void setLaborWelchesDenUntersuchungsauftragAnnimmt(
      Cluster laborWelchesDenUntersuchungsauftragAnnimmt) {
     this.laborWelchesDenUntersuchungsauftragAnnimmt = laborWelchesDenUntersuchungsauftragAnnimmt;
  }

  public Cluster getLaborWelchesDenUntersuchungsauftragAnnimmt() {
     return this.laborWelchesDenUntersuchungsauftragAnnimmt ;
  }

  public void setTestDetails(List<Cluster> testDetails) {
     this.testDetails = testDetails;
  }

  public List<Cluster> getTestDetails() {
     return this.testDetails ;
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
