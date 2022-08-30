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
package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvState;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-05-19T16:20:30.087761200+02:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_STATE")
public class ProAnalytTestmethodeDvState implements RMEntity, ProAnalytTestmethodeChoice {
    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro
     * Analyt/Testmethode/Testmethode Description: Die Beschreibung der Methode, mit der der Test nur
     * für diesen Analyten durchgeführt wurde.
     */
    @Path("")
    private DvState testmethode;

    public void setTestmethode(DvState testmethode) {
        this.testmethode = testmethode;
    }

    public DvState getTestmethode() {
        return this.testmethode;
    }
}
