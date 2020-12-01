package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
public class LaboranalytResultatCluster {
  @Path("/items[at0024 and name/value='Antibiotikum']/value|defining_code")
  private AntibiotikumDefiningCode antibiotikumDefiningCode;

  @Path("/items[at0001 and name/value='Minimale Hemmkonzentration']/value|magnitude")
  private Double minimaleHemmkonzentrationMagnitude;

  @Path("/items[at0001 and name/value='Minimale Hemmkonzentration']/value|units")
  private String minimaleHemmkonzentrationUnits;

  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  @Path("/items[at0004 and name/value='Resistenz']/value|value")
  private String resistenzValue;

  @Path("/items[at0003]/value|value")
  private String kommentarValue;

  public void setAntibiotikumDefiningCode(AntibiotikumDefiningCode antibiotikumDefiningCode) {
     this.antibiotikumDefiningCode = antibiotikumDefiningCode;
  }

  public AntibiotikumDefiningCode getAntibiotikumDefiningCode() {
     return this.antibiotikumDefiningCode ;
  }

  public void setMinimaleHemmkonzentrationMagnitude(Double minimaleHemmkonzentrationMagnitude) {
     this.minimaleHemmkonzentrationMagnitude = minimaleHemmkonzentrationMagnitude;
  }

  public Double getMinimaleHemmkonzentrationMagnitude() {
     return this.minimaleHemmkonzentrationMagnitude ;
  }

  public void setMinimaleHemmkonzentrationUnits(String minimaleHemmkonzentrationUnits) {
     this.minimaleHemmkonzentrationUnits = minimaleHemmkonzentrationUnits;
  }

  public String getMinimaleHemmkonzentrationUnits() {
     return this.minimaleHemmkonzentrationUnits ;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
     this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
     return this.analyseergebnisDetails ;
  }

  public void setResistenzValue(String resistenzValue) {
     this.resistenzValue = resistenzValue;
  }

  public String getResistenzValue() {
     return this.resistenzValue ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }
}
