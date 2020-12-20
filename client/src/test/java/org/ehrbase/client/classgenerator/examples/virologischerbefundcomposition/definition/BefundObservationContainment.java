package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
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

  public SelectAqlField<String> LABORTEST_BEZEICHNUNG_VALUE =
      new AqlFieldImp<String>(
          BefundObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value",
          "labortestBezeichnungValue",
          String.class,
          this);

  public ListSelectAqlField<ProbeCluster> PROBE =
      new ListAqlFieldImp<ProbeCluster>(
          BefundObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.specimen.v1]",
          "probe",
          ProbeCluster.class,
          this);

  public ListSelectAqlField<KulturCluster> KULTUR =
      new ListAqlFieldImp<KulturCluster>(
          BefundObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]",
          "kultur",
          KulturCluster.class,
          this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_TESTDIAGNOSTIK =
      new ListAqlFieldImp<Cluster>(
          BefundObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0122]",
          "strukturierteTestdiagnostik",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> MULTIMEDIA_DARSTELLUNG =
      new ListAqlFieldImp<Cluster>(
          BefundObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0118]",
          "multimediaDarstellung",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_ERFASSUNG_DER_STORFAKTOREN =
      new ListAqlFieldImp<Cluster>(
          BefundObservation.class,
          "/data[at0001]/events[at0002]/state[at0112]/items[at0114]",
          "strukturierteErfassungDerStorfaktoren",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          BefundObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          BefundObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<EmpfangerstandortCluster> EMPFANGERSTANDORT =
      new AqlFieldImp<EmpfangerstandortCluster>(
          BefundObservation.class,
          "/protocol[at0004]/items[openEHR-EHR-CLUSTER.location.v1]",
          "empfangerstandort",
          EmpfangerstandortCluster.class,
          this);

  public ListSelectAqlField<BefundAnforderungElement> ANFORDERUNG =
      new ListAqlFieldImp<BefundAnforderungElement>(
          BefundObservation.class,
          "/protocol[at0004]/items[at0094]/items[at0106]",
          "anforderung",
          BefundAnforderungElement.class,
          this);

  public SelectAqlField<DvIdentifier> AUFTRAGS_ID_DES_ANFORDERNDEN_EINSENDENDEN_SYSTEMS =
      new AqlFieldImp<DvIdentifier>(
          BefundObservation.class,
          "/protocol[at0004]/items[at0094]/items[at0062]/value",
          "auftragsIdDesAnforderndenEinsendendenSystems",
          DvIdentifier.class,
          this);

  public SelectAqlField<DvIdentifier> AUFTRAGS_ID_EMPFANGER =
      new AqlFieldImp<DvIdentifier>(
          BefundObservation.class,
          "/protocol[at0004]/items[at0094]/items[at0063]/value",
          "auftragsIdEmpfanger",
          DvIdentifier.class,
          this);

  public SelectAqlField<EinsenderstandortCluster> EINSENDERSTANDORT =
      new AqlFieldImp<EinsenderstandortCluster>(
          BefundObservation.class,
          "/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1]",
          "einsenderstandort",
          EinsenderstandortCluster.class,
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

  private BefundObservationContainment() {
    super("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
  }

  public static BefundObservationContainment getInstance() {
    return new BefundObservationContainment();
  }
}
