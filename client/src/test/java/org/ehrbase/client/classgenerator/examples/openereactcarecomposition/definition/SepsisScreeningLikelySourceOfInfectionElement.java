package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:51.583761300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class SepsisScreeningLikelySourceOfInfectionElement implements LocatableEntity {
  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/value
   */
  @Path("/value")
  @Choice
  private SepsisScreeningValueChoice value;

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setValue(SepsisScreeningValueChoice value) {
     this.value = value;
  }

  public SepsisScreeningValueChoice getValue() {
     return this.value ;
  }
}
