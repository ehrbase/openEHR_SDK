package laborbefund.laborbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class ProbeCluster2Containment extends Containment {
  public SelectAqlField<ProbeCluster2> PROBE_CLUSTER2 = new AqlFieldImp<ProbeCluster2>(ProbeCluster2.class, "", "ProbeCluster2", ProbeCluster2.class, this);

  public SelectAqlField<String> KOMMENTAR_DES_PROBENNEHMERS_VALUE = new AqlFieldImp<String>(ProbeCluster2.class, "/items[at0079]/value|value", "kommentarDesProbennehmersValue", String.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUM_PROBENNEHMER = new ListAqlFieldImp<Cluster>(ProbeCluster2.class, "/items[at0071]", "angabenZumProbennehmer", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUM_TRANSPORT = new ListAqlFieldImp<Cluster>(ProbeCluster2.class, "/items[at0093]", "angabenZumTransport", Cluster.class, this);

  public SelectAqlField<String> PROBENENTNAHMESTELLE_VALUE = new AqlFieldImp<String>(ProbeCluster2.class, "/items[at0087]/value|value", "probenentnahmestelleValue", String.class, this);

  public ListSelectAqlField<Cluster> ZUSATZLICHE_ANGABEN_ZUR_PROBENNAHME = new ListAqlFieldImp<Cluster>(ProbeCluster2.class, "/items[at0083]", "zusatzlicheAngabenZurProbennahme", Cluster.class, this);

  public ListSelectAqlField<Cluster> BEHALTER_DETAILS = new ListAqlFieldImp<Cluster>(ProbeCluster2.class, "/items[at0085]", "behalterDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> DIGITALE_DARSTELLUNG = new ListAqlFieldImp<Cluster>(ProbeCluster2.class, "/items[at0096]", "digitaleDarstellung", Cluster.class, this);

  public SelectAqlField<DvIdentifier> IDENTIFIKATOR_DER_UBERGEORDNETEN_PROBE = new AqlFieldImp<DvIdentifier>(ProbeCluster2.class, "/items[at0003]/value", "identifikatorDerUbergeordnetenProbe", DvIdentifier.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DES_PROBENEINGANGS_VALUE = new AqlFieldImp<TemporalAccessor>(ProbeCluster2.class, "/items[at0034]/value|value", "zeitpunktDesProbeneingangsValue", TemporalAccessor.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(ProbeCluster2.class, "/items[at0045]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<String> ZEITPUNKT_DES_PROBENEINGANGS_VALUE_NAME = new AqlFieldImp<String>(ProbeCluster2.class, "/items[at0034]/name|value", "zeitpunktDesProbeneingangsValueName", String.class, this);

  public SelectAqlField<String> ZEITPUNKT_DER_PROBENENTNAHME_VALUE = new AqlFieldImp<String>(ProbeCluster2.class, "/items[at0015]/name|value", "zeitpunktDerProbenentnahmeValue", String.class, this);

  public SelectAqlField<String> PROBENART_VALUE = new AqlFieldImp<String>(ProbeCluster2.class, "/items[at0029]/value|value", "probenartValue", String.class, this);

  public SelectAqlField<DvIdentifier> LABORPROBENIDENTIFIKATOR = new AqlFieldImp<DvIdentifier>(ProbeCluster2.class, "/items[at0001]/value", "laborprobenidentifikator", DvIdentifier.class, this);

  public ListSelectAqlField<Cluster> ANATOMISCHE_PROBENENTNAHMESTELLE = new ListAqlFieldImp<Cluster>(ProbeCluster2.class, "/items[at0013]", "anatomischeProbenentnahmestelle", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUR_VERARBEITUNG = new ListAqlFieldImp<Cluster>(ProbeCluster2.class, "/items[at0068]", "angabenZurVerarbeitung", Cluster.class, this);

  public ListSelectAqlField<Cluster> PHYSISCHE_EIGENSCHAFTEN = new ListAqlFieldImp<Cluster>(ProbeCluster2.class, "/items[at0027]", "physischeEigenschaften", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DER_PROBENENTNAHME_VALUE_VALUE = new AqlFieldImp<TemporalAccessor>(ProbeCluster2.class, "/items[at0015]/value|value", "zeitpunktDerProbenentnahmeValueValue", TemporalAccessor.class, this);

  private ProbeCluster2Containment() {
    super("openEHR-EHR-CLUSTER.specimen.v1");
  }

  public static ProbeCluster2Containment getInstance() {
    return new ProbeCluster2Containment();
  }
}
