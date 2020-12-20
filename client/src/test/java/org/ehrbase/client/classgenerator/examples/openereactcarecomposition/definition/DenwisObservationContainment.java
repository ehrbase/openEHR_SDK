package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class DenwisObservationContainment extends Containment {
  public SelectAqlField<DenwisObservation> DENWIS_OBSERVATION =
      new AqlFieldImp<DenwisObservation>(
          DenwisObservation.class, "", "DenwisObservation", DenwisObservation.class, this);

  public SelectAqlField<DvOrdinal> Q1_BREATHING =
      new AqlFieldImp<DvOrdinal>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0028]/value",
          "q1Breathing",
          DvOrdinal.class,
          this);

  public ListSelectAqlField<DenwisBreathingIndicatorElement> BREATHING_INDICATOR =
      new ListAqlFieldImp<DenwisBreathingIndicatorElement>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0062]",
          "breathingIndicator",
          DenwisBreathingIndicatorElement.class,
          this);

  public SelectAqlField<DvOrdinal> Q2_CIRCULATION =
      new AqlFieldImp<DvOrdinal>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0033]/value",
          "q2Circulation",
          DvOrdinal.class,
          this);

  public ListSelectAqlField<DenwisCirculationIndicatorElement> CIRCULATION_INDICATOR =
      new ListAqlFieldImp<DenwisCirculationIndicatorElement>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0071]",
          "circulationIndicator",
          DenwisCirculationIndicatorElement.class,
          this);

  public SelectAqlField<DvOrdinal> Q3_TEMPERATURE =
      new AqlFieldImp<DvOrdinal>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0038]/value",
          "q3Temperature",
          DvOrdinal.class,
          this);

  public ListSelectAqlField<DenwisTemperatureIndicatorElement> TEMPERATURE_INDICATOR =
      new ListAqlFieldImp<DenwisTemperatureIndicatorElement>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0076]",
          "temperatureIndicator",
          DenwisTemperatureIndicatorElement.class,
          this);

  public SelectAqlField<DvOrdinal> Q4_MENTATION =
      new AqlFieldImp<DvOrdinal>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0043]/value",
          "q4Mentation",
          DvOrdinal.class,
          this);

  public ListSelectAqlField<DenwisMentationIndicatorElement> MENTATION_INDICATOR =
      new ListAqlFieldImp<DenwisMentationIndicatorElement>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0078]",
          "mentationIndicator",
          DenwisMentationIndicatorElement.class,
          this);

  public SelectAqlField<DvOrdinal> Q5_AGITATION =
      new AqlFieldImp<DvOrdinal>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0047]/value",
          "q5Agitation",
          DvOrdinal.class,
          this);

  public ListSelectAqlField<DenwisAgitationIndicatorElement> AGITATION_INDICATOR =
      new ListAqlFieldImp<DenwisAgitationIndicatorElement>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0081]",
          "agitationIndicator",
          DenwisAgitationIndicatorElement.class,
          this);

  public SelectAqlField<DvOrdinal> Q6_PAIN =
      new AqlFieldImp<DvOrdinal>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0050]/value",
          "q6Pain",
          DvOrdinal.class,
          this);

  public ListSelectAqlField<DenwisPainIndicatorElement> PAIN_INDICATOR =
      new ListAqlFieldImp<DenwisPainIndicatorElement>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0084]",
          "painIndicator",
          DenwisPainIndicatorElement.class,
          this);

  public SelectAqlField<DvOrdinal> Q7_TRAJECTORY =
      new AqlFieldImp<DvOrdinal>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0053]/value",
          "q7Trajectory",
          DvOrdinal.class,
          this);

  public ListSelectAqlField<DenwisTrajectoryIndicatorElement> TRAJECTORY_INDICATOR =
      new ListAqlFieldImp<DenwisTrajectoryIndicatorElement>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0087]",
          "trajectoryIndicator",
          DenwisTrajectoryIndicatorElement.class,
          this);

  public SelectAqlField<DvOrdinal> Q8_PATIENT_SUBJECTIVE =
      new AqlFieldImp<DvOrdinal>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0056]/value",
          "q8PatientSubjective",
          DvOrdinal.class,
          this);

  public ListSelectAqlField<DenwisPatientIndicatorElement> PATIENT_INDICATOR =
      new ListAqlFieldImp<DenwisPatientIndicatorElement>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0090]",
          "patientIndicator",
          DenwisPatientIndicatorElement.class,
          this);

  public SelectAqlField<DvOrdinal> Q9_NURSE_SUBJECTIVE =
      new AqlFieldImp<DvOrdinal>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0059]/value",
          "q9NurseSubjective",
          DvOrdinal.class,
          this);

  public ListSelectAqlField<DenwisNurseSubjectiveIndicatorElement> NURSE_SUBJECTIVE_INDICATOR =
      new ListAqlFieldImp<DenwisNurseSubjectiveIndicatorElement>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0093]",
          "nurseSubjectiveIndicator",
          DenwisNurseSubjectiveIndicatorElement.class,
          this);

  public SelectAqlField<String> Q10_OTHER_COMMENT_VALUE =
      new AqlFieldImp<String>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0027]/value|value",
          "q10OtherCommentValue",
          String.class,
          this);

  public SelectAqlField<Long> TOTAL_SCORE_MAGNITUDE =
      new AqlFieldImp<Long>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|magnitude",
          "totalScoreMagnitude",
          Long.class,
          this);

  public SelectAqlField<ItemTree> TREE =
      new AqlFieldImp<ItemTree>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/state[at0006]",
          "tree",
          ItemTree.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          DenwisObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          DenwisObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION =
      new ListAqlFieldImp<Cluster>(
          DenwisObservation.class,
          "/protocol[at0004]/items[at0005]",
          "extension",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          DenwisObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          DenwisObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          DenwisObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private DenwisObservationContainment() {
    super("openEHR-EHR-OBSERVATION.denwis.v0");
  }

  public static DenwisObservationContainment getInstance() {
    return new DenwisObservationContainment();
  }
}
