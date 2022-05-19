package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.590779900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class SepsisScreeningLikelySourceOfInfectionElement implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Likely source of infection
   * Description: Used to record details of source of any infection from the sepsis screening tool.
   */
  @Path("/value")
  @Choice
  private List<SepsisScreeningLikelySourceOfInfectionChoice> value;

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setValue(List<SepsisScreeningLikelySourceOfInfectionChoice> value) {
     this.value = value;
  }

  public List<SepsisScreeningLikelySourceOfInfectionChoice> getValue() {
     return this.value ;
  }
}
