package laborbefund.laborbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
public class LaboranalytResultatCluster2 {
  @Path("/items[at0005]/value")
  @Choice
  private LaboranalytResultatErgebnisStatusChoiceOrgEhrbaseEhrEncodeWrappersSnakecase77cf3f8b ergebnisStatus;

  @Path("/items[at0006]/value|value")
  private TemporalAccessor zeitpunktErgebnisStatusValue;

  @Path("/items[at0024]/value|value")
  private String untersuchterAnalytValue;

  @Path("/items[at0004]/value|value")
  private String referenzbereichsHinweiseValue;

  @Path("/items[at0003]/value|value")
  private String kommentarValue;

  @Path("/items[at0026]/name|value")
  private String probeValue;

  @Path("/items[at0001]/name|value")
  private String analytResultatValue;

  @Path("/items[at0006]/name|value")
  private String zeitpunktErgebnisStatusValueName;

  @Path("/items[at0024]/name|value")
  private String untersuchterAnalytValueName;

  @Path("/items[at0001]/value")
  @Choice
  private LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase1df98368 analytResultat;

  @Path("/items[at0026]/value")
  @Choice
  private LaboranalytResultatProbeChoiceOrgEhrbaseEhrEncodeWrappersSnakecase21ca139c probe;

  @Path("/items[at0004]/name|value")
  private String referenzbereichsHinweiseValueName;

  @Path("/items[at0025]/value|value")
  private TemporalAccessor zeitpunktValidationValue;

  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  public void setErgebnisStatus(
      LaboranalytResultatErgebnisStatusChoiceOrgEhrbaseEhrEncodeWrappersSnakecase77cf3f8b ergebnisStatus) {
     this.ergebnisStatus = ergebnisStatus;
  }

  public LaboranalytResultatErgebnisStatusChoiceOrgEhrbaseEhrEncodeWrappersSnakecase77cf3f8b getErgebnisStatus(
      ) {
     return this.ergebnisStatus ;
  }

  public void setZeitpunktErgebnisStatusValue(TemporalAccessor zeitpunktErgebnisStatusValue) {
     this.zeitpunktErgebnisStatusValue = zeitpunktErgebnisStatusValue;
  }

  public TemporalAccessor getZeitpunktErgebnisStatusValue() {
     return this.zeitpunktErgebnisStatusValue ;
  }

  public void setUntersuchterAnalytValue(String untersuchterAnalytValue) {
     this.untersuchterAnalytValue = untersuchterAnalytValue;
  }

  public String getUntersuchterAnalytValue() {
     return this.untersuchterAnalytValue ;
  }

  public void setReferenzbereichsHinweiseValue(String referenzbereichsHinweiseValue) {
     this.referenzbereichsHinweiseValue = referenzbereichsHinweiseValue;
  }

  public String getReferenzbereichsHinweiseValue() {
     return this.referenzbereichsHinweiseValue ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setProbeValue(String probeValue) {
     this.probeValue = probeValue;
  }

  public String getProbeValue() {
     return this.probeValue ;
  }

  public void setAnalytResultatValue(String analytResultatValue) {
     this.analytResultatValue = analytResultatValue;
  }

  public String getAnalytResultatValue() {
     return this.analytResultatValue ;
  }

  public void setZeitpunktErgebnisStatusValueName(String zeitpunktErgebnisStatusValueName) {
     this.zeitpunktErgebnisStatusValueName = zeitpunktErgebnisStatusValueName;
  }

  public String getZeitpunktErgebnisStatusValueName() {
     return this.zeitpunktErgebnisStatusValueName ;
  }

  public void setUntersuchterAnalytValueName(String untersuchterAnalytValueName) {
     this.untersuchterAnalytValueName = untersuchterAnalytValueName;
  }

  public String getUntersuchterAnalytValueName() {
     return this.untersuchterAnalytValueName ;
  }

  public void setAnalytResultat(
      LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase1df98368 analytResultat) {
     this.analytResultat = analytResultat;
  }

  public LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase1df98368 getAnalytResultat(
      ) {
     return this.analytResultat ;
  }

  public void setProbe(
      LaboranalytResultatProbeChoiceOrgEhrbaseEhrEncodeWrappersSnakecase21ca139c probe) {
     this.probe = probe;
  }

  public LaboranalytResultatProbeChoiceOrgEhrbaseEhrEncodeWrappersSnakecase21ca139c getProbe() {
     return this.probe ;
  }

  public void setReferenzbereichsHinweiseValueName(String referenzbereichsHinweiseValueName) {
     this.referenzbereichsHinweiseValueName = referenzbereichsHinweiseValueName;
  }

  public String getReferenzbereichsHinweiseValueName() {
     return this.referenzbereichsHinweiseValueName ;
  }

  public void setZeitpunktValidationValue(TemporalAccessor zeitpunktValidationValue) {
     this.zeitpunktValidationValue = zeitpunktValidationValue;
  }

  public TemporalAccessor getZeitpunktValidationValue() {
     return this.zeitpunktValidationValue ;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
     this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
     return this.analyseergebnisDetails ;
  }
}
