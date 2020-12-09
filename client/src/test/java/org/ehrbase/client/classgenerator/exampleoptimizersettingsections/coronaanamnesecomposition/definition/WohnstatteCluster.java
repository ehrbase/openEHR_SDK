package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Long;
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
    date = "2020-12-09T11:37:53.418286800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class WohnstatteCluster implements LocatableEntity {
  /**
   * Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte/Anzahl der Schlafzimmer
   */
  @Path("/items[at0028]/value|magnitude")
  private Long anzahlDerSchlafzimmerMagnitude;

  /**
   * Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte/Ergänzende Details
   */
  @Path("/items[at0003]")
  private List<Cluster> erganzendeDetails;

  /**
   * Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAnzahlDerSchlafzimmerMagnitude(Long anzahlDerSchlafzimmerMagnitude) {
     this.anzahlDerSchlafzimmerMagnitude = anzahlDerSchlafzimmerMagnitude;
  }

  public Long getAnzahlDerSchlafzimmerMagnitude() {
     return this.anzahlDerSchlafzimmerMagnitude ;
  }

  public void setErganzendeDetails(List<Cluster> erganzendeDetails) {
     this.erganzendeDetails = erganzendeDetails;
  }

  public List<Cluster> getErganzendeDetails() {
     return this.erganzendeDetails ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
