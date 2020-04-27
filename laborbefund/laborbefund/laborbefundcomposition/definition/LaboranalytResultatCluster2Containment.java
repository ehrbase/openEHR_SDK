package laborbefund.laborbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class LaboranalytResultatCluster2Containment extends Containment {
  public SelectAqlField<LaboranalytResultatCluster2> LABORANALYT_RESULTAT_CLUSTER2 = new AqlFieldImp<LaboranalytResultatCluster2>(LaboranalytResultatCluster2.class, "", "LaboranalytResultatCluster2", LaboranalytResultatCluster2.class, this);

  public SelectAqlField<LaboranalytResultatErgebnisStatusChoiceOrgEhrbaseEhrEncodeWrappersSnakecase77cf3f8b> ERGEBNIS_STATUS = new AqlFieldImp<LaboranalytResultatErgebnisStatusChoiceOrgEhrbaseEhrEncodeWrappersSnakecase77cf3f8b>(LaboranalytResultatCluster2.class, "/items[at0005]/value", "ergebnisStatus", LaboranalytResultatErgebnisStatusChoiceOrgEhrbaseEhrEncodeWrappersSnakecase77cf3f8b.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_ERGEBNIS_STATUS_VALUE = new AqlFieldImp<TemporalAccessor>(LaboranalytResultatCluster2.class, "/items[at0006]/value|value", "zeitpunktErgebnisStatusValue", TemporalAccessor.class, this);

  public SelectAqlField<String> UNTERSUCHTER_ANALYT_VALUE = new AqlFieldImp<String>(LaboranalytResultatCluster2.class, "/items[at0024]/value|value", "untersuchterAnalytValue", String.class, this);

  public SelectAqlField<String> REFERENZBEREICHS_HINWEISE_VALUE = new AqlFieldImp<String>(LaboranalytResultatCluster2.class, "/items[at0004]/value|value", "referenzbereichsHinweiseValue", String.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(LaboranalytResultatCluster2.class, "/items[at0003]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<String> PROBE_VALUE = new AqlFieldImp<String>(LaboranalytResultatCluster2.class, "/items[at0026]/name|value", "probeValue", String.class, this);

  public SelectAqlField<String> ANALYT_RESULTAT_VALUE = new AqlFieldImp<String>(LaboranalytResultatCluster2.class, "/items[at0001]/name|value", "analytResultatValue", String.class, this);

  public SelectAqlField<String> ZEITPUNKT_ERGEBNIS_STATUS_VALUE_NAME = new AqlFieldImp<String>(LaboranalytResultatCluster2.class, "/items[at0006]/name|value", "zeitpunktErgebnisStatusValueName", String.class, this);

  public SelectAqlField<String> UNTERSUCHTER_ANALYT_VALUE_NAME = new AqlFieldImp<String>(LaboranalytResultatCluster2.class, "/items[at0024]/name|value", "untersuchterAnalytValueName", String.class, this);

  public SelectAqlField<LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase1df98368> ANALYT_RESULTAT = new AqlFieldImp<LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase1df98368>(LaboranalytResultatCluster2.class, "/items[at0001]/value", "analytResultat", LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase1df98368.class, this);

  public SelectAqlField<LaboranalytResultatProbeChoiceOrgEhrbaseEhrEncodeWrappersSnakecase21ca139c> PROBE = new AqlFieldImp<LaboranalytResultatProbeChoiceOrgEhrbaseEhrEncodeWrappersSnakecase21ca139c>(LaboranalytResultatCluster2.class, "/items[at0026]/value", "probe", LaboranalytResultatProbeChoiceOrgEhrbaseEhrEncodeWrappersSnakecase21ca139c.class, this);

  public SelectAqlField<String> REFERENZBEREICHS_HINWEISE_VALUE_NAME = new AqlFieldImp<String>(LaboranalytResultatCluster2.class, "/items[at0004]/name|value", "referenzbereichsHinweiseValueName", String.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_VALIDATION_VALUE = new AqlFieldImp<TemporalAccessor>(LaboranalytResultatCluster2.class, "/items[at0025]/value|value", "zeitpunktValidationValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> ANALYSEERGEBNIS_DETAILS = new ListAqlFieldImp<Cluster>(LaboranalytResultatCluster2.class, "/items[at0014]", "analyseergebnisDetails", Cluster.class, this);

  private LaboranalytResultatCluster2Containment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
  }

  public static LaboranalytResultatCluster2Containment getInstance() {
    return new LaboranalytResultatCluster2Containment();
  }
}
