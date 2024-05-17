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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.diagnosecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.RMEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:12.359025800+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_TEXT")
public class ProblemDiagnoseSchweregradDvText implements RMEntity, ProblemDiagnoseSchweregradChoice {
    /**
     * Path: COVID-19-Diagnose/Problem/Diagnose/value/value
     */
    @Path("|value")
    private String schweregradValue;

    public void setSchweregradValue(String schweregradValue) {
        this.schweregradValue = schweregradValue;
    }

    public String getSchweregradValue() {
        return this.schweregradValue;
    }
}
