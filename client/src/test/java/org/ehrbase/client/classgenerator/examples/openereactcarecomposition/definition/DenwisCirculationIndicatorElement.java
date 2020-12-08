package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
public class DenwisCirculationIndicatorElement implements LocatableEntity {
  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/Circulation indicator
   */
  @Path("/value|defining_code")
  private CirculationIndicatorDefiningCode value;

  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(CirculationIndicatorDefiningCode value) {
     this.value = value;
  }

  public CirculationIndicatorDefiningCode getValue() {
     return this.value ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
