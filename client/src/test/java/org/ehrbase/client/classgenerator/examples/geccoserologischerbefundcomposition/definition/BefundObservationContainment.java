package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

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

public class BefundObservationContainment extends Containment {
  public SelectAqlField<BefundObservation> BEFUND_OBSERVATION =
      new AqlFieldImp<BefundObservation>(
          BefundObservation.class, "", "BefundObservation", BefundObservation.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          BefundObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<Cluster> LABOR_WELCHES_DEN_UNTERSUCHUNGSAUFTRAG_ANNIMMT =
      new AqlFieldImp<Cluster>(
          BefundObservation.class,
          "/protocol[at0004]/items[at0017]",
          "laborWelchesDenUntersuchungsauftragAnnimmt",
          Cluster.class,
          this);

  public SelectAqlField<AnforderungDefiningCode> ANFORDERUNG_DEFINING_CODE =
      new AqlFieldImp<AnforderungDefiningCode>(
          BefundObservation.class,
          "/protocol[at0004]/items[at0094]/items[at0106]/value|defining_code",
          "anforderungDefiningCode",
          AnforderungDefiningCode.class,
          this);

  public SelectAqlField<Cluster> EINSENDER =
      new AqlFieldImp<Cluster>(
          BefundObservation.class,
          "/protocol[at0004]/items[at0094]/items[at0090]",
          "einsender",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> VERTEILERLISTE =
      new ListAqlFieldImp<Cluster>(
          BefundObservation.class,
          "/protocol[at0004]/items[at0094]/items[at0035]",
          "verteilerliste",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> TEST_DETAILS =
      new ListAqlFieldImp<Cluster>(
          BefundObservation.class,
          "/protocol[at0004]/items[at0110]",
          "testDetails",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          BefundObservation.class,
          "/protocol[at0004]/items[at0117]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          BefundObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          BefundObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          BefundObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public ListSelectAqlField<BefundJedesEreignisChoice> JEDES_EREIGNIS =
      new ListAqlFieldImp<BefundJedesEreignisChoice>(
          BefundObservation.class,
          "/data[at0001]/events[at0002]",
          "jedesEreignis",
          BefundJedesEreignisChoice.class,
          this);

  private BefundObservationContainment() {
    super("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
  }

  public static BefundObservationContainment getInstance() {
    return new BefundObservationContainment();
  }
}
