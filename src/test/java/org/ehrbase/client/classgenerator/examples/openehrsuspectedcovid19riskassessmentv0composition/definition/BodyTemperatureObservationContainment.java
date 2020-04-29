package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class BodyTemperatureObservationContainment extends Containment {
  public SelectAqlField<BodyTemperatureObservation> BODY_TEMPERATURE_OBSERVATION = new AqlFieldImp<BodyTemperatureObservation>(BodyTemperatureObservation.class, "", "BodyTemperatureObservation", BodyTemperatureObservation.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(BodyTemperatureObservation.class, "/protocol[at0020]/items[at0062]", "extension", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(BodyTemperatureObservation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<Cluster> STRUCTURED_MEASUREMENT_LOCATION = new ListAqlFieldImp<Cluster>(BodyTemperatureObservation.class, "/protocol[at0020]/items[at0064]", "structuredMeasurementLocation", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(BodyTemperatureObservation.class, "/data[at0002]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Cluster> EXERTION = new AqlFieldImp<Cluster>(BodyTemperatureObservation.class, "/data[at0002]/events[at0003]/state[at0029]/items[at0057]", "exertion", Cluster.class, this);

  public SelectAqlField<Double> TEMPERATURE_MAGNITUDE = new AqlFieldImp<Double>(BodyTemperatureObservation.class, "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude", "temperatureMagnitude", Double.class, this);

  public SelectAqlField<String> TEMPERATURE_UNITS = new AqlFieldImp<String>(BodyTemperatureObservation.class, "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units", "temperatureUnits", String.class, this);

  public ListSelectAqlField<Cluster> ENVIRONMENTAL_CONDITIONS = new ListAqlFieldImp<Cluster>(BodyTemperatureObservation.class, "/data[at0002]/events[at0003]/state[at0029]/items[at0056]", "environmentalConditions", Cluster.class, this);

  public SelectAqlField<Cluster> DEVICE = new AqlFieldImp<Cluster>(BodyTemperatureObservation.class, "/protocol[at0020]/items[at0059]", "device", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(BodyTemperatureObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(BodyTemperatureObservation.class, "/data[at0002]/events[at0003]/time|value", "timeValue", TemporalAccessor.class, this);

  private BodyTemperatureObservationContainment() {
    super("openEHR-EHR-OBSERVATION.body_temperature.v2");
  }

  public static BodyTemperatureObservationContainment getInstance() {
    return new BodyTemperatureObservationContainment();
  }
}
