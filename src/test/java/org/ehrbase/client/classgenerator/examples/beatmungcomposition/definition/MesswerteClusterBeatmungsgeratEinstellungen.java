package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.ventilator_settings2.v0")
public class MesswerteClusterBeatmungsgeratEinstellungen {
  @Path("/items[at0060]")
  private List<BeatmungsgeratEinstellungenMesswerteFlowRateElement> flowRate;

  @Path("/items[at0095]/value")
  private DvProportion kunstlicheAtemwegskompensation;

  @Path("/items[at0031]")
  private List<BeatmungsgeratEinstellungenMesswerteVolumenElement> volumen;

  @Path("/items[at0087]")
  private List<BeatmungsgeratEinstellungenMesswerteArtDesAuslosersElement> artDesAuslosers;

  @Path("/items[openEHR-EHR-CLUSTER.gas_delivery-oxygen.v0]")
  private InhalierteSauerstoffversorgungCluster2 inhalierteSauerstoffversorgung;

  @Path("/items[at0043]/value")
  private DvProportion expiration;

  @Path("/items[at0044]/value")
  private DvProportion total;

  @Path("/items[at0055]/value|value")
  private Boolean heaterGenutztValue;

  @Path("/items[at0054]/value|magnitude")
  private Double no2EntferntMagnitude;

  @Path("/items[at0054]/value|units")
  private String no2EntferntUnits;

  @Path("/items[at0053]/value|magnitude")
  private Double noGegebenMagnitude;

  @Path("/items[at0053]/value|units")
  private String noGegebenUnits;

  @Path("/items[at0001]/value|defining_code")
  private BeatmungsformDefiningcode beatmungsformDefiningcode;

  @Path("/items[at0144]/value|defining_code")
  private ArtDerBeatmungDefiningcode artDerBeatmungDefiningcode;

  @Path("/items[at0056]")
  private Cluster beatmungsgerat;

  @Path("/items[at0045]")
  private List<LuftsauerstoffTriggerValueElement> triggerValue;

  @Path("/items[at0015]")
  private List<LuftsauerstoffDruckElement> druck;

  @Path("/items[at0114]")
  private List<InhalierteSauerstoffversorgungBeatmungsformSubmodeElement> beatmungsformSubmode;

  @Path("/items[at0038]")
  private List<LuftsauerstoffZeitElement> zeit;

  @Path("/items[at0007]")
  private List<LuftsauerstoffFrequenzElement> frequenz;

  public void setFlowRate(List<BeatmungsgeratEinstellungenMesswerteFlowRateElement> flowRate) {
     this.flowRate = flowRate;
  }

  public List<BeatmungsgeratEinstellungenMesswerteFlowRateElement> getFlowRate() {
     return this.flowRate ;
  }

  public void setKunstlicheAtemwegskompensation(DvProportion kunstlicheAtemwegskompensation) {
     this.kunstlicheAtemwegskompensation = kunstlicheAtemwegskompensation;
  }

  public DvProportion getKunstlicheAtemwegskompensation() {
     return this.kunstlicheAtemwegskompensation ;
  }

  public void setVolumen(List<BeatmungsgeratEinstellungenMesswerteVolumenElement> volumen) {
     this.volumen = volumen;
  }

  public List<BeatmungsgeratEinstellungenMesswerteVolumenElement> getVolumen() {
     return this.volumen ;
  }

  public void setArtDesAuslosers(
      List<BeatmungsgeratEinstellungenMesswerteArtDesAuslosersElement> artDesAuslosers) {
     this.artDesAuslosers = artDesAuslosers;
  }

  public List<BeatmungsgeratEinstellungenMesswerteArtDesAuslosersElement> getArtDesAuslosers() {
     return this.artDesAuslosers ;
  }

  public void setInhalierteSauerstoffversorgung(
      InhalierteSauerstoffversorgungCluster2 inhalierteSauerstoffversorgung) {
     this.inhalierteSauerstoffversorgung = inhalierteSauerstoffversorgung;
  }

  public InhalierteSauerstoffversorgungCluster2 getInhalierteSauerstoffversorgung() {
     return this.inhalierteSauerstoffversorgung ;
  }

  public void setExpiration(DvProportion expiration) {
     this.expiration = expiration;
  }

  public DvProportion getExpiration() {
     return this.expiration ;
  }

  public void setTotal(DvProportion total) {
     this.total = total;
  }

  public DvProportion getTotal() {
     return this.total ;
  }

  public void setHeaterGenutztValue(Boolean heaterGenutztValue) {
     this.heaterGenutztValue = heaterGenutztValue;
  }

  public Boolean isHeaterGenutztValue() {
     return this.heaterGenutztValue ;
  }

  public void setNo2EntferntMagnitude(Double no2EntferntMagnitude) {
     this.no2EntferntMagnitude = no2EntferntMagnitude;
  }

  public Double getNo2EntferntMagnitude() {
     return this.no2EntferntMagnitude ;
  }

  public void setNo2EntferntUnits(String no2EntferntUnits) {
     this.no2EntferntUnits = no2EntferntUnits;
  }

  public String getNo2EntferntUnits() {
     return this.no2EntferntUnits ;
  }

  public void setNoGegebenMagnitude(Double noGegebenMagnitude) {
     this.noGegebenMagnitude = noGegebenMagnitude;
  }

  public Double getNoGegebenMagnitude() {
     return this.noGegebenMagnitude ;
  }

  public void setNoGegebenUnits(String noGegebenUnits) {
     this.noGegebenUnits = noGegebenUnits;
  }

  public String getNoGegebenUnits() {
     return this.noGegebenUnits ;
  }

  public void setBeatmungsformDefiningcode(BeatmungsformDefiningcode beatmungsformDefiningcode) {
     this.beatmungsformDefiningcode = beatmungsformDefiningcode;
  }

  public BeatmungsformDefiningcode getBeatmungsformDefiningcode() {
     return this.beatmungsformDefiningcode ;
  }

  public void setArtDerBeatmungDefiningcode(ArtDerBeatmungDefiningcode artDerBeatmungDefiningcode) {
     this.artDerBeatmungDefiningcode = artDerBeatmungDefiningcode;
  }

  public ArtDerBeatmungDefiningcode getArtDerBeatmungDefiningcode() {
     return this.artDerBeatmungDefiningcode ;
  }

  public void setBeatmungsgerat(Cluster beatmungsgerat) {
     this.beatmungsgerat = beatmungsgerat;
  }

  public Cluster getBeatmungsgerat() {
     return this.beatmungsgerat ;
  }

  public void setTriggerValue(List<LuftsauerstoffTriggerValueElement> triggerValue) {
     this.triggerValue = triggerValue;
  }

  public List<LuftsauerstoffTriggerValueElement> getTriggerValue() {
     return this.triggerValue ;
  }

  public void setDruck(List<LuftsauerstoffDruckElement> druck) {
     this.druck = druck;
  }

  public List<LuftsauerstoffDruckElement> getDruck() {
     return this.druck ;
  }

  public void setBeatmungsformSubmode(
      List<InhalierteSauerstoffversorgungBeatmungsformSubmodeElement> beatmungsformSubmode) {
     this.beatmungsformSubmode = beatmungsformSubmode;
  }

  public List<InhalierteSauerstoffversorgungBeatmungsformSubmodeElement> getBeatmungsformSubmode() {
     return this.beatmungsformSubmode ;
  }

  public void setZeit(List<LuftsauerstoffZeitElement> zeit) {
     this.zeit = zeit;
  }

  public List<LuftsauerstoffZeitElement> getZeit() {
     return this.zeit ;
  }

  public void setFrequenz(List<LuftsauerstoffFrequenzElement> frequenz) {
     this.frequenz = frequenz;
  }

  public List<LuftsauerstoffFrequenzElement> getFrequenz() {
     return this.frequenz ;
  }
}
