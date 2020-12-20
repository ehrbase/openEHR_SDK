package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class PulseOximetryObservationContainment extends Containment {
  public SelectAqlField<PulseOximetryObservation> PULSE_OXIMETRY_OBSERVATION =
      new AqlFieldImp<PulseOximetryObservation>(
          PulseOximetryObservation.class,
          "",
          "PulseOximetryObservation",
          PulseOximetryObservation.class,
          this);

  public SelectAqlField<DvProportion> SPO =
      new AqlFieldImp<DvProportion>(
          PulseOximetryObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value",
          "spo",
          DvProportion.class,
          this);

  public ListSelectAqlField<Cluster> WAVEFORM =
      new ListAqlFieldImp<Cluster>(
          PulseOximetryObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0054]",
          "waveform",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> MULTIMEDIA_IMAGE =
      new ListAqlFieldImp<Cluster>(
          PulseOximetryObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0060]",
          "multimediaImage",
          Cluster.class,
          this);

  public SelectAqlField<Cluster> EXERTION =
      new AqlFieldImp<Cluster>(
          PulseOximetryObservation.class,
          "/data[at0001]/events[at0002]/state[at0014]/items[at0034]",
          "exertion",
          Cluster.class,
          this);

  public SelectAqlField<Cluster> INSPIRED_OXYGEN =
      new AqlFieldImp<Cluster>(
          PulseOximetryObservation.class,
          "/data[at0001]/events[at0002]/state[at0014]/items[at0015]",
          "inspiredOxygen",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          PulseOximetryObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          PulseOximetryObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<Cluster> OXIMETRY_DEVICE =
      new AqlFieldImp<Cluster>(
          PulseOximetryObservation.class,
          "/protocol[at0007]/items[at0018]",
          "oximetryDevice",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION =
      new ListAqlFieldImp<Cluster>(
          PulseOximetryObservation.class,
          "/protocol[at0007]/items[at0059]",
          "extension",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          PulseOximetryObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          PulseOximetryObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          PulseOximetryObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private PulseOximetryObservationContainment() {
    super("openEHR-EHR-OBSERVATION.pulse_oximetry.v1");
  }

  public static PulseOximetryObservationContainment getInstance() {
    return new PulseOximetryObservationContainment();
  }
}
