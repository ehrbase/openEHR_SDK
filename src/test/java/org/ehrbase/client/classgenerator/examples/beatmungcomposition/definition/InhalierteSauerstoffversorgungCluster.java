package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.gas_delivery-oxygen.v0")
public class InhalierteSauerstoffversorgungCluster {
  @Path("/items[at0017]/items[at0018]/value|defining_code")
  private MaskenbedingtesErythemDefiningcode maskenbedingtesErythemDefiningcode;

  @Path("/items[at0.97]")
  private Cluster detailsDerTrachealkanule;

  @Path("/items[at0095]/value|value")
  private Boolean eigenverabreichungValue;

  @Path("/items[at0017]/items[at0019]")
  private List<InhalierteSauerstoffversorgungLeckageDerMaskeElement> leckageDerMaske;

  @Path("/items[at0094]/value|magnitude")
  private Double atmospharischerDruckMagnitude;

  @Path("/items[at0094]/value|units")
  private String atmospharischerDruckUnits;

  @Path("/items[at0097]")
  private Cluster detailsDesTrachealtubus;

  @Path("/items[at0007]/value|defining_code")
  private BeatmungsschlauchsystemDefiningcode beatmungsschlauchsystemDefiningcode;

  @Path("/items[openEHR-EHR-CLUSTER.ambient_oxygen.v0]")
  private LuftsauerstoffCluster luftsauerstoff;

  @Path("/items[at0.107]/value|defining_code")
  private DurchflussmodusDefiningcode durchflussmodusDefiningcode;

  @Path("/items[at0093]/value|value")
  private TemporalAmount dauerDerTherapieValue;

  @Path("/items[at0022.1]/value|defining_code")
  private NameDesGasesSauerstoffDefiningcode nameDesGasesSauerstoffDefiningcode;

  @Path("/items[at0017]/items[at0004]/value|defining_code")
  private MaskengroEDefiningcode maskengroEDefiningcode;

  @Path("/items[at0003]/items[at0016]/value|value")
  private Boolean eingesetzterAtemgasbefeuchterValue;

  @Path("/items[at0001]/value|defining_code")
  private MethodeDefiningcode methodeDefiningcode;

  @Path("/items[at0002]/value|defining_code")
  private MechanismusDefiningcode mechanismusDefiningcode;

  @Path("/items[at0023]")
  private List<Cluster> gerat;

  @Path("/items[at0003]/items[at0009]/value|defining_code")
  private ArtDesAtemgasbefeuchtersDefiningcode artDesAtemgasbefeuchtersDefiningcode;

  @Path("/items[at0003]/items[at0005]/value|value")
  private TemporalAccessor datumUndUhrzeitDesLetztenWechselsDesWassersDesAtemgasbefeuchtersValue;

  public void setMaskenbedingtesErythemDefiningcode(
      MaskenbedingtesErythemDefiningcode maskenbedingtesErythemDefiningcode) {
     this.maskenbedingtesErythemDefiningcode = maskenbedingtesErythemDefiningcode;
  }

  public MaskenbedingtesErythemDefiningcode getMaskenbedingtesErythemDefiningcode() {
     return this.maskenbedingtesErythemDefiningcode ;
  }

  public void setDetailsDerTrachealkanule(Cluster detailsDerTrachealkanule) {
     this.detailsDerTrachealkanule = detailsDerTrachealkanule;
  }

  public Cluster getDetailsDerTrachealkanule() {
     return this.detailsDerTrachealkanule ;
  }

  public void setEigenverabreichungValue(Boolean eigenverabreichungValue) {
     this.eigenverabreichungValue = eigenverabreichungValue;
  }

  public Boolean isEigenverabreichungValue() {
     return this.eigenverabreichungValue ;
  }

  public void setLeckageDerMaske(
      List<InhalierteSauerstoffversorgungLeckageDerMaskeElement> leckageDerMaske) {
     this.leckageDerMaske = leckageDerMaske;
  }

  public List<InhalierteSauerstoffversorgungLeckageDerMaskeElement> getLeckageDerMaske() {
     return this.leckageDerMaske ;
  }

  public void setAtmospharischerDruckMagnitude(Double atmospharischerDruckMagnitude) {
     this.atmospharischerDruckMagnitude = atmospharischerDruckMagnitude;
  }

  public Double getAtmospharischerDruckMagnitude() {
     return this.atmospharischerDruckMagnitude ;
  }

  public void setAtmospharischerDruckUnits(String atmospharischerDruckUnits) {
     this.atmospharischerDruckUnits = atmospharischerDruckUnits;
  }

  public String getAtmospharischerDruckUnits() {
     return this.atmospharischerDruckUnits ;
  }

  public void setDetailsDesTrachealtubus(Cluster detailsDesTrachealtubus) {
     this.detailsDesTrachealtubus = detailsDesTrachealtubus;
  }

  public Cluster getDetailsDesTrachealtubus() {
     return this.detailsDesTrachealtubus ;
  }

  public void setBeatmungsschlauchsystemDefiningcode(
      BeatmungsschlauchsystemDefiningcode beatmungsschlauchsystemDefiningcode) {
     this.beatmungsschlauchsystemDefiningcode = beatmungsschlauchsystemDefiningcode;
  }

  public BeatmungsschlauchsystemDefiningcode getBeatmungsschlauchsystemDefiningcode() {
     return this.beatmungsschlauchsystemDefiningcode ;
  }

  public void setLuftsauerstoff(LuftsauerstoffCluster luftsauerstoff) {
     this.luftsauerstoff = luftsauerstoff;
  }

  public LuftsauerstoffCluster getLuftsauerstoff() {
     return this.luftsauerstoff ;
  }

  public void setDurchflussmodusDefiningcode(
      DurchflussmodusDefiningcode durchflussmodusDefiningcode) {
     this.durchflussmodusDefiningcode = durchflussmodusDefiningcode;
  }

  public DurchflussmodusDefiningcode getDurchflussmodusDefiningcode() {
     return this.durchflussmodusDefiningcode ;
  }

  public void setDauerDerTherapieValue(TemporalAmount dauerDerTherapieValue) {
     this.dauerDerTherapieValue = dauerDerTherapieValue;
  }

  public TemporalAmount getDauerDerTherapieValue() {
     return this.dauerDerTherapieValue ;
  }

  public void setNameDesGasesSauerstoffDefiningcode(
      NameDesGasesSauerstoffDefiningcode nameDesGasesSauerstoffDefiningcode) {
     this.nameDesGasesSauerstoffDefiningcode = nameDesGasesSauerstoffDefiningcode;
  }

  public NameDesGasesSauerstoffDefiningcode getNameDesGasesSauerstoffDefiningcode() {
     return this.nameDesGasesSauerstoffDefiningcode ;
  }

  public void setMaskengroEDefiningcode(MaskengroEDefiningcode maskengroEDefiningcode) {
     this.maskengroEDefiningcode = maskengroEDefiningcode;
  }

  public MaskengroEDefiningcode getMaskengroEDefiningcode() {
     return this.maskengroEDefiningcode ;
  }

  public void setEingesetzterAtemgasbefeuchterValue(Boolean eingesetzterAtemgasbefeuchterValue) {
     this.eingesetzterAtemgasbefeuchterValue = eingesetzterAtemgasbefeuchterValue;
  }

  public Boolean isEingesetzterAtemgasbefeuchterValue() {
     return this.eingesetzterAtemgasbefeuchterValue ;
  }

  public void setMethodeDefiningcode(MethodeDefiningcode methodeDefiningcode) {
     this.methodeDefiningcode = methodeDefiningcode;
  }

  public MethodeDefiningcode getMethodeDefiningcode() {
     return this.methodeDefiningcode ;
  }

  public void setMechanismusDefiningcode(MechanismusDefiningcode mechanismusDefiningcode) {
     this.mechanismusDefiningcode = mechanismusDefiningcode;
  }

  public MechanismusDefiningcode getMechanismusDefiningcode() {
     return this.mechanismusDefiningcode ;
  }

  public void setGerat(List<Cluster> gerat) {
     this.gerat = gerat;
  }

  public List<Cluster> getGerat() {
     return this.gerat ;
  }

  public void setArtDesAtemgasbefeuchtersDefiningcode(
      ArtDesAtemgasbefeuchtersDefiningcode artDesAtemgasbefeuchtersDefiningcode) {
     this.artDesAtemgasbefeuchtersDefiningcode = artDesAtemgasbefeuchtersDefiningcode;
  }

  public ArtDesAtemgasbefeuchtersDefiningcode getArtDesAtemgasbefeuchtersDefiningcode() {
     return this.artDesAtemgasbefeuchtersDefiningcode ;
  }

  public void setDatumUndUhrzeitDesLetztenWechselsDesWassersDesAtemgasbefeuchtersValue(
      TemporalAccessor datumUndUhrzeitDesLetztenWechselsDesWassersDesAtemgasbefeuchtersValue) {
     this.datumUndUhrzeitDesLetztenWechselsDesWassersDesAtemgasbefeuchtersValue = datumUndUhrzeitDesLetztenWechselsDesWassersDesAtemgasbefeuchtersValue;
  }

  public TemporalAccessor getDatumUndUhrzeitDesLetztenWechselsDesWassersDesAtemgasbefeuchtersValue(
      ) {
     return this.datumUndUhrzeitDesLetztenWechselsDesWassersDesAtemgasbefeuchtersValue ;
  }
}
