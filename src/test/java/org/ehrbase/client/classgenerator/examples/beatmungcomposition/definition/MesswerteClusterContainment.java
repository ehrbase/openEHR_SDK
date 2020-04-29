package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class MesswerteClusterContainment extends Containment {
  public SelectAqlField<MesswerteCluster> MESSWERTE_CLUSTER = new AqlFieldImp<MesswerteCluster>(MesswerteCluster.class, "", "MesswerteCluster", MesswerteCluster.class, this);

  public ListSelectAqlField<BeatmungsgeratEinstellungenMesswerteFlowRateElement> FLOW_RATE = new ListAqlFieldImp<BeatmungsgeratEinstellungenMesswerteFlowRateElement>(MesswerteCluster.class, "/items[at0060]", "flowRate", BeatmungsgeratEinstellungenMesswerteFlowRateElement.class, this);

  public SelectAqlField<DvProportion> KUNSTLICHE_ATEMWEGSKOMPENSATION = new AqlFieldImp<DvProportion>(MesswerteCluster.class, "/items[at0095]/value", "kunstlicheAtemwegskompensation", DvProportion.class, this);

  public ListSelectAqlField<BeatmungsgeratEinstellungenMesswerteVolumenElement> VOLUMEN = new ListAqlFieldImp<BeatmungsgeratEinstellungenMesswerteVolumenElement>(MesswerteCluster.class, "/items[at0031]", "volumen", BeatmungsgeratEinstellungenMesswerteVolumenElement.class, this);

  public ListSelectAqlField<BeatmungsgeratEinstellungenMesswerteArtDesAuslosersElement> ART_DES_AUSLOSERS = new ListAqlFieldImp<BeatmungsgeratEinstellungenMesswerteArtDesAuslosersElement>(MesswerteCluster.class, "/items[at0087]", "artDesAuslosers", BeatmungsgeratEinstellungenMesswerteArtDesAuslosersElement.class, this);

  public SelectAqlField<InhalierteSauerstoffversorgungCluster> INHALIERTE_SAUERSTOFFVERSORGUNG = new AqlFieldImp<InhalierteSauerstoffversorgungCluster>(MesswerteCluster.class, "/items[openEHR-EHR-CLUSTER.gas_delivery-oxygen.v0]", "inhalierteSauerstoffversorgung", InhalierteSauerstoffversorgungCluster.class, this);

  public SelectAqlField<DvProportion> EXPIRATION = new AqlFieldImp<DvProportion>(MesswerteCluster.class, "/items[at0043]/value", "expiration", DvProportion.class, this);

  public SelectAqlField<DvProportion> TOTAL = new AqlFieldImp<DvProportion>(MesswerteCluster.class, "/items[at0044]/value", "total", DvProportion.class, this);

  public SelectAqlField<Boolean> HEATER_GENUTZT_VALUE = new AqlFieldImp<Boolean>(MesswerteCluster.class, "/items[at0055]/value|value", "heaterGenutztValue", Boolean.class, this);

  public SelectAqlField<Double> NO2_ENTFERNT_MAGNITUDE = new AqlFieldImp<Double>(MesswerteCluster.class, "/items[at0054]/value|magnitude", "no2EntferntMagnitude", Double.class, this);

  public SelectAqlField<String> NO2_ENTFERNT_UNITS = new AqlFieldImp<String>(MesswerteCluster.class, "/items[at0054]/value|units", "no2EntferntUnits", String.class, this);

  public SelectAqlField<Double> NO_GEGEBEN_MAGNITUDE = new AqlFieldImp<Double>(MesswerteCluster.class, "/items[at0053]/value|magnitude", "noGegebenMagnitude", Double.class, this);

  public SelectAqlField<String> NO_GEGEBEN_UNITS = new AqlFieldImp<String>(MesswerteCluster.class, "/items[at0053]/value|units", "noGegebenUnits", String.class, this);

  public SelectAqlField<BeatmungsformDefiningcode> BEATMUNGSFORM_DEFININGCODE = new AqlFieldImp<BeatmungsformDefiningcode>(MesswerteCluster.class, "/items[at0001]/value|defining_code", "beatmungsformDefiningcode", BeatmungsformDefiningcode.class, this);

  public SelectAqlField<ArtDerBeatmungDefiningcode> ART_DER_BEATMUNG_DEFININGCODE = new AqlFieldImp<ArtDerBeatmungDefiningcode>(MesswerteCluster.class, "/items[at0144]/value|defining_code", "artDerBeatmungDefiningcode", ArtDerBeatmungDefiningcode.class, this);

  public SelectAqlField<Cluster> BEATMUNGSGERAT = new AqlFieldImp<Cluster>(MesswerteCluster.class, "/items[at0056]", "beatmungsgerat", Cluster.class, this);

  public ListSelectAqlField<LuftsauerstoffTriggerValueElement> TRIGGER_VALUE = new ListAqlFieldImp<LuftsauerstoffTriggerValueElement>(MesswerteCluster.class, "/items[at0045]", "triggerValue", LuftsauerstoffTriggerValueElement.class, this);

  public ListSelectAqlField<LuftsauerstoffDruckElement> DRUCK = new ListAqlFieldImp<LuftsauerstoffDruckElement>(MesswerteCluster.class, "/items[at0015]", "druck", LuftsauerstoffDruckElement.class, this);

  public ListSelectAqlField<LuftsauerstoffBeatmungsformSubmodeElement> BEATMUNGSFORM_SUBMODE = new ListAqlFieldImp<LuftsauerstoffBeatmungsformSubmodeElement>(MesswerteCluster.class, "/items[at0114]", "beatmungsformSubmode", LuftsauerstoffBeatmungsformSubmodeElement.class, this);

  public ListSelectAqlField<LuftsauerstoffZeitElement> ZEIT = new ListAqlFieldImp<LuftsauerstoffZeitElement>(MesswerteCluster.class, "/items[at0038]", "zeit", LuftsauerstoffZeitElement.class, this);

  public ListSelectAqlField<LuftsauerstoffFrequenzElement> FREQUENZ = new ListAqlFieldImp<LuftsauerstoffFrequenzElement>(MesswerteCluster.class, "/items[at0007]", "frequenz", LuftsauerstoffFrequenzElement.class, this);

  private MesswerteClusterContainment() {
    super("openEHR-EHR-CLUSTER.ventilator_settings2.v0");
  }

  public static MesswerteClusterContainment getInstance() {
    return new MesswerteClusterContainment();
  }
}
