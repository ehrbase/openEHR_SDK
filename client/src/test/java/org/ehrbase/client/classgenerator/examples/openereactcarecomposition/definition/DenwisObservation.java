package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
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
@Archetype("openEHR-EHR-OBSERVATION.denwis.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.382500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class DenwisObservation implements EntryEntity {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Q1 Breathing Description: Nurse recorded
   * changes in breathing.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0028]/value")
  private DvOrdinal q1Breathing;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Breathing indicator Description: Nurse
   * recorded the indication that there are changes in breathing.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0062]")
  private List<DenwisBreathingIndicatorElement> breathingIndicator;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Q2 Circulation Description: Nurse
   * recorded changes in circulation.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0033]/value")
  private DvOrdinal q2Circulation;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Circulation indicator Description: Nurse
   * recorded changes in circulation.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0071]")
  private List<DenwisCirculationIndicatorElement> circulationIndicator;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Q3 Temperature Description: Nurse
   * recorded changes in temperature.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0038]/value")
  private DvOrdinal q3Temperature;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Temperature indicator Description: Nurse
   * recorded changes in temperature.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0076]")
  private List<DenwisTemperatureIndicatorElement> temperatureIndicator;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Q4 Mentation Description: Nurse recorded
   * changes in mentation.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0043]/value")
  private DvOrdinal q4Mentation;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Mentation indicator Description: Nurse
   * recorded changes in mentation.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0078]")
  private List<DenwisMentationIndicatorElement> mentationIndicator;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Q5 Agitation Description: Nurse recorded
   * changes in agitation.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0047]/value")
  private DvOrdinal q5Agitation;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Agitation indicator Description: Nurse
   * recorded changes in agitation.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0081]")
  private List<DenwisAgitationIndicatorElement> agitationIndicator;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Q6 Pain Description: Nurse recorded
   * changes in pain.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0050]/value")
  private DvOrdinal q6Pain;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Pain indicator Description: Nurse
   * recorded changes in pain.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0084]")
  private List<DenwisPainIndicatorElement> painIndicator;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Q7 Trajectory Description: Nurse
   * recorded unexpected trajectory.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0053]/value")
  private DvOrdinal q7Trajectory;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Trajectory indicator Description: Nurse
   * recorded unexpected trajectory.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0087]")
  private List<DenwisTrajectoryIndicatorElement> trajectoryIndicator;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Q8 Patient subjective Description: Nurse
   * recorded patient indicators.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0056]/value")
  private DvOrdinal q8PatientSubjective;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Patient indicator Description: Nurse
   * recorded patient indicators.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0090]")
  private List<DenwisPatientIndicatorElement> patientIndicator;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Q9 Nurse subjective Description: Nurse
   * recorded subjective observation.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0059]/value")
  private DvOrdinal q9NurseSubjective;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Nurse subjective indicator Description:
   * Nurse recorded subjective observation.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0093]")
  private List<DenwisNurseSubjectiveIndicatorElement> nurseSubjectiveIndicator;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Q 10 Other comment Description:
   * Narrative comment.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0027]/value|value")
  private String q10OtherCommentValue;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Total score Description: The total score
   * for the four questions.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|magnitude")
  private Long totalScoreMagnitude;

  /** Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Tree Description: @ internal @ */
  @Path("/data[at0001]/events[at0002]/state[at0006]")
  private ItemTree tree;

  /** Path: open_eREACT-Care/Assessment/DENWIS/Point in time/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Path: open_eREACT-Care/Assessment/DENWIS/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Extension Description: Additional information required
   * to capture local context or to align with other reference models/formalisms. Comment: e.g.
   * Local hospital departmental infomation or additional metadata to align with HL7 or CDISC
   * equivalents.
   */
  @Path("/protocol[at0004]/items[at0005]")
  private List<Cluster> extension;

  /** Path: open_eREACT-Care/Assessment/DENWIS/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: open_eREACT-Care/Assessment/DENWIS/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/Assessment/DENWIS/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setQ1Breathing(DvOrdinal q1Breathing) {
    this.q1Breathing = q1Breathing;
  }

  public DvOrdinal getQ1Breathing() {
    return this.q1Breathing;
  }

  public void setBreathingIndicator(List<DenwisBreathingIndicatorElement> breathingIndicator) {
    this.breathingIndicator = breathingIndicator;
  }

  public List<DenwisBreathingIndicatorElement> getBreathingIndicator() {
    return this.breathingIndicator;
  }

  public void setQ2Circulation(DvOrdinal q2Circulation) {
    this.q2Circulation = q2Circulation;
  }

  public DvOrdinal getQ2Circulation() {
    return this.q2Circulation;
  }

  public void setCirculationIndicator(
      List<DenwisCirculationIndicatorElement> circulationIndicator) {
    this.circulationIndicator = circulationIndicator;
  }

  public List<DenwisCirculationIndicatorElement> getCirculationIndicator() {
    return this.circulationIndicator;
  }

  public void setQ3Temperature(DvOrdinal q3Temperature) {
    this.q3Temperature = q3Temperature;
  }

  public DvOrdinal getQ3Temperature() {
    return this.q3Temperature;
  }

  public void setTemperatureIndicator(
      List<DenwisTemperatureIndicatorElement> temperatureIndicator) {
    this.temperatureIndicator = temperatureIndicator;
  }

  public List<DenwisTemperatureIndicatorElement> getTemperatureIndicator() {
    return this.temperatureIndicator;
  }

  public void setQ4Mentation(DvOrdinal q4Mentation) {
    this.q4Mentation = q4Mentation;
  }

  public DvOrdinal getQ4Mentation() {
    return this.q4Mentation;
  }

  public void setMentationIndicator(List<DenwisMentationIndicatorElement> mentationIndicator) {
    this.mentationIndicator = mentationIndicator;
  }

  public List<DenwisMentationIndicatorElement> getMentationIndicator() {
    return this.mentationIndicator;
  }

  public void setQ5Agitation(DvOrdinal q5Agitation) {
    this.q5Agitation = q5Agitation;
  }

  public DvOrdinal getQ5Agitation() {
    return this.q5Agitation;
  }

  public void setAgitationIndicator(List<DenwisAgitationIndicatorElement> agitationIndicator) {
    this.agitationIndicator = agitationIndicator;
  }

  public List<DenwisAgitationIndicatorElement> getAgitationIndicator() {
    return this.agitationIndicator;
  }

  public void setQ6Pain(DvOrdinal q6Pain) {
    this.q6Pain = q6Pain;
  }

  public DvOrdinal getQ6Pain() {
    return this.q6Pain;
  }

  public void setPainIndicator(List<DenwisPainIndicatorElement> painIndicator) {
    this.painIndicator = painIndicator;
  }

  public List<DenwisPainIndicatorElement> getPainIndicator() {
    return this.painIndicator;
  }

  public void setQ7Trajectory(DvOrdinal q7Trajectory) {
    this.q7Trajectory = q7Trajectory;
  }

  public DvOrdinal getQ7Trajectory() {
    return this.q7Trajectory;
  }

  public void setTrajectoryIndicator(List<DenwisTrajectoryIndicatorElement> trajectoryIndicator) {
    this.trajectoryIndicator = trajectoryIndicator;
  }

  public List<DenwisTrajectoryIndicatorElement> getTrajectoryIndicator() {
    return this.trajectoryIndicator;
  }

  public void setQ8PatientSubjective(DvOrdinal q8PatientSubjective) {
    this.q8PatientSubjective = q8PatientSubjective;
  }

  public DvOrdinal getQ8PatientSubjective() {
    return this.q8PatientSubjective;
  }

  public void setPatientIndicator(List<DenwisPatientIndicatorElement> patientIndicator) {
    this.patientIndicator = patientIndicator;
  }

  public List<DenwisPatientIndicatorElement> getPatientIndicator() {
    return this.patientIndicator;
  }

  public void setQ9NurseSubjective(DvOrdinal q9NurseSubjective) {
    this.q9NurseSubjective = q9NurseSubjective;
  }

  public DvOrdinal getQ9NurseSubjective() {
    return this.q9NurseSubjective;
  }

  public void setNurseSubjectiveIndicator(
      List<DenwisNurseSubjectiveIndicatorElement> nurseSubjectiveIndicator) {
    this.nurseSubjectiveIndicator = nurseSubjectiveIndicator;
  }

  public List<DenwisNurseSubjectiveIndicatorElement> getNurseSubjectiveIndicator() {
    return this.nurseSubjectiveIndicator;
  }

  public void setQ10OtherCommentValue(String q10OtherCommentValue) {
    this.q10OtherCommentValue = q10OtherCommentValue;
  }

  public String getQ10OtherCommentValue() {
    return this.q10OtherCommentValue;
  }

  public void setTotalScoreMagnitude(Long totalScoreMagnitude) {
    this.totalScoreMagnitude = totalScoreMagnitude;
  }

  public Long getTotalScoreMagnitude() {
    return this.totalScoreMagnitude;
  }

  public void setTree(ItemTree tree) {
    this.tree = tree;
  }

  public ItemTree getTree() {
    return this.tree;
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

  public void setExtension(List<Cluster> extension) {
    this.extension = extension;
  }

  public List<Cluster> getExtension() {
    return this.extension;
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
