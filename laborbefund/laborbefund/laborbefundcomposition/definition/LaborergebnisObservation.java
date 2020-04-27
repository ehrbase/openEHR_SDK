package laborbefund.laborbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import laborbefund.shareddefinition.Language;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.laboratory_test_result.v1")
public class LaborergebnisObservation {
  @Path("/protocol[at0004]/items[openEHR-EHR-CLUSTER.location.v1]")
  private StandortCluster standort;

  @Path("/protocol[at0004]/items[at0110]")
  private List<Cluster> testDetails;

  @Path("/protocol[at0004]/items[at0094]")
  private List<StandortDetailsDerTestanforderungCluster> detailsDerTestanforderung;

  @Path("/language")
  private Language language;

  @Path("/protocol[at0004]/items[at0121]/value|value")
  private String testmethodeValue;

  @Path("/protocol[at0004]/items[at0117]")
  private List<Cluster> erweiterung;

  @Path("/protocol[at0004]/items[at0121]/name|value")
  private String testmethodeValueTree;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  @Path("/data[at0001]/events[at0002]")
  @Choice
  private List<StandortJedesEreignisChoice> jedesEreignis;

  public void setStandort(StandortCluster standort) {
     this.standort = standort;
  }

  public StandortCluster getStandort() {
     return this.standort ;
  }

  public void setTestDetails(List<Cluster> testDetails) {
     this.testDetails = testDetails;
  }

  public List<Cluster> getTestDetails() {
     return this.testDetails ;
  }

  public void setDetailsDerTestanforderung(
      List<StandortDetailsDerTestanforderungCluster> detailsDerTestanforderung) {
     this.detailsDerTestanforderung = detailsDerTestanforderung;
  }

  public List<StandortDetailsDerTestanforderungCluster> getDetailsDerTestanforderung() {
     return this.detailsDerTestanforderung ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setTestmethodeValue(String testmethodeValue) {
     this.testmethodeValue = testmethodeValue;
  }

  public String getTestmethodeValue() {
     return this.testmethodeValue ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setTestmethodeValueTree(String testmethodeValueTree) {
     this.testmethodeValueTree = testmethodeValueTree;
  }

  public String getTestmethodeValueTree() {
     return this.testmethodeValueTree ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setJedesEreignis(List<StandortJedesEreignisChoice> jedesEreignis) {
     this.jedesEreignis = jedesEreignis;
  }

  public List<StandortJedesEreignisChoice> getJedesEreignis() {
     return this.jedesEreignis ;
  }
}
