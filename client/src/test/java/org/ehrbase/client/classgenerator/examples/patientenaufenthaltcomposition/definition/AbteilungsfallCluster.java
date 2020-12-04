package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
public class AbteilungsfallCluster {
  /**
   * Patientenaufenthalt/context/Abteilungsfall/Zugehörige Abteilungsfall-Kennung
   */
  @Path("/items[at0001 and name/value='Zugehörige Abteilungsfall-Kennung']/value|value")
  private String zugehorigeAbteilungsfallKennungValue;

  /**
   * Patientenaufenthalt/context/Abteilungsfall/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setZugehorigeAbteilungsfallKennungValue(String zugehorigeAbteilungsfallKennungValue) {
     this.zugehorigeAbteilungsfallKennungValue = zugehorigeAbteilungsfallKennungValue;
  }

  public String getZugehorigeAbteilungsfallKennungValue() {
     return this.zugehorigeAbteilungsfallKennungValue ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
