package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.591781400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_CODED_TEXT")
public class SepsisScreeningLikelySourceOfInfectionDvCodedText implements RMEntity, SepsisScreeningLikelySourceOfInfectionChoice {
  /**
   * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Likely source of infection/Likely source of infection
   * Description: Used to record details of source of any infection from the sepsis screening tool.
   */
  @Path("|defining_code")
  private LikelySourceOfInfectionDefiningCode likelySourceOfInfectionDefiningCode;

  public void setLikelySourceOfInfectionDefiningCode(
      LikelySourceOfInfectionDefiningCode likelySourceOfInfectionDefiningCode) {
     this.likelySourceOfInfectionDefiningCode = likelySourceOfInfectionDefiningCode;
  }

  public LikelySourceOfInfectionDefiningCode getLikelySourceOfInfectionDefiningCode() {
     return this.likelySourceOfInfectionDefiningCode ;
  }
}
