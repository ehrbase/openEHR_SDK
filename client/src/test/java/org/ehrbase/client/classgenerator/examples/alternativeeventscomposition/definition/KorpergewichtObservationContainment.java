package org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition;

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

public class KorpergewichtObservationContainment extends Containment {
  public SelectAqlField<KorpergewichtObservation> KORPERGEWICHT_OBSERVATION =
      new AqlFieldImp<KorpergewichtObservation>(
          KorpergewichtObservation.class,
          "",
          "KorpergewichtObservation",
          KorpergewichtObservation.class,
          this);

  public SelectAqlField<KorpergewichtBirthEnPointEvent> BIRTH_EN =
      new AqlFieldImp<KorpergewichtBirthEnPointEvent>(
          KorpergewichtObservation.class,
          "/data[at0002]/events[at0026]",
          "birthEn",
          KorpergewichtBirthEnPointEvent.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          KorpergewichtObservation.class,
          "/data[at0002]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<Cluster> GERAT =
      new AqlFieldImp<Cluster>(
          KorpergewichtObservation.class,
          "/protocol[at0015]/items[at0020]",
          "gerat",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION_EN =
      new ListAqlFieldImp<Cluster>(
          KorpergewichtObservation.class,
          "/protocol[at0015]/items[at0027]",
          "extensionEn",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          KorpergewichtObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          KorpergewichtObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          KorpergewichtObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public ListSelectAqlField<KorpergewichtAnyEventEnChoice> ANY_EVENT_EN =
      new ListAqlFieldImp<KorpergewichtAnyEventEnChoice>(
          KorpergewichtObservation.class,
          "/data[at0002]/events[at0003]",
          "anyEventEn",
          KorpergewichtAnyEventEnChoice.class,
          this);

  private KorpergewichtObservationContainment() {
    super("openEHR-EHR-OBSERVATION.body_weight.v2");
  }

  public static KorpergewichtObservationContainment getInstance() {
    return new KorpergewichtObservationContainment();
  }
}
