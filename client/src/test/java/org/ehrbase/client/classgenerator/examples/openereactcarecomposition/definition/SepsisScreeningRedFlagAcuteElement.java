package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:51.585763300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
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
