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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.errortestcomposition.definition;

import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.RMEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2022-03-02T14:11:00.845608300+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_INTERVAL<DV_DATE_TIME>")
public class SpecimenCollectionDateTimeDvIntervalDvDateTime implements RMEntity, SpecimenCollectionDateTimeChoice {
    /** Path: ErrorTest/Laboratory test result/Any event/Specimen/Collection date/time/lower */
    @Path("/lower|value")
    private TemporalAccessor lowerValue;

    /** Path: ErrorTest/Laboratory test result/Any event/Specimen/Collection date/time/upper */
    @Path("/upper|value")
    private TemporalAccessor upperValue;

    /**
     * Path: ErrorTest/Laboratory test result/Any event/Specimen/Collection date/time/lower_included
     */
    @Path("/lower_included")
    private Boolean lowerIncluded;

    /**
     * Path: ErrorTest/Laboratory test result/Any event/Specimen/Collection date/time/upper_included
     */
    @Path("/upper_included")
    private Boolean upperIncluded;

    public void setLowerValue(TemporalAccessor lowerValue) {
        this.lowerValue = lowerValue;
    }

    public TemporalAccessor getLowerValue() {
        return this.lowerValue;
    }

    public void setUpperValue(TemporalAccessor upperValue) {
        this.upperValue = upperValue;
    }

    public TemporalAccessor getUpperValue() {
        return this.upperValue;
    }

    public void setLowerIncluded(Boolean lowerIncluded) {
        this.lowerIncluded = lowerIncluded;
    }

    public Boolean isLowerIncluded() {
        return this.lowerIncluded;
    }

    public void setUpperIncluded(Boolean upperIncluded) {
        this.upperIncluded = upperIncluded;
    }

    public Boolean isUpperIncluded() {
        return this.upperIncluded;
    }
}
