package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;

public class BloodPressureTrainingSampleObservationContainment extends Containment {
  public SelectAqlField<BloodPressureTrainingSampleObservation> BLOOD_PRESSURE_TRAINING_SAMPLE_OBSERVATION = new AqlFieldImp<BloodPressureTrainingSampleObservation>(BloodPressureTrainingSampleObservation.class, "", "BloodPressureTrainingSampleObservation", BloodPressureTrainingSampleObservation.class, this);

  public ListSelectAqlField<Cluster> DEVICE = new ListAqlFieldImp<Cluster>(BloodPressureTrainingSampleObservation.class, "/protocol[at0011]/items[at1025]", "device", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(BloodPressureTrainingSampleObservation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<Cluster> LEVEL_OF_EXERTION = new ListAqlFieldImp<Cluster>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/state[at0007]/items[at1030]", "levelOfExertion", Cluster.class, this);

  public SelectAqlField<String> COMMENT_VALUE = new AqlFieldImp<String>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0033]/value|value", "commentValue", String.class, this);

  public SelectAqlField<CuffSizeDefiningcode> CUFF_SIZE_DEFININGCODE = new AqlFieldImp<CuffSizeDefiningcode>(BloodPressureTrainingSampleObservation.class, "/protocol[at0011]/items[at0013]/value|defining_code", "cuffSizeDefiningcode", CuffSizeDefiningcode.class, this);

  public SelectAqlField<KorotkoffSoundsDefiningcode> KOROTKOFF_SOUNDS_DEFININGCODE = new AqlFieldImp<KorotkoffSoundsDefiningcode>(BloodPressureTrainingSampleObservation.class, "/protocol[at0011]/items[at1010]/value|defining_code", "korotkoffSoundsDefiningcode", KorotkoffSoundsDefiningcode.class, this);

  public SelectAqlField<Double> SYSTOLIC_MAGNITUDE = new AqlFieldImp<Double>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude", "systolicMagnitude", Double.class, this);

  public SelectAqlField<String> SYSTOLIC_UNITS = new AqlFieldImp<String>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units", "systolicUnits", String.class, this);

  public SelectAqlField<Double> DIASTOLIC_MAGNITUDE = new AqlFieldImp<Double>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|magnitude", "diastolicMagnitude", Double.class, this);

  public SelectAqlField<String> DIASTOLIC_UNITS = new AqlFieldImp<String>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|units", "diastolicUnits", String.class, this);

  public SelectAqlField<PositionDefiningcode> POSITION_DEFININGCODE = new AqlFieldImp<PositionDefiningcode>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/state[at0007]/items[at0008]/value|defining_code", "positionDefiningcode", PositionDefiningcode.class, this);

  public SelectAqlField<Double> TILT_MAGNITUDE = new AqlFieldImp<Double>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|magnitude", "tiltMagnitude", Double.class, this);

  public SelectAqlField<String> TILT_UNITS = new AqlFieldImp<String>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|units", "tiltUnits", String.class, this);

  public SelectAqlField<Double> MEAN_ARTERIAL_PRESSURE_MAGNITUDE = new AqlFieldImp<Double>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|magnitude", "meanArterialPressureMagnitude", Double.class, this);

  public SelectAqlField<String> MEAN_ARTERIAL_PRESSURE_UNITS = new AqlFieldImp<String>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|units", "meanArterialPressureUnits", String.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(BloodPressureTrainingSampleObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Double> PULSE_PRESSURE_MAGNITUDE = new AqlFieldImp<Double>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|magnitude", "pulsePressureMagnitude", Double.class, this);

  public SelectAqlField<String> PULSE_PRESSURE_UNITS = new AqlFieldImp<String>(BloodPressureTrainingSampleObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|units", "pulsePressureUnits", String.class, this);

  public SelectAqlField<LocationOfMeasurementDefiningcode> LOCATION_OF_MEASUREMENT_DEFININGCODE = new AqlFieldImp<LocationOfMeasurementDefiningcode>(BloodPressureTrainingSampleObservation.class, "/protocol[at0011]/items[at0014]/value|defining_code", "locationOfMeasurementDefiningcode", LocationOfMeasurementDefiningcode.class, this);

  private BloodPressureTrainingSampleObservationContainment() {
    super("openEHR-EHR-OBSERVATION.sample_blood_pressure.v1");
  }

  public static BloodPressureTrainingSampleObservationContainment getInstance() {
    return new BloodPressureTrainingSampleObservationContainment();
  }
}
