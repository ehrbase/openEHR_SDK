package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.exposure_assessment.v0")
public class AufenthaltInGesundheitseinrichtungObservation {
  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Agent (en)
   */
  @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0043]/value|value")
  private String agentEnValue;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Presence of any exposure? (en)
   */
  @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value|defining_code")
  private PresenceOfAnyExposureEnDefiningCode presenceOfAnyExposureEnDefiningCode;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Specific exposure (en)/*Exposure (en)
   */
  @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0045]/value|value")
  private String exposureEnValue;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Specific exposure (en)/Vorhandensein
   */
  @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0046]/value|defining_code")
  private VorhandenseinDefiningCode vorhandenseinDefiningCode;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/Kommentar
   */
  @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value|value")
  private String kommentarValue;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Erweiterung
   */
  @Path("/protocol[at0004]/items[at0056]")
  private List<Cluster> erweiterung;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/language
   */
  @Path("/language")
  private Language language;

  public void setAgentEnValue(String agentEnValue) {
     this.agentEnValue = agentEnValue;
  }

  public String getAgentEnValue() {
     return this.agentEnValue ;
  }

  public void setPresenceOfAnyExposureEnDefiningCode(
      PresenceOfAnyExposureEnDefiningCode presenceOfAnyExposureEnDefiningCode) {
     this.presenceOfAnyExposureEnDefiningCode = presenceOfAnyExposureEnDefiningCode;
  }

  public PresenceOfAnyExposureEnDefiningCode getPresenceOfAnyExposureEnDefiningCode() {
     return this.presenceOfAnyExposureEnDefiningCode ;
  }

  public void setExposureEnValue(String exposureEnValue) {
     this.exposureEnValue = exposureEnValue;
  }

  public String getExposureEnValue() {
     return this.exposureEnValue ;
  }

  public void setVorhandenseinDefiningCode(VorhandenseinDefiningCode vorhandenseinDefiningCode) {
     this.vorhandenseinDefiningCode = vorhandenseinDefiningCode;
  }

  public VorhandenseinDefiningCode getVorhandenseinDefiningCode() {
     return this.vorhandenseinDefiningCode ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
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
}