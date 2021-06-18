package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ProbeClusterContainment extends Containment {
  public SelectAqlField<ProbeCluster> PROBE_CLUSTER = new AqlFieldImp<ProbeCluster>(ProbeCluster.class, "", "ProbeCluster", ProbeCluster.class, this);

  public SelectAqlField<String> PROBENART_VALUE = new AqlFieldImp<String>(ProbeCluster.class, "/items[at0029]/value|value", "probenartValue", String.class, this);

  public SelectAqlField<NullFlavour> PROBENART_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0029]/null_flavour|defining_code", "probenartNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvIdentifier> LABORPROBENIDENTIFIKATOR = new AqlFieldImp<DvIdentifier>(ProbeCluster.class, "/items[at0001]/value", "laborprobenidentifikator", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> LABORPROBENIDENTIFIKATOR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0001]/null_flavour|defining_code", "laborprobenidentifikatorNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> LABOREINGANG_VALUE = new AqlFieldImp<TemporalAccessor>(ProbeCluster.class, "/items[at0034]/value|value", "laboreingangValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> LABOREINGANG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0034]/null_flavour|defining_code", "laboreingangNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> PHYSISCHE_EIGENSCHAFTEN = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0027]", "physischeEigenschaften", Cluster.class, this);

  public SelectAqlField<String> KOMMENTAR_DES_PROBENNEHMERS_VALUE = new AqlFieldImp<String>(ProbeCluster.class, "/items[at0079]/value|value", "kommentarDesProbennehmersValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_DES_PROBENNEHMERS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0079]/null_flavour|defining_code", "kommentarDesProbennehmersNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> PROBENENTNAHMESTELLE_VALUE = new AqlFieldImp<String>(ProbeCluster.class, "/items[at0087]/value|value", "probenentnahmestelleValue", String.class, this);

  public SelectAqlField<NullFlavour> PROBENENTNAHMESTELLE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0087]/null_flavour|defining_code", "probenentnahmestelleNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANATOMISCHE_PROBENENTNAHMESTELLE = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0013]", "anatomischeProbenentnahmestelle", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> PROBENENTNAHME_VALUE = new AqlFieldImp<TemporalAccessor>(ProbeCluster.class, "/items[at0015]/value|value", "probenentnahmeValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> PROBENENTNAHME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0015]/null_flavour|defining_code", "probenentnahmeNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUM_PROBENNEHMER = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0071]", "angabenZumProbennehmer", Cluster.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_ANGABEN_ZUR_PROBENNAHME = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0083]", "zusaetzlicheAngabenZurProbennahme", Cluster.class, this);

  public ListSelectAqlField<Cluster> BEHAELTER_DETAILS = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0085]", "behaelterDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUR_VERARBEITUNG = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0068]", "angabenZurVerarbeitung", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUM_TRANSPORT = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0093]", "angabenZumTransport", Cluster.class, this);

  public ListSelectAqlField<Cluster> DIGITALE_DARSTELLUNG = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0096]", "digitaleDarstellung", Cluster.class, this);

  public SelectAqlField<DvIdentifier> IDENTIFIKATOR_DER_UEBERGEORDNETEN_PROBE = new AqlFieldImp<DvIdentifier>(ProbeCluster.class, "/items[at0003]/value", "identifikatorDerUebergeordnetenProbe", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> IDENTIFIKATOR_DER_UEBERGEORDNETEN_PROBE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0003]/null_flavour|defining_code", "identifikatorDerUebergeordnetenProbeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(ProbeCluster.class, "/items[at0045]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0045]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ProbeCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ProbeClusterContainment() {
    super("openEHR-EHR-CLUSTER.specimen.v1");
  }

  public static ProbeClusterContainment getInstance() {
    return new ProbeClusterContainment();
  }
}
