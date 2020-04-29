package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class LaborergebnisObservationContainment extends Containment {
  public SelectAqlField<LaborergebnisObservation> LABORERGEBNIS_OBSERVATION = new AqlFieldImp<LaborergebnisObservation>(LaborergebnisObservation.class, "", "LaborergebnisObservation", LaborergebnisObservation.class, this);

  public ListSelectAqlField<Cluster> TEST_DETAILS = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0110]", "testDetails", Cluster.class, this);

  public ListSelectAqlField<ProbeCluster> PROBE = new ListAqlFieldImp<ProbeCluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.specimen.v1]", "probe", ProbeCluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(LaborergebnisObservation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<LabortestPanelCluster> LABORTEST_PANEL = new ListAqlFieldImp<LabortestPanelCluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]", "labortestPanel", LabortestPanelCluster.class, this);

  public ListSelectAqlField<Cluster> MULTIMEDIA_DARSTELLUNG = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0118]", "multimediaDarstellung", Cluster.class, this);

  public SelectAqlField<DvIdentifier> EINSENDENDEN_SYSTEMS = new AqlFieldImp<DvIdentifier>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0094]/items[at0062]/value", "einsendendenSystems", DvIdentifier.class, this);

  public SelectAqlField<DvIdentifier> AUFTRAGS_ID_EMPFANGER = new AqlFieldImp<DvIdentifier>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0094]/items[at0063]/value", "auftragsIdEmpfanger", DvIdentifier.class, this);

  public SelectAqlField<StandortCluster> STANDORT = new AqlFieldImp<StandortCluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[openEHR-EHR-CLUSTER.location.v1]", "standort", StandortCluster.class, this);

  public SelectAqlField<String> LABORTEST_BEZEICHNUNG_VALUE = new AqlFieldImp<String>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value", "labortestBezeichnungValue", String.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_ERFASSUNG_DER_STORFAKTOREN = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/state[at0112]/items[at0114]", "strukturierteErfassungDerStorfaktoren", Cluster.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_TESTDIAGNOSTIK = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0122]", "strukturierteTestdiagnostik", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0117]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<StandortOriginalerNameDerAngefordertenTestungElement> ORIGINALER_NAME_DER_ANGEFORDERTEN_TESTUNG = new ListAqlFieldImp<StandortOriginalerNameDerAngefordertenTestungElement>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0094]/items[at0106]", "originalerNameDerAngefordertenTestung", StandortOriginalerNameDerAngefordertenTestungElement.class, this);

  public SelectAqlField<StandortCluster2> STANDORT_ITEMS_OPENEHR_EHR_CLUSTER_LOCATION_V1 = new AqlFieldImp<StandortCluster2>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1]", "standortItemsOpenehrEhrClusterLocationV1", StandortCluster2.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(LaborergebnisObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(LaborergebnisObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> VERTEILERLISTE = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0094]/items[at0035]", "verteilerliste", Cluster.class, this);

  private LaborergebnisObservationContainment() {
    super("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
  }

  public static LaborergebnisObservationContainment getInstance() {
    return new LaborergebnisObservationContainment();
  }
}
