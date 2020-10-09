package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;

public class LaborergebnisObservationContainment extends Containment {
  public SelectAqlField<LaborergebnisObservation> LABORERGEBNIS_OBSERVATION = new AqlFieldImp<LaborergebnisObservation>(LaborergebnisObservation.class, "", "LaborergebnisObservation", LaborergebnisObservation.class, this);

  public ListSelectAqlField<Cluster> TEST_DETAILS = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0110]", "testDetails", Cluster.class, this);

  public SelectAqlField<PhWertCluster> PH_WERT = new AqlFieldImp<PhWertCluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='pH-Wert']", "phWert", PhWertCluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(LaborergebnisObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<SauerstoffpartialdruckCluster> SAUERSTOFFPARTIALDRUCK = new AqlFieldImp<SauerstoffpartialdruckCluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Sauerstoffpartialdruck']", "sauerstoffpartialdruck", SauerstoffpartialdruckCluster.class, this);

  public ListSelectAqlField<Cluster> MULTIMEDIA_DARSTELLUNG = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0118]", "multimediaDarstellung", Cluster.class, this);

  public SelectAqlField<LabortestBezeichnungDefiningcode> LABORTEST_BEZEICHNUNG_DEFININGCODE = new AqlFieldImp<LabortestBezeichnungDefiningcode>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|defining_code", "labortestBezeichnungDefiningcode", LabortestBezeichnungDefiningcode.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_ERFASSUNG_DER_STORFAKTOREN = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/state[at0112]/items[at0114]", "strukturierteErfassungDerStorfaktoren", Cluster.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_TESTDIAGNOSTIK = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0122]", "strukturierteTestdiagnostik", Cluster.class, this);

  public SelectAqlField<KohlendioxidpartialdruckCluster> KOHLENDIOXIDPARTIALDRUCK = new AqlFieldImp<KohlendioxidpartialdruckCluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Kohlendioxidpartialdruck']", "kohlendioxidpartialdruck", KohlendioxidpartialdruckCluster.class, this);

  public SelectAqlField<SauerstoffsattigungCluster> SAUERSTOFFSATTIGUNG = new AqlFieldImp<SauerstoffsattigungCluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Sauerstoffsättigung']", "sauerstoffsattigung", SauerstoffsattigungCluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0117]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> PROBENDETAIL = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0065]", "probendetail", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(LaborergebnisObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(LaborergebnisObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Cluster> LABOR_WELCHES_DEN_UNTERSUCHUNGSAUFTRAG_ANNIMMT = new AqlFieldImp<Cluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0017]", "laborWelchesDenUntersuchungsauftragAnnimmt", Cluster.class, this);

  private LaborergebnisObservationContainment() {
    super("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
  }

  public static LaborergebnisObservationContainment getInstance() {
    return new LaborergebnisObservationContainment();
  }
}
