package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.medication_use.v0")
public class FragebogenZumMedikationsScreeningObservation {
  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Medikamente in Verwendung?
   */
  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0027]/value")
  private DvCodedText medikamenteInVerwendung;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Spezifische Medikamentenklasse/Name der Medikamentenklasse
   */
  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0002]/value|value")
  private String nameDerMedikamentenklasseValue;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Spezifische Medikamentenklasse/Medikamentenklasse in Verwendung?
   */
  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0003]/value|defining_code")
  private MedikamentenklasseInVerwendungDefiningCode medikamentenklasseInVerwendungDefiningCode;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Spezifische Medikamentenklasse/Spezifische Medikamente
   */
  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0008]")
  private List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster> spezifischeMedikamente;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/time
   */
  @Path("/data[at0022]/events[at0023]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/origin
   */
  @Path("/data[at0022]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Erweiterung
   */
  @Path("/protocol[at0005]/items[at0019]")
  private List<Cluster> erweiterung;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/language
   */
  @Path("/language")
  private Language language;

  public void setMedikamenteInVerwendung(DvCodedText medikamenteInVerwendung) {
     this.medikamenteInVerwendung = medikamenteInVerwendung;
  }

  public DvCodedText getMedikamenteInVerwendung() {
     return this.medikamenteInVerwendung ;
  }

  public void setNameDerMedikamentenklasseValue(String nameDerMedikamentenklasseValue) {
     this.nameDerMedikamentenklasseValue = nameDerMedikamentenklasseValue;
  }

  public String getNameDerMedikamentenklasseValue() {
     return this.nameDerMedikamentenklasseValue ;
  }

  public void setMedikamentenklasseInVerwendungDefiningCode(
      MedikamentenklasseInVerwendungDefiningCode medikamentenklasseInVerwendungDefiningCode) {
     this.medikamentenklasseInVerwendungDefiningCode = medikamentenklasseInVerwendungDefiningCode;
  }

  public MedikamentenklasseInVerwendungDefiningCode getMedikamentenklasseInVerwendungDefiningCode(
      ) {
     return this.medikamentenklasseInVerwendungDefiningCode ;
  }

  public void setSpezifischeMedikamente(
      List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster> spezifischeMedikamente) {
     this.spezifischeMedikamente = spezifischeMedikamente;
  }

  public List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster> getSpezifischeMedikamente(
      ) {
     return this.spezifischeMedikamente ;
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
