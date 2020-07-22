package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;

public class BodyTemperatureObservationContainment extends Containment {
  public SelectAqlField<BodyTemperatureObservation> BODY_TEMPERATURE_OBSERVATION = new AqlFieldImp<BodyTemperatureObservation>(BodyTemperatureObservation.class, "", "BodyTemperatureObservation", BodyTemperatureObservation.class, this);

  public ListSelectAqlField<BodyTemperatureAnyEventChoice> ANY_EVENT = new ListAqlFieldImp<BodyTemperatureAnyEventChoice>(BodyTemperatureObservation.class, "/data[at0002]/events[at0003]", "anyEvent", BodyTemperatureAnyEventChoice.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(BodyTemperatureObservation.class, "/protocol[at0020]/items[at0062]", "extension", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(BodyTemperatureObservation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<Cluster> STRUCTURED_MEASUREMENT_LOCATION = new ListAqlFieldImp<Cluster>(BodyTemperatureObservation.class, "/protocol[at0020]/items[at0064]", "structuredMeasurementLocation", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(BodyTemperatureObservation.class, "/data[at0002]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Cluster> DEVICE = new AqlFieldImp<Cluster>(BodyTemperatureObservation.class, "/protocol[at0020]/items[at0059]", "device", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(BodyTemperatureObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<BodyTemperatureLocationOfMeasurementChoice> LOCATION_OF_MEASUREMENT = new AqlFieldImp<BodyTemperatureLocationOfMeasurementChoice>(BodyTemperatureObservation.class, "/protocol[at0020]/items[at0021]/value", "locationOfMeasurement", BodyTemperatureLocationOfMeasurementChoice.class, this);

  private BodyTemperatureObservationContainment() {
    super("openEHR-EHR-OBSERVATION.body_temperature.v2");
  }

  public static BodyTemperatureObservationContainment getInstance() {
    return new BodyTemperatureObservationContainment();
  }
}
