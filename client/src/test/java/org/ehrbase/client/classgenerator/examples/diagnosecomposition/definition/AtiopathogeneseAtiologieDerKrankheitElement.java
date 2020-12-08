package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
public class AtiopathogeneseAtiologieDerKrankheitElement implements LocatableEntity {
  /**
   * COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/value
   */
  @Path("/value")
  @Choice
  private AtiopathogeneseValueChoice value;

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setValue(AtiopathogeneseValueChoice value) {
     this.value = value;
  }

  public AtiopathogeneseValueChoice getValue() {
     return this.value ;
  }
}
