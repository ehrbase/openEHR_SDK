package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class TemperatureObservationContainment extends Containment {
  public SelectAqlField<TemperatureObservation> TEMPERATURE_OBSERVATION =
      new AqlFieldImp<TemperatureObservation>(
          TemperatureObservation.class,
          "",
          "TemperatureObservation",
          TemperatureObservation.class,
          this);

  public SelectAqlField<Double> TEMPERATURE_MAGNITUDE =
      new AqlFieldImp<Double>(
          TemperatureObservation.class,
          "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude",
          "temperatureMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> TEMPERATURE_UNITS =
      new AqlFieldImp<String>(
          TemperatureObservation.class,
          "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units",
          "temperatureUnits",
          String.class,
          this);

  public SelectAqlField<Cluster> ENVIRONMENTAL_CONDITIONS =
      new AqlFieldImp<Cluster>(
          TemperatureObservation.class,
          "/data[at0002]/events[at0003]/state[at0029]/items[at0056]",
          "environmentalConditions",
          Cluster.class,
          this);

  public SelectAqlField<Cluster> EXERTION =
      new AqlFieldImp<Cluster>(
          TemperatureObservation.class,
          "/data[at0002]/events[at0003]/state[at0029]/items[at0057]",
          "exertion",
          Cluster.class,
          this);

  public SelectAqlField<Element> MENSTRUAL_CYCLE =
      new AqlFieldImp<Element>(
          TemperatureObservation.class,
          "/data[at0002]/events[at0003]/state[at0029]/items[at0058]",
          "menstrualCycle",
          Element.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          TemperatureObservation.class,
          "/data[at0002]/events[at0003]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          TemperatureObservation.class,
          "/data[at0002]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<Cluster> DEVICE =
      new AqlFieldImp<Cluster>(
          TemperatureObservation.class,
          "/protocol[at0020]/items[at0059]",
          "device",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          TemperatureObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          TemperatureObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          TemperatureObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private TemperatureObservationContainment() {
    super("openEHR-EHR-OBSERVATION.body_temperature.v1");
  }

  public static TemperatureObservationContainment getInstance() {
    return new TemperatureObservationContainment();
  }
}
