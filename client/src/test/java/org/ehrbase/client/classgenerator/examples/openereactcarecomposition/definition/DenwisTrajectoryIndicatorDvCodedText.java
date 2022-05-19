package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.561779200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_CODED_TEXT")
public class DenwisTrajectoryIndicatorDvCodedText implements RMEntity, DenwisTrajectoryIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Trajectory indicator/Trajectory indicator
   * Description: Nurse recorded unexpected trajectory.
   */
  @Path("|defining_code")
  private TrajectoryIndicatorDefiningCode trajectoryIndicatorDefiningCode;

  public void setTrajectoryIndicatorDefiningCode(
      TrajectoryIndicatorDefiningCode trajectoryIndicatorDefiningCode) {
     this.trajectoryIndicatorDefiningCode = trajectoryIndicatorDefiningCode;
  }

  public TrajectoryIndicatorDefiningCode getTrajectoryIndicatorDefiningCode() {
     return this.trajectoryIndicatorDefiningCode ;
  }
}
