package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

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

public class BloodPressureObservationContainment extends Containment {
  public SelectAqlField<BloodPressureObservation> BLOOD_PRESSURE_OBSERVATION =
      new AqlFieldImp<BloodPressureObservation>(
          BloodPressureObservation.class,
          "",
          "BloodPressureObservation",
          BloodPressureObservation.class,
          this);

  public SelectAqlField<Double> SYSTOLIC_MAGNITUDE =
      new AqlFieldImp<Double>(
          BloodPressureObservation.class,
          "/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value|magnitude",
          "systolicMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> SYSTOLIC_UNITS =
      new AqlFieldImp<String>(
          BloodPressureObservation.class,
          "/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value|units",
          "systolicUnits",
          String.class,
          this);

  public SelectAqlField<Double> DIASTOLIC_MAGNITUDE =
      new AqlFieldImp<Double>(
          BloodPressureObservation.class,
          "/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value|magnitude",
          "diastolicMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> DIASTOLIC_UNITS =
      new AqlFieldImp<String>(
          BloodPressureObservation.class,
          "/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value|units",
          "diastolicUnits",
          String.class,
          this);

  public SelectAqlField<Cluster> EXERTION =
      new AqlFieldImp<Cluster>(
          BloodPressureObservation.class,
          "/data[at0001]/events[at0006]/state[at0007]/items[at1030]",
          "exertion",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          BloodPressureObservation.class,
          "/data[at0001]/events[at0006]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          BloodPressureObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> STRUCTURED_MEASUREMENT_LOCATION =
      new ListAqlFieldImp<Cluster>(
          BloodPressureObservation.class,
          "/protocol[at0011]/items[at1057]",
          "structuredMeasurementLocation",
          Cluster.class,
          this);

  public SelectAqlField<Cluster> DEVICE =
      new AqlFieldImp<Cluster>(
          BloodPressureObservation.class,
          "/protocol[at0011]/items[at1025]",
          "device",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION =
      new ListAqlFieldImp<Cluster>(
          BloodPressureObservation.class,
          "/protocol[at0011]/items[at1058]",
          "extension",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          BloodPressureObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          BloodPressureObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          BloodPressureObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private BloodPressureObservationContainment() {
    super("openEHR-EHR-OBSERVATION.blood_pressure.v2");
  }

  public static BloodPressureObservationContainment getInstance() {
    return new BloodPressureObservationContainment();
  }
}
