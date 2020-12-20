package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class BloodPressureTrainingSampleObservationContainment extends Containment {
  public SelectAqlField<BloodPressureTrainingSampleObservation>
      BLOOD_PRESSURE_TRAINING_SAMPLE_OBSERVATION =
          new AqlFieldImp<BloodPressureTrainingSampleObservation>(
              BloodPressureTrainingSampleObservation.class,
              "",
              "BloodPressureTrainingSampleObservation",
              BloodPressureTrainingSampleObservation.class,
              this);

  public SelectAqlField<Double> SYSTOLIC_MAGNITUDE =
      new AqlFieldImp<Double>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude",
          "systolicMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> SYSTOLIC_UNITS =
      new AqlFieldImp<String>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units",
          "systolicUnits",
          String.class,
          this);

  public SelectAqlField<NullFlavour> SYSTOLIC_NULL_FLAVOUR_DEFINING_CODE =
      new AqlFieldImp<NullFlavour>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code",
          "systolicNullFlavourDefiningCode",
          NullFlavour.class,
          this);

  public SelectAqlField<Double> DIASTOLIC_MAGNITUDE =
      new AqlFieldImp<Double>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|magnitude",
          "diastolicMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> DIASTOLIC_UNITS =
      new AqlFieldImp<String>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|units",
          "diastolicUnits",
          String.class,
          this);

  public SelectAqlField<NullFlavour> DIASTOLIC_NULL_FLAVOUR_DEFINING_CODE =
      new AqlFieldImp<NullFlavour>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/null_flavour|defining_code",
          "diastolicNullFlavourDefiningCode",
          NullFlavour.class,
          this);

  public SelectAqlField<Double> MEAN_ARTERIAL_PRESSURE_MAGNITUDE =
      new AqlFieldImp<Double>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|magnitude",
          "meanArterialPressureMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> MEAN_ARTERIAL_PRESSURE_UNITS =
      new AqlFieldImp<String>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|units",
          "meanArterialPressureUnits",
          String.class,
          this);

  public SelectAqlField<NullFlavour> MEAN_ARTERIAL_PRESSURE_NULL_FLAVOUR_DEFINING_CODE =
      new AqlFieldImp<NullFlavour>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at1006]/null_flavour|defining_code",
          "meanArterialPressureNullFlavourDefiningCode",
          NullFlavour.class,
          this);

  public SelectAqlField<Double> PULSE_PRESSURE_MAGNITUDE =
      new AqlFieldImp<Double>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|magnitude",
          "pulsePressureMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> PULSE_PRESSURE_UNITS =
      new AqlFieldImp<String>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|units",
          "pulsePressureUnits",
          String.class,
          this);

  public SelectAqlField<NullFlavour> PULSE_PRESSURE_NULL_FLAVOUR_DEFINING_CODE =
      new AqlFieldImp<NullFlavour>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at1007]/null_flavour|defining_code",
          "pulsePressureNullFlavourDefiningCode",
          NullFlavour.class,
          this);

  public SelectAqlField<String> COMMENT_VALUE =
      new AqlFieldImp<String>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0033]/value|value",
          "commentValue",
          String.class,
          this);

  public SelectAqlField<NullFlavour> COMMENT_NULL_FLAVOUR_DEFINING_CODE =
      new AqlFieldImp<NullFlavour>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0033]/null_flavour|defining_code",
          "commentNullFlavourDefiningCode",
          NullFlavour.class,
          this);

  public SelectAqlField<PositionDefiningCode> POSITION_DEFINING_CODE =
      new AqlFieldImp<PositionDefiningCode>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/state[at0007]/items[at0008]/value|defining_code",
          "positionDefiningCode",
          PositionDefiningCode.class,
          this);

  public SelectAqlField<NullFlavour> POSITION_NULL_FLAVOUR_DEFINING_CODE =
      new AqlFieldImp<NullFlavour>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/state[at0007]/items[at0008]/null_flavour|defining_code",
          "positionNullFlavourDefiningCode",
          NullFlavour.class,
          this);

  public ListSelectAqlField<Cluster> LEVEL_OF_EXERTION =
      new ListAqlFieldImp<Cluster>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/state[at0007]/items[at1030]",
          "levelOfExertion",
          Cluster.class,
          this);

  public SelectAqlField<Double> TILT_MAGNITUDE =
      new AqlFieldImp<Double>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|magnitude",
          "tiltMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> TILT_UNITS =
      new AqlFieldImp<String>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|units",
          "tiltUnits",
          String.class,
          this);

  public SelectAqlField<NullFlavour> TILT_NULL_FLAVOUR_DEFINING_CODE =
      new AqlFieldImp<NullFlavour>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/state[at0007]/items[at1005]/null_flavour|defining_code",
          "tiltNullFlavourDefiningCode",
          NullFlavour.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          BloodPressureTrainingSampleObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<CuffSizeDefiningCode> CUFF_SIZE_DEFINING_CODE =
      new AqlFieldImp<CuffSizeDefiningCode>(
          BloodPressureTrainingSampleObservation.class,
          "/protocol[at0011]/items[at0013]/value|defining_code",
          "cuffSizeDefiningCode",
          CuffSizeDefiningCode.class,
          this);

  public SelectAqlField<NullFlavour> CUFF_SIZE_NULL_FLAVOUR_DEFINING_CODE =
      new AqlFieldImp<NullFlavour>(
          BloodPressureTrainingSampleObservation.class,
          "/protocol[at0011]/items[at0013]/null_flavour|defining_code",
          "cuffSizeNullFlavourDefiningCode",
          NullFlavour.class,
          this);

  public SelectAqlField<LocationOfMeasurementDefiningCode> LOCATION_OF_MEASUREMENT_DEFINING_CODE =
      new AqlFieldImp<LocationOfMeasurementDefiningCode>(
          BloodPressureTrainingSampleObservation.class,
          "/protocol[at0011]/items[at0014]/value|defining_code",
          "locationOfMeasurementDefiningCode",
          LocationOfMeasurementDefiningCode.class,
          this);

  public SelectAqlField<NullFlavour> LOCATION_OF_MEASUREMENT_NULL_FLAVOUR_DEFINING_CODE =
      new AqlFieldImp<NullFlavour>(
          BloodPressureTrainingSampleObservation.class,
          "/protocol[at0011]/items[at0014]/null_flavour|defining_code",
          "locationOfMeasurementNullFlavourDefiningCode",
          NullFlavour.class,
          this);

  public SelectAqlField<KorotkoffSoundsDefiningCode> KOROTKOFF_SOUNDS_DEFINING_CODE =
      new AqlFieldImp<KorotkoffSoundsDefiningCode>(
          BloodPressureTrainingSampleObservation.class,
          "/protocol[at0011]/items[at1010]/value|defining_code",
          "korotkoffSoundsDefiningCode",
          KorotkoffSoundsDefiningCode.class,
          this);

  public SelectAqlField<NullFlavour> KOROTKOFF_SOUNDS_NULL_FLAVOUR_DEFINING_CODE =
      new AqlFieldImp<NullFlavour>(
          BloodPressureTrainingSampleObservation.class,
          "/protocol[at0011]/items[at1010]/null_flavour|defining_code",
          "korotkoffSoundsNullFlavourDefiningCode",
          NullFlavour.class,
          this);

  public ListSelectAqlField<Cluster> DEVICE =
      new ListAqlFieldImp<Cluster>(
          BloodPressureTrainingSampleObservation.class,
          "/protocol[at0011]/items[at1025]",
          "device",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          BloodPressureTrainingSampleObservation.class,
          "/subject",
          "subject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          BloodPressureTrainingSampleObservation.class,
          "/language",
          "language",
          Language.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          BloodPressureTrainingSampleObservation.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  private BloodPressureTrainingSampleObservationContainment() {
    super("openEHR-EHR-OBSERVATION.sample_blood_pressure.v1");
  }

  public static BloodPressureTrainingSampleObservationContainment getInstance() {
    return new BloodPressureTrainingSampleObservationContainment();
  }
}
