package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

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

public class ReisefallObservationContainment extends Containment {
  public SelectAqlField<ReisefallObservation> REISEFALL_OBSERVATION =
      new AqlFieldImp<ReisefallObservation>(
          ReisefallObservation.class, "", "ReisefallObservation", ReisefallObservation.class, this);

  public ListSelectAqlField<ReisefallBeliebigesIntervallereignisIntervalEvent>
      BELIEBIGES_INTERVALLEREIGNIS =
          new ListAqlFieldImp<ReisefallBeliebigesIntervallereignisIntervalEvent>(
              ReisefallObservation.class,
              "/data[at0001]/events[at0002]",
              "beliebigesIntervallereignis",
              ReisefallBeliebigesIntervallereignisIntervalEvent.class,
              this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          ReisefallObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          ReisefallObservation.class,
          "/protocol[at0007]/items[at0021]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          ReisefallObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          ReisefallObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          ReisefallObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ReisefallObservationContainment() {
    super("openEHR-EHR-OBSERVATION.travel_event.v0");
  }

  public static ReisefallObservationContainment getInstance() {
    return new ReisefallObservationContainment();
  }
}
