package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;

public class HistorieObservationContainment extends Containment {
  public SelectAqlField<HistorieObservation> HISTORIE_OBSERVATION = new AqlFieldImp<HistorieObservation>(HistorieObservation.class, "", "HistorieObservation", HistorieObservation.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(HistorieObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(HistorieObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<GeschichteHistorieBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS = new ListAqlFieldImp<GeschichteHistorieBeliebigesEreignisChoice>(HistorieObservation.class, "/data[at0001]/events[at0002]", "beliebigesEreignis", GeschichteHistorieBeliebigesEreignisChoice.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(HistorieObservation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(HistorieObservation.class, "/protocol[at0007]/items[at0008]", "erweiterung", Cluster.class, this);

  private HistorieObservationContainment() {
    super("openEHR-EHR-OBSERVATION.story.v1");
  }

  public static HistorieObservationContainment getInstance() {
    return new HistorieObservationContainment();
  }
}
