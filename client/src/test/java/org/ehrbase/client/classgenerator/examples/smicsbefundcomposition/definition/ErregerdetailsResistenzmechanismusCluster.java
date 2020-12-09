package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:52.205764+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class ErregerdetailsResistenzmechanismusCluster implements LocatableEntity {
  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Resistenzmechanismus/Resistenzmechanismus Bezeichnung
   */
  @Path("/items[at0017]/value|value")
  private String resistenzmechanismusBezeichnungValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Resistenzmechanismus/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setResistenzmechanismusBezeichnungValue(String resistenzmechanismusBezeichnungValue) {
     this.resistenzmechanismusBezeichnungValue = resistenzmechanismusBezeichnungValue;
  }

  public String getResistenzmechanismusBezeichnungValue() {
     return this.resistenzmechanismusBezeichnungValue ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
