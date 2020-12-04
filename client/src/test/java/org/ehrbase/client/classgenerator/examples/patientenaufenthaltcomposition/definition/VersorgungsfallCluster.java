package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
public class VersorgungsfallCluster {
  /**
   * Patientenaufenthalt/context/Versorgungsfall/Zugehörige Versorgungsfall-Kennung
   */
  @Path("/items[at0001 and name/value='Zugehörige Versorgungsfall-Kennung']/value|value")
  private String zugehorigeVersorgungsfallKennungValue;

  /**
   * Patientenaufenthalt/context/Versorgungsfall/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setZugehorigeVersorgungsfallKennungValue(
      String zugehorigeVersorgungsfallKennungValue) {
     this.zugehorigeVersorgungsfallKennungValue = zugehorigeVersorgungsfallKennungValue;
  }

  public String getZugehorigeVersorgungsfallKennungValue() {
     return this.zugehorigeVersorgungsfallKennungValue ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
