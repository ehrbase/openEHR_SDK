package laborbefund.laborbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import laborbefund.shareddefinition.Language;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class LaborergebnisObservationContainment extends Containment {
  public SelectAqlField<LaborergebnisObservation> LABORERGEBNIS_OBSERVATION = new AqlFieldImp<LaborergebnisObservation>(LaborergebnisObservation.class, "", "LaborergebnisObservation", LaborergebnisObservation.class, this);

  public SelectAqlField<StandortCluster> STANDORT = new AqlFieldImp<StandortCluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[openEHR-EHR-CLUSTER.location.v1]", "standort", StandortCluster.class, this);

  public ListSelectAqlField<Cluster> TEST_DETAILS = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0110]", "testDetails", Cluster.class, this);

  public ListSelectAqlField<StandortDetailsDerTestanforderungCluster> DETAILS_DER_TESTANFORDERUNG = new ListAqlFieldImp<StandortDetailsDerTestanforderungCluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0094]", "detailsDerTestanforderung", StandortDetailsDerTestanforderungCluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(LaborergebnisObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<String> TESTMETHODE_VALUE = new AqlFieldImp<String>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0121]/value|value", "testmethodeValue", String.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0117]", "erweiterung", Cluster.class, this);

  public SelectAqlField<String> TESTMETHODE_VALUE_TREE = new AqlFieldImp<String>(LaborergebnisObservation.class, "/protocol[at0004]/items[at0121]/name|value", "testmethodeValueTree", String.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(LaborergebnisObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(LaborergebnisObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<StandortJedesEreignisChoice> JEDES_EREIGNIS = new ListAqlFieldImp<StandortJedesEreignisChoice>(LaborergebnisObservation.class, "/data[at0001]/events[at0002]", "jedesEreignis", StandortJedesEreignisChoice.class, this);

  private LaborergebnisObservationContainment() {
    super("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
  }

  public static LaborergebnisObservationContainment getInstance() {
    return new LaborergebnisObservationContainment();
  }
}
