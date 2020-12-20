package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.892029300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AbteilungsfallCluster implements LocatableEntity {
  /**
   * Path: Patientenaufenthalt/context/Abteilungsfall/Zugehörige Abteilungsfall-Kennung Description:
   * Der Bezeichner/die Kennung dieses Falls.
   */
  @Path("/items[at0001 and name/value='Zugehörige Abteilungsfall-Kennung']/value|value")
  private String zugehorigeAbteilungsfallKennungValue;

  /** Path: Patientenaufenthalt/context/Abteilungsfall/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setZugehorigeAbteilungsfallKennungValue(String zugehorigeAbteilungsfallKennungValue) {
    this.zugehorigeAbteilungsfallKennungValue = zugehorigeAbteilungsfallKennungValue;
  }

  public String getZugehorigeAbteilungsfallKennungValue() {
    return this.zugehorigeAbteilungsfallKennungValue;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
