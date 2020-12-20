package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.385500900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class DenwisCirculationIndicatorElement implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Circulation indicator Description: Nurse
   * recorded changes in circulation.
   */
  @Path("/value|defining_code")
  private CirculationIndicatorDefiningCode value;

  /** Path: open_eREACT-Care/Assessment/DENWIS/Point in time/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(CirculationIndicatorDefiningCode value) {
    this.value = value;
  }

  public CirculationIndicatorDefiningCode getValue() {
    return this.value;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
