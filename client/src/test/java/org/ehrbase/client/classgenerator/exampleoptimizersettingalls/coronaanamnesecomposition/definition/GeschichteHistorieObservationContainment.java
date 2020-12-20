package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

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

public class GeschichteHistorieObservationContainment extends Containment {
  public SelectAqlField<GeschichteHistorieObservation> GESCHICHTE_HISTORIE_OBSERVATION =
      new AqlFieldImp<GeschichteHistorieObservation>(
          GeschichteHistorieObservation.class,
          "",
          "GeschichteHistorieObservation",
          GeschichteHistorieObservation.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          GeschichteHistorieObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          GeschichteHistorieObservation.class,
          "/protocol[at0007]/items[at0008]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          GeschichteHistorieObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          GeschichteHistorieObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          GeschichteHistorieObservation.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  public ListSelectAqlField<GeschichteHistorieBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS =
      new ListAqlFieldImp<GeschichteHistorieBeliebigesEreignisChoice>(
          GeschichteHistorieObservation.class,
          "/data[at0001]/events[at0002]",
          "beliebigesEreignis",
          GeschichteHistorieBeliebigesEreignisChoice.class,
          this);

  private GeschichteHistorieObservationContainment() {
    super("openEHR-EHR-OBSERVATION.story.v1");
  }

  public static GeschichteHistorieObservationContainment getInstance() {
    return new GeschichteHistorieObservationContainment();
  }
}
