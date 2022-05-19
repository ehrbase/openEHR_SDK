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

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class SepsisSectionContainment extends Containment {
    public SelectAqlField<SepsisSection> SEPSIS_SECTION =
            new AqlFieldImp<SepsisSection>(SepsisSection.class, "", "SepsisSection", SepsisSection.class, this);

    public SelectAqlField<SepsisScreeningObservation> SEPSIS_SCREENING = new AqlFieldImp<SepsisScreeningObservation>(
            SepsisSection.class,
            "/items[openEHR-EHR-OBSERVATION.sepsis_screening_tool.v0]",
            "sepsisScreening",
            SepsisScreeningObservation.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT =
            new AqlFieldImp<FeederAudit>(SepsisSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private SepsisSectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static SepsisSectionContainment getInstance() {
        return new SepsisSectionContainment();
    }
}
