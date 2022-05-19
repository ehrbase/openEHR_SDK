package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class LaborergebnisObservationContainment extends Containment {
  public SelectAqlField<LaborergebnisObservation> LABORERGEBNIS_OBSERVATION = new AqlFieldImp<LaborergebnisObservation>(LaborergebnisObservation.class, "", "LaborergebnisObservation", LaborergebnisObservation.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(LaborergebnisObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<LaborbereichCluster> LABORBEREICH = new AqlFieldImp<LaborbereichCluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[openEHR-EHR-CLUSTER.location.v1]", "laborbereich", LaborbereichCluster.class, this);

  public ListSelectAqlField<LaborergebnisDetailsDerTestanforderungCluster> DETAILS_DER_TESTANFORDERUNG = new ListAqlFieldImp<LaborergebnisDetailsDerTestanforderungCluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0094]", "detailsDerTestanforderung", LaborergebnisDetailsDerTestanforderungCluster.class, this);

  public SelectAqlField<String> UNTERSUCHUNGSMETHODE_VALUE = new AqlFieldImp<String>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0121]/value|value", "untersuchungsmethodeValue", String.class, this);

  public SelectAqlField<NullFlavour> UNTERSUCHUNGSMETHODE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0121]/null_flavour|defining_code", "untersuchungsmethodeNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> TEST_DETAILS = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0110]", "testDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0117]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(LaborergebnisObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(LaborergebnisObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(LaborergebnisObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public ListSelectAqlField<LaborergebnisJedesEreignisChoice> JEDES_EREIGNIS = new ListAqlFieldImp<LaborergebnisJedesEreignisChoice>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]", "jedesEreignis", LaborergebnisJedesEreignisChoice.class, this);

  private LaborergebnisObservationContainment() {
    super("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
  }

  public static LaborergebnisObservationContainment getInstance() {
    return new LaborergebnisObservationContainment();
  }
}
