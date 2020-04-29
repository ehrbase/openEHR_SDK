package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.Long;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class LaboranalytResultatClusterContainment extends Containment {
  public SelectAqlField<LaboranalytResultatCluster> LABORANALYT_RESULTAT_CLUSTER = new AqlFieldImp<LaboranalytResultatCluster>(LaboranalytResultatCluster.class, "", "LaboranalytResultatCluster", LaboranalytResultatCluster.class, this);

  public SelectAqlField<String> UNTERSUCHTER_ANALYT_VALUE = new AqlFieldImp<String>(LaboranalytResultatCluster.class, "/items[at0024]/value|value", "untersuchterAnalytValue", String.class, this);

  public SelectAqlField<String> UNTERSUCHTER_ANALYT_VALUE_NAME = new AqlFieldImp<String>(LaboranalytResultatCluster.class, "/items[at0024]/name|value", "untersuchterAnalytValueName", String.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(LaboranalytResultatCluster.class, "/items[at0003]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<LaboranalytResultatAnalytResultatChoice> ANALYT_RESULTAT = new AqlFieldImp<LaboranalytResultatAnalytResultatChoice>(LaboranalytResultatCluster.class, "/items[at0001]/value", "analytResultat", LaboranalytResultatAnalytResultatChoice.class, this);

  public SelectAqlField<DvIdentifier> PROBE = new AqlFieldImp<DvIdentifier>(LaboranalytResultatCluster.class, "/items[at0026]/value", "probe", DvIdentifier.class, this);

  public SelectAqlField<String> PROBE_VALUE = new AqlFieldImp<String>(LaboranalytResultatCluster.class, "/items[at0026]/name|value", "probeValue", String.class, this);

  public SelectAqlField<Long> ANALYSEERGEBNIS_REIHENFOLGE_MAGNITUDE = new AqlFieldImp<Long>(LaboranalytResultatCluster.class, "/items[at0027]/value|magnitude", "analyseergebnisReihenfolgeMagnitude", Long.class, this);

  public ListSelectAqlField<Cluster> ANALYSEERGEBNIS_DETAILS = new ListAqlFieldImp<Cluster>(LaboranalytResultatCluster.class, "/items[at0014]", "analyseergebnisDetails", Cluster.class, this);

  public SelectAqlField<LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase40f33492> ANALYT_RESULTAT_NAME = new AqlFieldImp<LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase40f33492>(LaboranalytResultatCluster.class, "/items[at0001]/name", "analytResultatName", LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase40f33492.class, this);

  private LaboranalytResultatClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
  }

  public static LaboranalytResultatClusterContainment getInstance() {
    return new LaboranalytResultatClusterContainment();
  }
}
