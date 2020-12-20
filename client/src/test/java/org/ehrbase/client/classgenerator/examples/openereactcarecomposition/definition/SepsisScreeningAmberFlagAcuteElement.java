package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.415498500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class SepsisScreeningAmberFlagAcuteElement implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Amber flag (acute)
   * Description: Used to record details of any amber flag indicators from the sepsis screening
   * tool.
   */
  @Path("/value|defining_code")
  private AmberFlagAcuteDefiningCode value;

  /** Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(AmberFlagAcuteDefiningCode value) {
    this.value = value;
  }

  public AmberFlagAcuteDefiningCode getValue() {
    return this.value;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
