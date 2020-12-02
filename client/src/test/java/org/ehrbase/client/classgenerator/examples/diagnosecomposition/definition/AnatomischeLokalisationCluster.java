package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.anatomical_location.v1")
public class AnatomischeLokalisationCluster {
  @Path("/items[at0001]/value|value")
  private String nameDerKorperstelleValue;

  @Path("/items[at0002]/value|defining_code")
  private LateralitatDefiningCode lateralitatDefiningCode;

  @Path("/items[at0053]")
  private List<Cluster> alternativeStruktur;

  @Path("/items[at0054]")
  private List<Cluster> multimedialeDarstellung;

  public void setNameDerKorperstelleValue(String nameDerKorperstelleValue) {
     this.nameDerKorperstelleValue = nameDerKorperstelleValue;
  }

  public String getNameDerKorperstelleValue() {
     return this.nameDerKorperstelleValue ;
  }

  public void setLateralitatDefiningCode(LateralitatDefiningCode lateralitatDefiningCode) {
     this.lateralitatDefiningCode = lateralitatDefiningCode;
  }

  public LateralitatDefiningCode getLateralitatDefiningCode() {
     return this.lateralitatDefiningCode ;
  }

  public void setAlternativeStruktur(List<Cluster> alternativeStruktur) {
     this.alternativeStruktur = alternativeStruktur;
  }

  public List<Cluster> getAlternativeStruktur() {
     return this.alternativeStruktur ;
  }

  public void setMultimedialeDarstellung(List<Cluster> multimedialeDarstellung) {
     this.multimedialeDarstellung = multimedialeDarstellung;
  }

  public List<Cluster> getMultimedialeDarstellung() {
     return this.multimedialeDarstellung ;
  }
}
