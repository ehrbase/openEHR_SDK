package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.100496300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class PhWertCluster implements LocatableEntity {
  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/untersuchter Analyt
   * Description: Die Bezeichnung des Analyt-Resultats
   */
  @Path("/items[at0024]/value|defining_code")
  private UntersuchterAnalytDefiningCode3 untersuchterAnalytDefiningCode;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/Analyt-Resultat
   * Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("/items[at0001]/value|magnitude")
  private Double analytResultatMagnitude;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/Analyt-Resultat
   * Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("/items[at0001]/value|units")
  private String analytResultatUnits;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/Analyseergebnis-Details
   * Description: Weitere Details zu einem einzelnen Ergebnis.
   */
  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/Ergebnis-Status
   * Description: Status des Analyseergebnisses.
   */
  @Path("/items[at0005]/value|value")
  private String ergebnisStatusValue;

  /** Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setUntersuchterAnalytDefiningCode(
      UntersuchterAnalytDefiningCode3 untersuchterAnalytDefiningCode) {
    this.untersuchterAnalytDefiningCode = untersuchterAnalytDefiningCode;
  }

  public UntersuchterAnalytDefiningCode3 getUntersuchterAnalytDefiningCode() {
    return this.untersuchterAnalytDefiningCode;
  }

  public void setAnalytResultatMagnitude(Double analytResultatMagnitude) {
    this.analytResultatMagnitude = analytResultatMagnitude;
  }

  public Double getAnalytResultatMagnitude() {
    return this.analytResultatMagnitude;
  }

  public void setAnalytResultatUnits(String analytResultatUnits) {
    this.analytResultatUnits = analytResultatUnits;
  }

  public String getAnalytResultatUnits() {
    return this.analytResultatUnits;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
    this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
    return this.analyseergebnisDetails;
  }

  public void setErgebnisStatusValue(String ergebnisStatusValue) {
    this.ergebnisStatusValue = ergebnisStatusValue;
  }

  public String getErgebnisStatusValue() {
    return this.ergebnisStatusValue;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
