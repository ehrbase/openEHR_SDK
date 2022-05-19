/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_CODED_TEXT")
public class DenwisTrajectoryIndicatorDvCodedText implements RMEntity, DenwisTrajectoryIndicatorChoice {
    /**
     * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Trajectory indicator/Trajectory indicator
     * Description: Nurse recorded unexpected trajectory.
     */
    @Path("|defining_code")
    private TrajectoryIndicatorDefiningCode trajectoryIndicatorDefiningCode;

    public void setTrajectoryIndicatorDefiningCode(TrajectoryIndicatorDefiningCode trajectoryIndicatorDefiningCode) {
        this.trajectoryIndicatorDefiningCode = trajectoryIndicatorDefiningCode;
    }

    public TrajectoryIndicatorDefiningCode getTrajectoryIndicatorDefiningCode() {
        return this.trajectoryIndicatorDefiningCode;
    }
}
