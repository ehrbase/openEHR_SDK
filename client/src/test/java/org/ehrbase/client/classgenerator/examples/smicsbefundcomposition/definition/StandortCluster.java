package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:52.173764100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class StandortCluster implements LocatableEntity {
  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Standorttyp
   */
  @Path("/items[at0040]/value|value")
  private String standorttypValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Standortbeschreibung
   */
  @Path("/items[at0046]/value|value")
  private String standortbeschreibungValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Standortschlüssel
   */
  @Path("/items[at0048]/value|value")
  private String standortschlusselValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Station
   */
  @Path("/items[at0027]/value|value")
  private String stationValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Zimmer
   */
  @Path("/items[at0029]/value|value")
  private String zimmerValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Details
   */
  @Path("/items[at0047]")
  private List<Cluster> details;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStandorttypValue(String standorttypValue) {
     this.standorttypValue = standorttypValue;
  }

  public String getStandorttypValue() {
     return this.standorttypValue ;
  }

  public void setStandortbeschreibungValue(String standortbeschreibungValue) {
     this.standortbeschreibungValue = standortbeschreibungValue;
  }

  public String getStandortbeschreibungValue() {
     return this.standortbeschreibungValue ;
  }

  public void setStandortschlusselValue(String standortschlusselValue) {
     this.standortschlusselValue = standortschlusselValue;
  }

  public String getStandortschlusselValue() {
     return this.standortschlusselValue ;
  }

  public void setStationValue(String stationValue) {
     this.stationValue = stationValue;
  }

  public String getStationValue() {
     return this.stationValue ;
  }

  public void setZimmerValue(String zimmerValue) {
     this.zimmerValue = zimmerValue;
  }

  public String getZimmerValue() {
     return this.zimmerValue ;
  }

  public void setDetails(List<Cluster> details) {
     this.details = details;
  }

  public List<Cluster> getDetails() {
     return this.details ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
