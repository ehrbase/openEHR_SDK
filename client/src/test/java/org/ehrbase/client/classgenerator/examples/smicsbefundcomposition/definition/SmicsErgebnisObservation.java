package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.smics_befund.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.117022900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class SmicsErgebnisObservation implements EntryEntity {
  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/SmICS Ergebniskategorie Description:
   * Dokumentation über die Angabe ob es eine Häufung oder einen Ausbruch gab.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code")
  private SmicsErgebniskategorieDefiningCode smicsErgebniskategorieDefiningCode;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Multispezies Description: Multispezies
   * Ausbrüche sind gekennzeichnet durch die Übertragung von bestimmten mobilen genetischen
   * Resistenz Elementen. Angaben von Ausbruch und Häufungen von mehreren Erregern.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value")
  private Boolean multispeziesValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Beginn Description: Zeitpunkt an dem der
   * Ausbruchsbeginn stattfand.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value|value")
  private TemporalAccessor beginnValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Beginn der Untersuchung Description: Zeitpunkt
   * an dem die Ausbruchsuntersuchung begann.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|value")
  private TemporalAccessor beginnDerUntersuchungValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Ende Description: Zeitpunkt an dem der
   * Ausbruch beendet wurde.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|value")
  private TemporalAccessor endeValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Dauer Description: Die Gesamtdauer des
   * Ausbruches.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0009]/value|value")
  private TemporalAmount dauerValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort Description: Standort umfasst sowohl
   * beiläufige Orte (ein Ort, der für die medizinische Versorgung ohne vorherige Benennung oder
   * Genehmigung genutzt wird) als auch spezielle, offiziell benannte Orte. Die Standorte können
   * privat, öffentlich, mobil oder stationär sein.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.location.v1]")
  private List<StandortCluster> standort;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Kommentar Description: Zusätzliche
   * Infomationen zum Ausbruch bzw. zu der Häufung.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value|value")
  private String baumKommentarValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Art der Übertragung/Übertragungsweg
   * Description: Angaben zur Expositionsmethode. Die indirekte Infektions/ Erregerübertragung zb.
   * Patient zu Patient, Personal auf Patient o. Punktquelle der Umwelt usw.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0014]/value|value")
  private String ubertragungswegValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Art der Übertragung/Transmissionsweg
   * Description: Der Transmissionsweg des Erregers. Die direkte Infektions/ Erregerübertragung
   * mittels verschiedener Vehikel zb. Kontaktübetragung, Aerogene Übertragung o.
   * Tröpfchenübertragung usw ohne Zwischenschritte.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0013]/value|value")
  private String transmissionswegValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Art der Übertragung/Kommentar Description:
   * Angaben über weitere zusätzliche Übertragungswege.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0038]/value|value")
  private String artDerUbertragungKommentarValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregername Description: Eine Erregerhäufung bzw. Ausbruch von mehrern involvierten
   * Spezies.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0016]/value|defining_code")
  private ErregernameDefiningCode erregernameDefiningCode;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Anzahl
   * der Erregernachweise Description: Die Anzahl der nachgewiesenen Erreger.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0037]")
  private List<SmicsErgebnisAnzahlDerErregernachweiseElement> anzahlDerErregernachweise;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails Description: Detaillierte Angaben zum nachgewiesenen Erreger.
   */
  @Path(
      "/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[openEHR-EHR-CLUSTER.erregerdetails.v1]")
  private ErregerdetailsCluster erregerdetails;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregertypisierung Description: Zur Erfassung von Details zur Molekularen Typisierung,
   * welches eine Differenzierung zwischen gleichen und unterschiedlichen Erregern darstellt.
   */
  @Path(
      "/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[openEHR-EHR-CLUSTER.molekulare_typisierung.v0 and name/value='Erregertypisierung']")
  private ErregertypisierungCluster erregertypisierung;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Zeitpunkt des ersten Erregernachweises Description: Zeitpunkt an dem der erste
   * Erregernachweis festgestellt wurde.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0021]/value|value")
  private TemporalAccessor zeitpunktDesErstenErregernachweisesValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Zeitpunkt des letzten Erregernachweises Description: Zeitpunkt an dem der letzte
   * Erregernachweis festgestellt wurde.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0020]/value|value")
  private TemporalAccessor zeitpunktDesLetztenErregernachweisesValue;

  /** Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Path: SmICS Befund/SmICS-Ergebnis/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /** Path: SmICS Befund/SmICS-Ergebnis/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: SmICS Befund/SmICS-Ergebnis/language */
  @Path("/language")
  private Language language;

  /** Path: SmICS Befund/SmICS-Ergebnis/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSmicsErgebniskategorieDefiningCode(
      SmicsErgebniskategorieDefiningCode smicsErgebniskategorieDefiningCode) {
    this.smicsErgebniskategorieDefiningCode = smicsErgebniskategorieDefiningCode;
  }

  public SmicsErgebniskategorieDefiningCode getSmicsErgebniskategorieDefiningCode() {
    return this.smicsErgebniskategorieDefiningCode;
  }

  public void setMultispeziesValue(Boolean multispeziesValue) {
    this.multispeziesValue = multispeziesValue;
  }

  public Boolean isMultispeziesValue() {
    return this.multispeziesValue;
  }

  public void setBeginnValue(TemporalAccessor beginnValue) {
    this.beginnValue = beginnValue;
  }

  public TemporalAccessor getBeginnValue() {
    return this.beginnValue;
  }

  public void setBeginnDerUntersuchungValue(TemporalAccessor beginnDerUntersuchungValue) {
    this.beginnDerUntersuchungValue = beginnDerUntersuchungValue;
  }

  public TemporalAccessor getBeginnDerUntersuchungValue() {
    return this.beginnDerUntersuchungValue;
  }

  public void setEndeValue(TemporalAccessor endeValue) {
    this.endeValue = endeValue;
  }

  public TemporalAccessor getEndeValue() {
    return this.endeValue;
  }

  public void setDauerValue(TemporalAmount dauerValue) {
    this.dauerValue = dauerValue;
  }

  public TemporalAmount getDauerValue() {
    return this.dauerValue;
  }

  public void setStandort(List<StandortCluster> standort) {
    this.standort = standort;
  }

  public List<StandortCluster> getStandort() {
    return this.standort;
  }

  public void setBaumKommentarValue(String baumKommentarValue) {
    this.baumKommentarValue = baumKommentarValue;
  }

  public String getBaumKommentarValue() {
    return this.baumKommentarValue;
  }

  public void setUbertragungswegValue(String ubertragungswegValue) {
    this.ubertragungswegValue = ubertragungswegValue;
  }

  public String getUbertragungswegValue() {
    return this.ubertragungswegValue;
  }

  public void setTransmissionswegValue(String transmissionswegValue) {
    this.transmissionswegValue = transmissionswegValue;
  }

  public String getTransmissionswegValue() {
    return this.transmissionswegValue;
  }

  public void setArtDerUbertragungKommentarValue(String artDerUbertragungKommentarValue) {
    this.artDerUbertragungKommentarValue = artDerUbertragungKommentarValue;
  }

  public String getArtDerUbertragungKommentarValue() {
    return this.artDerUbertragungKommentarValue;
  }

  public void setErregernameDefiningCode(ErregernameDefiningCode erregernameDefiningCode) {
    this.erregernameDefiningCode = erregernameDefiningCode;
  }

  public ErregernameDefiningCode getErregernameDefiningCode() {
    return this.erregernameDefiningCode;
  }

  public void setAnzahlDerErregernachweise(
      List<SmicsErgebnisAnzahlDerErregernachweiseElement> anzahlDerErregernachweise) {
    this.anzahlDerErregernachweise = anzahlDerErregernachweise;
  }

  public List<SmicsErgebnisAnzahlDerErregernachweiseElement> getAnzahlDerErregernachweise() {
    return this.anzahlDerErregernachweise;
  }

  public void setErregerdetails(ErregerdetailsCluster erregerdetails) {
    this.erregerdetails = erregerdetails;
  }

  public ErregerdetailsCluster getErregerdetails() {
    return this.erregerdetails;
  }

  public void setErregertypisierung(ErregertypisierungCluster erregertypisierung) {
    this.erregertypisierung = erregertypisierung;
  }

  public ErregertypisierungCluster getErregertypisierung() {
    return this.erregertypisierung;
  }

  public void setZeitpunktDesErstenErregernachweisesValue(
      TemporalAccessor zeitpunktDesErstenErregernachweisesValue) {
    this.zeitpunktDesErstenErregernachweisesValue = zeitpunktDesErstenErregernachweisesValue;
  }

  public TemporalAccessor getZeitpunktDesErstenErregernachweisesValue() {
    return this.zeitpunktDesErstenErregernachweisesValue;
  }

  public void setZeitpunktDesLetztenErregernachweisesValue(
      TemporalAccessor zeitpunktDesLetztenErregernachweisesValue) {
    this.zeitpunktDesLetztenErregernachweisesValue = zeitpunktDesLetztenErregernachweisesValue;
  }

  public TemporalAccessor getZeitpunktDesLetztenErregernachweisesValue() {
    return this.zeitpunktDesLetztenErregernachweisesValue;
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
