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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.geccoserologischerbefundcomposition.definition;

import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.RMEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-05-19T16:20:30.095762500+02:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_TIME")
public class ProAnalytTestmethodeDvTime implements RMEntity, ProAnalytTestmethodeChoice {
    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro
     * Analyt/Testmethode/Testmethode Description: Die Beschreibung der Methode, mit der der Test nur
     * für diesen Analyten durchgeführt wurde.
     */
    @Path("|value")
    private TemporalAccessor testmethodeValue;

    public void setTestmethodeValue(TemporalAccessor testmethodeValue) {
        this.testmethodeValue = testmethodeValue;
    }

    public TemporalAccessor getTestmethodeValue() {
        return this.testmethodeValue;
    }
}
