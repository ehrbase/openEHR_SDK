package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
public class SepsisScreeningAmberFlagAcuteElement implements LocatableEntity {
  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Amber flag (acute)
   */
  @Path("/value|defining_code")
  private AmberFlagAcuteDefiningCode value;

  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(AmberFlagAcuteDefiningCode value) {
     this.value = value;
  }

  public AmberFlagAcuteDefiningCode getValue() {
     return this.value ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
