package laborbefund.laborbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("POINT_EVENT")
public class StandortJedesEreignisPointEvent implements StandortJedesEreignisChoice {
  @Path("/data[at0003]/items[at0005]/value|value")
  private String labortestBezeichnungValue;

  @Path("/data[at0003]/items[at0101]/value|value")
  private String kommentarValue;

  @Path("/data[at0003]/items[openEHR-EHR-CLUSTER.specimen.v1]")
  private List<ProbeCluster> probe;

  @Path("/data[at0003]/items[at0118]")
  private List<Cluster> multimediaDarstellung;

  @Path("/state[at0112]/items[at0114]")
  private List<Cluster> strukturierteErfassungDerStorfaktoren;

  @Path("/data[at0003]/items[at0057]/name|value")
  private String schlussfolgerungValue;

  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]")
  private List<LaboranalytResultatCluster> laboranalytResultat;

  @Path("/data[at0003]/items[at0073]/name|value")
  private String gesamtteststatusValue;

  @Path("/data[at0003]/items[at0073]/value|defining_code")
  private GesamtteststatusDefiningcode gesamtteststatusDefiningcode;

  @Path("/data[at0003]/items[at0005]/name|value")
  private String labortestBezeichnungValueTree;

  @Path("/data[at0003]/items[at0122]")
  private List<Cluster> strukturierteTestdiagnostik;

  @Path("/data[at0003]/items[at0057]/value|value")
  private String schlussfolgerungValueTree;

  public void setLabortestBezeichnungValue(String labortestBezeichnungValue) {
     this.labortestBezeichnungValue = labortestBezeichnungValue;
  }

  public String getLabortestBezeichnungValue() {
     return this.labortestBezeichnungValue ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setProbe(List<ProbeCluster> probe) {
     this.probe = probe;
  }

  public List<ProbeCluster> getProbe() {
     return this.probe ;
  }

  public void setMultimediaDarstellung(List<Cluster> multimediaDarstellung) {
     this.multimediaDarstellung = multimediaDarstellung;
  }

  public List<Cluster> getMultimediaDarstellung() {
     return this.multimediaDarstellung ;
  }

  public void setStrukturierteErfassungDerStorfaktoren(
      List<Cluster> strukturierteErfassungDerStorfaktoren) {
     this.strukturierteErfassungDerStorfaktoren = strukturierteErfassungDerStorfaktoren;
  }

  public List<Cluster> getStrukturierteErfassungDerStorfaktoren() {
     return this.strukturierteErfassungDerStorfaktoren ;
  }

  public void setSchlussfolgerungValue(String schlussfolgerungValue) {
     this.schlussfolgerungValue = schlussfolgerungValue;
  }

  public String getSchlussfolgerungValue() {
     return this.schlussfolgerungValue ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setLaboranalytResultat(List<LaboranalytResultatCluster> laboranalytResultat) {
     this.laboranalytResultat = laboranalytResultat;
  }

  public List<LaboranalytResultatCluster> getLaboranalytResultat() {
     return this.laboranalytResultat ;
  }

  public void setGesamtteststatusValue(String gesamtteststatusValue) {
     this.gesamtteststatusValue = gesamtteststatusValue;
  }

  public String getGesamtteststatusValue() {
     return this.gesamtteststatusValue ;
  }

  public void setGesamtteststatusDefiningcode(
      GesamtteststatusDefiningcode gesamtteststatusDefiningcode) {
     this.gesamtteststatusDefiningcode = gesamtteststatusDefiningcode;
  }

  public GesamtteststatusDefiningcode getGesamtteststatusDefiningcode() {
     return this.gesamtteststatusDefiningcode ;
  }

  public void setLabortestBezeichnungValueTree(String labortestBezeichnungValueTree) {
     this.labortestBezeichnungValueTree = labortestBezeichnungValueTree;
  }

  public String getLabortestBezeichnungValueTree() {
     return this.labortestBezeichnungValueTree ;
  }

  public void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik) {
     this.strukturierteTestdiagnostik = strukturierteTestdiagnostik;
  }

  public List<Cluster> getStrukturierteTestdiagnostik() {
     return this.strukturierteTestdiagnostik ;
  }

  public void setSchlussfolgerungValueTree(String schlussfolgerungValueTree) {
     this.schlussfolgerungValueTree = schlussfolgerungValueTree;
  }

  public String getSchlussfolgerungValueTree() {
     return this.schlussfolgerungValueTree ;
  }
}
