/*
 * Copyright (c) 2022 vitasystems GmbH.
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
        date = "2021-02-16T12:59:53.719778800+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_CODED_TEXT")
public class LatestCovid19TestOverallTestStatusDvCodedText
        implements RMEntity, LatestCovid19TestOverallTestStatusChoice {
    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Overall test status/Overall test status
     * Description: The status of the laboratory test result as a whole.
     */
    @Path("|defining_code")
    private OverallTestStatusDefiningCode overallTestStatusDefiningCode;

    public void setOverallTestStatusDefiningCode(OverallTestStatusDefiningCode overallTestStatusDefiningCode) {
        this.overallTestStatusDefiningCode = overallTestStatusDefiningCode;
    }

    public OverallTestStatusDefiningCode getOverallTestStatusDefiningCode() {
        return this.overallTestStatusDefiningCode;
    }
}
