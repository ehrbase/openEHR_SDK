package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
public class ProVirusCluster {
  @Path("/items[at0027]/value|magnitude")
  private Long analyseergebnisReihenfolgeMagnitude;

  @Path("/items[at0024 and name/value='Virus']/value|value")
  private String virusValue;

  @Path("/items[at0001 and name/value='Nachweis']/value|value")
  private String nachweisValue;

  @Path("/items[at0001 and name/value='Viruslast (ct-Wert)']/value|magnitude")
  private Double viruslastCtWertMagnitude;

  @Path("/items[at0001 and name/value='Viruslast (ct-Wert)']/value|units")
  private String viruslastCtWertUnits;

  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  @Path("/items[at0026 and name/value='Zugehörige Laborprobe']/value")
  private DvIdentifier zugehorigeLaborprobe;

  @Path("/items[at0003]/value|value")
  private String kommentarValue;

  public void setAnalyseergebnisReihenfolgeMagnitude(Long analyseergebnisReihenfolgeMagnitude) {
     this.analyseergebnisReihenfolgeMagnitude = analyseergebnisReihenfolgeMagnitude;
  }

  public Long getAnalyseergebnisReihenfolgeMagnitude() {
     return this.analyseergebnisReihenfolgeMagnitude ;
  }

  public void setVirusValue(String virusValue) {
     this.virusValue = virusValue;
  }

  public String getVirusValue() {
     return this.virusValue ;
  }

  public void setNachweisValue(String nachweisValue) {
     this.nachweisValue = nachweisValue;
  }

  public String getNachweisValue() {
     return this.nachweisValue ;
  }

  public void setViruslastCtWertMagnitude(Double viruslastCtWertMagnitude) {
     this.viruslastCtWertMagnitude = viruslastCtWertMagnitude;
  }

  public Double getViruslastCtWertMagnitude() {
     return this.viruslastCtWertMagnitude ;
  }

  public void setViruslastCtWertUnits(String viruslastCtWertUnits) {
     this.viruslastCtWertUnits = viruslastCtWertUnits;
  }

  public String getViruslastCtWertUnits() {
     return this.viruslastCtWertUnits ;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
     this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
     return this.analyseergebnisDetails ;
  }

  public void setZugehorigeLaborprobe(DvIdentifier zugehorigeLaborprobe) {
     this.zugehorigeLaborprobe = zugehorigeLaborprobe;
  }

  public DvIdentifier getZugehorigeLaborprobe() {
     return this.zugehorigeLaborprobe ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }
}
