package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.exposure_assessment.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.560026600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AufenthaltInGesundheitseinrichtungObservation implements EntryEntity {
  /**
   * Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Agent (en)
   * Description: Der Name des chemischen, physikalischen oder biologischen Stoffes, dem eine Person
   * ausgesetzt gewesen sein könnte.
   */
  @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0043]/value|value")
  private String agentEnValue;

  /**
   * Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Presence of any
   * exposure? (en) Description: *Presence of any relevant exposure. (en)
   */
  @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value|defining_code")
  private PresenceOfAnyExposureEnDefiningCode presenceOfAnyExposureEnDefiningCode;

  /**
   * Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Specific
   * exposure (en)/*Exposure (en) Description: Bezeichnung des Expositionsrisikofaktors.
   */
  @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0045]/value|value")
  private String exposureEnValue;

  /**
   * Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Specific
   * exposure (en)/Vorhandensein Description: *Presence of the exposure. (en)
   */
  @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0046]/value|defining_code")
  private VorhandenseinDefiningCode vorhandenseinDefiningCode;

  /**
   * Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/Kommentar
   * Description: *Additional narrative about the exposure screening questionnaire not captured in
   * other fields. (en)
   */
  @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value|value")
  private String kommentarValue;

  /** Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Erweiterung Description: Zusätzliche
   * Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere
   * Referenzmodelle/Formalismen. Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche
   * Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0004]/items[at0056]")
  private List<Cluster> erweiterung;

  /** Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/language */
  @Path("/language")
  private Language language;

  /** Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAgentEnValue(String agentEnValue) {
    this.agentEnValue = agentEnValue;
  }

  public String getAgentEnValue() {
    return this.agentEnValue;
  }

  public void setPresenceOfAnyExposureEnDefiningCode(
      PresenceOfAnyExposureEnDefiningCode presenceOfAnyExposureEnDefiningCode) {
    this.presenceOfAnyExposureEnDefiningCode = presenceOfAnyExposureEnDefiningCode;
  }

  public PresenceOfAnyExposureEnDefiningCode getPresenceOfAnyExposureEnDefiningCode() {
    return this.presenceOfAnyExposureEnDefiningCode;
  }

  public void setExposureEnValue(String exposureEnValue) {
    this.exposureEnValue = exposureEnValue;
  }

  public String getExposureEnValue() {
    return this.exposureEnValue;
  }

  public void setVorhandenseinDefiningCode(VorhandenseinDefiningCode vorhandenseinDefiningCode) {
    this.vorhandenseinDefiningCode = vorhandenseinDefiningCode;
  }

  public VorhandenseinDefiningCode getVorhandenseinDefiningCode() {
    return this.vorhandenseinDefiningCode;
  }

  public void setKommentarValue(String kommentarValue) {
    this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
    return this.kommentarValue;
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
