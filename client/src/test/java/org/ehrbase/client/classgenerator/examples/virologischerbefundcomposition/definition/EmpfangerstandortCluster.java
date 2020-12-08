package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

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
public class EmpfangerstandortCluster implements LocatableEntity {
  /**
   * Virologischer Befund/Befund/Empfängerstandort/Standorttyp
   */
  @Path("/items[at0040]/value|value")
  private String standorttypValue;

  /**
   * Virologischer Befund/Befund/Empfängerstandort/Standortbeschreibung
   */
  @Path("/items[at0046]/value|value")
  private String standortbeschreibungValue;

  /**
   * Virologischer Befund/Befund/Empfängerstandort/Standortschlüssel
   */
  @Path("/items[at0048]/value|value")
  private String standortschlusselValue;

  /**
   * Virologischer Befund/Befund/Empfängerstandort/Details
   */
  @Path("/items[at0047]")
  private List<Cluster> details;

  /**
   * Virologischer Befund/Befund/Empfängerstandort/feeder_audit
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
