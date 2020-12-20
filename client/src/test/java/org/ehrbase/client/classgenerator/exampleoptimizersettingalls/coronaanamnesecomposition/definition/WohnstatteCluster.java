package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.dwelling.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.110030600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class WohnstatteCluster implements LocatableEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte/Anzahl der Schlafzimmer Description:
   * Die Anzahl der Schlafzimmer innerhalb der Wohnstätte.
   */
  @Path("/items[at0028]/value|magnitude")
  private Long anzahlDerSchlafzimmerMagnitude;

  /**
   * Path: Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte/Ergänzende Details Description:
   * Weitere strukturierte Details zur Wohnstätte. Comment: Dieser SLOT kann verwendet werden, um
   * weitere Archetypen zu verschachteln, die die Wohnstätte mit ergänzenden Details beschreiben und
   * einem lokalen Zuständigkeitsbereich angehören können.
   */
  @Path("/items[at0003]")
  private List<Cluster> erganzendeDetails;

  /** Path: Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAnzahlDerSchlafzimmerMagnitude(Long anzahlDerSchlafzimmerMagnitude) {
    this.anzahlDerSchlafzimmerMagnitude = anzahlDerSchlafzimmerMagnitude;
  }

  public Long getAnzahlDerSchlafzimmerMagnitude() {
    return this.anzahlDerSchlafzimmerMagnitude;
  }

  public void setErganzendeDetails(List<Cluster> erganzendeDetails) {
    this.erganzendeDetails = erganzendeDetails;
  }

  public List<Cluster> getErganzendeDetails() {
    return this.erganzendeDetails;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
