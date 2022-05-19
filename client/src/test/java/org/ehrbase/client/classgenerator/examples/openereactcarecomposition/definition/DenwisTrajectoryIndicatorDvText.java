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
    date = "2021-02-16T12:59:53.562779600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_TEXT")
public class DenwisTrajectoryIndicatorDvText implements RMEntity, DenwisTrajectoryIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Trajectory indicator/Trajectory indicator
   * Description: Nurse recorded unexpected trajectory.
   */
  @Path("|value")
  private String trajectoryIndicatorValue;

  public void setTrajectoryIndicatorValue(String trajectoryIndicatorValue) {
     this.trajectoryIndicatorValue = trajectoryIndicatorValue;
  }

  public String getTrajectoryIndicatorValue() {
     return this.trajectoryIndicatorValue ;
  }
}
