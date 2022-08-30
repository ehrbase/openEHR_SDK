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
package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.060064900+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class KorpertemperaturStorfaktorenElement implements LocatableEntity {
    /**
     * Path: Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/Störfaktoren
     * Description: Zusätzliche Probleme oder Faktoren, die sich auf die Messung der Körpertemperatur auswirken können und in anderen Bereichen nicht dargestellt werden.
     */
    @Path("/value|value")
    private String value;

    /**
     * Path: Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
