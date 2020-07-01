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

public class HistorieDerReiseObservationContainment extends Containment {
  public SelectAqlField<HistorieDerReiseObservation> HISTORIE_DER_REISE_OBSERVATION = new AqlFieldImp<HistorieDerReiseObservation>(HistorieDerReiseObservation.class, "", "HistorieDerReiseObservation", HistorieDerReiseObservation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(HistorieDerReiseObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<StandortCluster> STANDORT = new AqlFieldImp<StandortCluster>(HistorieDerReiseObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0134]/items[openEHR-EHR-CLUSTER.location.v1]", "standort", StandortCluster.class, this);

  public SelectAqlField<KurzlicheReiseDefiningcode> KURZLICHE_REISE_DEFININGCODE = new AqlFieldImp<KurzlicheReiseDefiningcode>(HistorieDerReiseObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0111]/value|defining_code", "kurzlicheReiseDefiningcode", KurzlicheReiseDefiningcode.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(HistorieDerReiseObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(HistorieDerReiseObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(HistorieDerReiseObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<String> KURZLICHE_REISE_VALUE = new AqlFieldImp<String>(HistorieDerReiseObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0111]/name|value", "kurzlicheReiseValue", String.class, this);

  public ListSelectAqlField<Cluster> EXTENSION_EN = new ListAqlFieldImp<Cluster>(HistorieDerReiseObservation.class, "/protocol[at0100]/items[at0101]", "extensionEn", Cluster.class, this);

  public ListSelectAqlField<Cluster> DETAILLIERTE_ANGABEN_ZUR_EXPOSITION = new ListAqlFieldImp<Cluster>(HistorieDerReiseObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0109]", "detaillierteAngabenZurExposition", Cluster.class, this);

  private HistorieDerReiseObservationContainment() {
    super("openEHR-EHR-OBSERVATION.travel_history.v0");
  }

  public static HistorieDerReiseObservationContainment getInstance() {
    return new HistorieDerReiseObservationContainment();
  }
}
