package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class HistoryObservationContainment extends Containment {
  public SelectAqlField<HistoryObservation> HISTORY_OBSERVATION = new AqlFieldImp<HistoryObservation>(HistoryObservation.class, "", "HistoryObservation", HistoryObservation.class, this);

  public SelectAqlField<String> STORY_VALUE = new AqlFieldImp<String>(HistoryObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|value", "storyValue", String.class, this);

  public ListSelectAqlField<Cluster> STRUCTURED_DETAIL = new ListAqlFieldImp<Cluster>(HistoryObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0006]", "structuredDetail", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(HistoryObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(HistoryObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(HistoryObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(HistoryObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(HistoryObservation.class, "/protocol[at0007]/items[at0008]", "extension", Cluster.class, this);

  private HistoryObservationContainment() {
    super("openEHR-EHR-OBSERVATION.story.v1");
  }

  public static HistoryObservationContainment getInstance() {
    return new HistoryObservationContainment();
  }
}
