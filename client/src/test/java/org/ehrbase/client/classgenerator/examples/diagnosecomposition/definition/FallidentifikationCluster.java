package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

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
    date = "2020-12-10T13:06:12.339026+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class FallidentifikationCluster implements LocatableEntity {
  /**
   * Path: COVID-19-Diagnose/context/Fallidentifikation/Fall-Kennung Description: Der Bezeichner/die
   * Kennung dieses Falls.
   */
  @Path("/items[at0001]/value|value")
  private String fallKennungValue;

  /** Path: COVID-19-Diagnose/context/Fallidentifikation/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setFallKennungValue(String fallKennungValue) {
    this.fallKennungValue = fallKennungValue;
  }

  public String getFallKennungValue() {
    return this.fallKennungValue;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
