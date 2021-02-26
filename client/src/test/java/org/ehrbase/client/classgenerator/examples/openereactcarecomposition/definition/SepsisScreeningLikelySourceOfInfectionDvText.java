package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.593784+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_TEXT")
public class SepsisScreeningLikelySourceOfInfectionDvText implements RMEntity, SepsisScreeningLikelySourceOfInfectionChoice {
  /**
   * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Likely source of infection/Likely source of infection
   * Description: Used to record details of source of any infection from the sepsis screening tool.
   */
  @Path("|value")
  private String likelySourceOfInfectionValue;

  public void setLikelySourceOfInfectionValue(String likelySourceOfInfectionValue) {
     this.likelySourceOfInfectionValue = likelySourceOfInfectionValue;
  }

  public String getLikelySourceOfInfectionValue() {
     return this.likelySourceOfInfectionValue ;
  }
}
