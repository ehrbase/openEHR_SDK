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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.584781300+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class SepsisSection implements LocatableEntity {
    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening
     * Description: Sepsis screening tool for ages 12 plus based on the paper forms developed by The UK Sepsis Trust.
     */
    @Path("/items[openEHR-EHR-OBSERVATION.sepsis_screening_tool.v0 and name/value='Sepsis screening']")
    private SepsisScreeningObservation sepsisScreening;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setSepsisScreening(SepsisScreeningObservation sepsisScreening) {
        this.sepsisScreening = sepsisScreening;
    }

    public SepsisScreeningObservation getSepsisScreening() {
        return this.sepsisScreening;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
