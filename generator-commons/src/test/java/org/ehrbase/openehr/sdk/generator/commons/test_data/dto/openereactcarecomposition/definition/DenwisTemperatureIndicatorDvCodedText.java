/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.RMEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.527780+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_CODED_TEXT")
public class DenwisTemperatureIndicatorDvCodedText implements RMEntity, DenwisTemperatureIndicatorChoice {
    /**
     * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Temperature indicator/Temperature indicator
     * Description: Nurse recorded changes in temperature.
     */
    @Path("|defining_code")
    private TemperatureIndicatorDefiningCode temperatureIndicatorDefiningCode;

    public void setTemperatureIndicatorDefiningCode(TemperatureIndicatorDefiningCode temperatureIndicatorDefiningCode) {
        this.temperatureIndicatorDefiningCode = temperatureIndicatorDefiningCode;
    }

    public TemperatureIndicatorDefiningCode getTemperatureIndicatorDefiningCode() {
        return this.temperatureIndicatorDefiningCode;
    }
}
