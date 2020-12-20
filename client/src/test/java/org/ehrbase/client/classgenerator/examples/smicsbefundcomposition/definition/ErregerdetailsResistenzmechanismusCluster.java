package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.156026200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ErregerdetailsResistenzmechanismusCluster implements LocatableEntity {
  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Resistenzmechanismus/Resistenzmechanismus Bezeichnung Description:
   * Bezeichnung des Resistenzmechanismus.
   */
  @Path("/items[at0017]/value|value")
  private String resistenzmechanismusBezeichnungValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Resistenzmechanismus/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setResistenzmechanismusBezeichnungValue(String resistenzmechanismusBezeichnungValue) {
    this.resistenzmechanismusBezeichnungValue = resistenzmechanismusBezeichnungValue;
  }

  public String getResistenzmechanismusBezeichnungValue() {
    return this.resistenzmechanismusBezeichnungValue;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
