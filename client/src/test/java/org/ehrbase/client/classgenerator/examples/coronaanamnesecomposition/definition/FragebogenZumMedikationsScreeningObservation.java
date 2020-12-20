package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
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
@Archetype("openEHR-EHR-OBSERVATION.medication_use.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.601027600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class FragebogenZumMedikationsScreeningObservation implements EntryEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges
   * Ereignis/Medikamente in Verwendung? Description: Wendet die Person das Medikament bei oder
   * während des Zeitpunkts des Ergebnisses an?
   */
  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0027]/value")
  private DvCodedText medikamenteInVerwendung;

  /**
   * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges
   * Ereignis/Spezifische Medikamentenklasse/Name der Medikamentenklasse Description: Name der
   * Klasse oder des Medikamententyps.
   */
  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0002]/value|value")
  private String nameDerMedikamentenklasseValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges
   * Ereignis/Spezifische Medikamentenklasse/Medikamentenklasse in Verwendung? Description: Wendet
   * die Person das Medikament, die Klasse oder Art des Medikaments bei oder während des Zeitpunkts
   * des Ergebnisses an?
   */
  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0003]/value|defining_code")
  private MedikamentenklasseInVerwendungDefiningCode medikamentenklasseInVerwendungDefiningCode;

  /**
   * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges
   * Ereignis/Spezifische Medikamentenklasse/Spezifische Medikamente Description: Details über ein
   * spezifisches Medikament oder eine Medikamentenunterklasse der Medikamentenklasse.
   */
  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0008]")
  private List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster>
      spezifischeMedikamente;

  /**
   * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/time
   */
  @Path("/data[at0022]/events[at0023]/time|value")
  private TemporalAccessor timeValue;

  /** Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/origin */
  @Path("/data[at0022]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Erweiterung Description:
   * Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere
   * Referenzmodelle/Formalismen. Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche
   * Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0005]/items[at0019]")
  private List<Cluster> erweiterung;

  /** Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/language */
  @Path("/language")
  private Language language;

  /** Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setMedikamenteInVerwendung(DvCodedText medikamenteInVerwendung) {
    this.medikamenteInVerwendung = medikamenteInVerwendung;
  }

  public DvCodedText getMedikamenteInVerwendung() {
    return this.medikamenteInVerwendung;
  }

  public void setNameDerMedikamentenklasseValue(String nameDerMedikamentenklasseValue) {
    this.nameDerMedikamentenklasseValue = nameDerMedikamentenklasseValue;
  }

  public String getNameDerMedikamentenklasseValue() {
    return this.nameDerMedikamentenklasseValue;
  }

  public void setMedikamentenklasseInVerwendungDefiningCode(
      MedikamentenklasseInVerwendungDefiningCode medikamentenklasseInVerwendungDefiningCode) {
    this.medikamentenklasseInVerwendungDefiningCode = medikamentenklasseInVerwendungDefiningCode;
  }

  public MedikamentenklasseInVerwendungDefiningCode
      getMedikamentenklasseInVerwendungDefiningCode() {
    return this.medikamentenklasseInVerwendungDefiningCode;
  }

  public void setSpezifischeMedikamente(
      List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster> spezifischeMedikamente) {
    this.spezifischeMedikamente = spezifischeMedikamente;
  }

  public List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster>
      getSpezifischeMedikamente() {
    return this.spezifischeMedikamente;
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
