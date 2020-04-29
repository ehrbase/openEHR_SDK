package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
public class LaboranalytResultatCluster {
  @Path("/items[at0024]/value|value")
  private String untersuchterAnalytValue;

  @Path("/items[at0024]/name|value")
  private String untersuchterAnalytValueName;

  @Path("/items[at0003]/value|value")
  private String kommentarValue;

  @Path("/items[at0001]/value")
  @Choice
  private LaboranalytResultatAnalytResultatChoice analytResultat;

  @Path("/items[at0026]/value")
  private DvIdentifier probe;

  @Path("/items[at0026]/name|value")
  private String probeValue;

 /* @Path("/items[at0001]/name|value")
  private DvText analytName;*/

  @Path("/items[at0027]/value|magnitude")
  private Long analyseergebnisReihenfolgeMagnitude;

  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  @Path("/name")
  private DvText name;

 /* @Path("/items[at0001]/name")
  @Choice
  private LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase40f33492 analytResultatName;
*/
    @Path("/items[at0001]/name")
    private DvText analytResultatName;

  public void setUntersuchterAnalytValue(String untersuchterAnalytValue) {
     this.untersuchterAnalytValue = untersuchterAnalytValue;
  }

  public String getUntersuchterAnalytValue() {
     return this.untersuchterAnalytValue ;
  }

  public void setUntersuchterAnalytValueName(String untersuchterAnalytValueName) {
     this.untersuchterAnalytValueName = untersuchterAnalytValueName;
  }

  public String getUntersuchterAnalytValueName() {
     return this.untersuchterAnalytValueName ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setAnalytResultat(LaboranalytResultatAnalytResultatChoice analytResultat) {
     this.analytResultat = analytResultat;
  }

  public LaboranalytResultatAnalytResultatChoice getAnalytResultat() {
     return this.analytResultat ;
  }

  public void setProbe(DvIdentifier probe) {
     this.probe = probe;
  }

  public DvIdentifier getProbe() {
     return this.probe ;
  }

  public void setProbeValue(String probeValue) {
     this.probeValue = probeValue;
  }

  public String getProbeValue() {
     return this.probeValue ;
  }

  public void setAnalyseergebnisReihenfolgeMagnitude(Long analyseergebnisReihenfolgeMagnitude) {
     this.analyseergebnisReihenfolgeMagnitude = analyseergebnisReihenfolgeMagnitude;
  }

  public Long getAnalyseergebnisReihenfolgeMagnitude() {
     return this.analyseergebnisReihenfolgeMagnitude ;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
     this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
     return this.analyseergebnisDetails ;
  }

 /* public void setAnalytResultatName(
      LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase40f33492 analytResultatName) {
     this.analytResultatName = analytResultatName;
  }

  public LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase40f33492 getAnalytResultatName(
      ) {
     return this.analytResultatName ;
  }
*/
    public DvText getName() {
        return name;
    }

    public void setName(DvText name) {
        this.name = name;
    }


    public DvText getAnalytResultatName() {
        return analytResultatName;
    }

    public void setAnalytResultatName(DvText analytResultatName) {
        this.analytResultatName = analytResultatName;
    }
}
