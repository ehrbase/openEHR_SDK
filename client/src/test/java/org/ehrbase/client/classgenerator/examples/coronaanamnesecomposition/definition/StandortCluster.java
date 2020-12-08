package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
public class StandortCluster implements LocatableEntity {
  /**
   * Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort/Standortbeschreibung
   */
  @Path("/items[at0046]/value|value")
  private String standortbeschreibungValue;

  /**
   * Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort/Details
   */
  @Path("/items[at0047]")
  private List<Cluster> details;

  /**
   * Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStandortbeschreibungValue(String standortbeschreibungValue) {
     this.standortbeschreibungValue = standortbeschreibungValue;
  }

  public String getStandortbeschreibungValue() {
     return this.standortbeschreibungValue ;
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
