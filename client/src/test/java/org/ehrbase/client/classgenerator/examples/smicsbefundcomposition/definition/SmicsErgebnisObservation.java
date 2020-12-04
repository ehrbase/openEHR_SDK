package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.smics_befund.v1")
public class SmicsErgebnisObservation {
  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/SmICS Ergebniskategorie
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code")
  private SmicsErgebniskategorieDefiningCode smicsErgebniskategorieDefiningCode;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Multispezies
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value")
  private Boolean multispeziesValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Beginn
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value|value")
  private TemporalAccessor beginnValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Beginn der Untersuchung
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|value")
  private TemporalAccessor beginnDerUntersuchungValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Ende
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|value")
  private TemporalAccessor endeValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Dauer
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0009]/value|value")
  private TemporalAmount dauerValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.location.v1]")
  private List<StandortCluster> standort;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Kommentar
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value|value")
  private String baumKommentarValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Art der Übertragung/Übertragungsweg
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0014]/value|value")
  private String ubertragungswegValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Art der Übertragung/Transmissionsweg
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0013]/value|value")
  private String transmissionswegValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Art der Übertragung/Kommentar
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0038]/value|value")
  private String artDerUbertragungKommentarValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregername
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0016]/value|defining_code")
  private ErregernameDefiningCode erregernameDefiningCode;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Anzahl der Erregernachweise
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0037]")
  private List<SmicsErgebnisAnzahlDerErregernachweiseElement> anzahlDerErregernachweise;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[openEHR-EHR-CLUSTER.erregerdetails.v1]")
  private ErregerdetailsCluster erregerdetails;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregertypisierung
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[openEHR-EHR-CLUSTER.molekulare_typisierung.v0 and name/value='Erregertypisierung']")
  private ErregertypisierungCluster erregertypisierung;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Zeitpunkt des ersten Erregernachweises
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0021]/value|value")
  private TemporalAccessor zeitpunktDesErstenErregernachweisesValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Zeitpunkt des letzten Erregernachweises
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0020]/value|value")
  private TemporalAccessor zeitpunktDesLetztenErregernachweisesValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * SmICS Befund/SmICS-Ergebnis/language
   */
  @Path("/language")
  private Language language;

  /**
   * SmICS Befund/SmICS-Ergebnis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSmicsErgebniskategorieDefiningCode(
      SmicsErgebniskategorieDefiningCode smicsErgebniskategorieDefiningCode) {
     this.smicsErgebniskategorieDefiningCode = smicsErgebniskategorieDefiningCode;
  }

  public SmicsErgebniskategorieDefiningCode getSmicsErgebniskategorieDefiningCode() {
     return this.smicsErgebniskategorieDefiningCode ;
  }

  public void setMultispeziesValue(Boolean multispeziesValue) {
     this.multispeziesValue = multispeziesValue;
  }

  public Boolean isMultispeziesValue() {
     return this.multispeziesValue ;
  }

  public void setBeginnValue(TemporalAccessor beginnValue) {
     this.beginnValue = beginnValue;
  }

  public TemporalAccessor getBeginnValue() {
     return this.beginnValue ;
  }

  public void setBeginnDerUntersuchungValue(TemporalAccessor beginnDerUntersuchungValue) {
     this.beginnDerUntersuchungValue = beginnDerUntersuchungValue;
  }

  public TemporalAccessor getBeginnDerUntersuchungValue() {
     return this.beginnDerUntersuchungValue ;
  }

  public void setEndeValue(TemporalAccessor endeValue) {
     this.endeValue = endeValue;
  }

  public TemporalAccessor getEndeValue() {
     return this.endeValue ;
  }

  public void setDauerValue(TemporalAmount dauerValue) {
     this.dauerValue = dauerValue;
  }

  public TemporalAmount getDauerValue() {
     return this.dauerValue ;
  }

  public void setStandort(List<StandortCluster> standort) {
     this.standort = standort;
  }

  public List<StandortCluster> getStandort() {
     return this.standort ;
  }

  public void setBaumKommentarValue(String baumKommentarValue) {
     this.baumKommentarValue = baumKommentarValue;
  }

  public String getBaumKommentarValue() {
     return this.baumKommentarValue ;
  }

  public void setUbertragungswegValue(String ubertragungswegValue) {
     this.ubertragungswegValue = ubertragungswegValue;
  }

  public String getUbertragungswegValue() {
     return this.ubertragungswegValue ;
  }

  public void setTransmissionswegValue(String transmissionswegValue) {
     this.transmissionswegValue = transmissionswegValue;
  }

  public String getTransmissionswegValue() {
     return this.transmissionswegValue ;
  }

  public void setArtDerUbertragungKommentarValue(String artDerUbertragungKommentarValue) {
     this.artDerUbertragungKommentarValue = artDerUbertragungKommentarValue;
  }

  public String getArtDerUbertragungKommentarValue() {
     return this.artDerUbertragungKommentarValue ;
  }

  public void setErregernameDefiningCode(ErregernameDefiningCode erregernameDefiningCode) {
     this.erregernameDefiningCode = erregernameDefiningCode;
  }

  public ErregernameDefiningCode getErregernameDefiningCode() {
     return this.erregernameDefiningCode ;
  }

  public void setAnzahlDerErregernachweise(
      List<SmicsErgebnisAnzahlDerErregernachweiseElement> anzahlDerErregernachweise) {
     this.anzahlDerErregernachweise = anzahlDerErregernachweise;
  }

  public List<SmicsErgebnisAnzahlDerErregernachweiseElement> getAnzahlDerErregernachweise() {
     return this.anzahlDerErregernachweise ;
  }

  public void setErregerdetails(ErregerdetailsCluster erregerdetails) {
     this.erregerdetails = erregerdetails;
  }

  public ErregerdetailsCluster getErregerdetails() {
     return this.erregerdetails ;
  }

  public void setErregertypisierung(ErregertypisierungCluster erregertypisierung) {
     this.erregertypisierung = erregertypisierung;
  }

  public ErregertypisierungCluster getErregertypisierung() {
     return this.erregertypisierung ;
  }

  public void setZeitpunktDesErstenErregernachweisesValue(
      TemporalAccessor zeitpunktDesErstenErregernachweisesValue) {
     this.zeitpunktDesErstenErregernachweisesValue = zeitpunktDesErstenErregernachweisesValue;
  }

  public TemporalAccessor getZeitpunktDesErstenErregernachweisesValue() {
     return this.zeitpunktDesErstenErregernachweisesValue ;
  }

  public void setZeitpunktDesLetztenErregernachweisesValue(
      TemporalAccessor zeitpunktDesLetztenErregernachweisesValue) {
     this.zeitpunktDesLetztenErregernachweisesValue = zeitpunktDesLetztenErregernachweisesValue;
  }

  public TemporalAccessor getZeitpunktDesLetztenErregernachweisesValue() {
     return this.zeitpunktDesLetztenErregernachweisesValue ;
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
