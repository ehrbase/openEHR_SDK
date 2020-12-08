package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
public class SepsisScreeningRedFlagAcuteElement implements LocatableEntity {
  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Red flag (acute)
   */
  @Path("/value|defining_code")
  private RedFlagAcuteDefiningCode value;

  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(RedFlagAcuteDefiningCode value) {
     this.value = value;
  }

  public RedFlagAcuteDefiningCode getValue() {
     return this.value ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
