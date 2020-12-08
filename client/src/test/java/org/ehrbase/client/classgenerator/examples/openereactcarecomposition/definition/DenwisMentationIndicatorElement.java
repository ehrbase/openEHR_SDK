package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
public class DenwisMentationIndicatorElement implements LocatableEntity {
  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/value
   */
  @Path("/value")
  @Choice
  private DenwisValueChoice2 value;

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setValue(DenwisValueChoice2 value) {
     this.value = value;
  }

  public DenwisValueChoice2 getValue() {
     return this.value ;
  }
}
