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

public class AssessmentSectionContainment extends Containment {
    public SelectAqlField<AssessmentSection> ASSESSMENT_SECTION = new AqlFieldImp<AssessmentSection>(
            AssessmentSection.class, "", "AssessmentSection", AssessmentSection.class, this);

    public SelectAqlField<DenwisObservation> DENWIS = new AqlFieldImp<DenwisObservation>(
            AssessmentSection.class,
            "/items[openEHR-EHR-OBSERVATION.denwis.v0]",
            "denwis",
            DenwisObservation.class,
            this);

    public SelectAqlField<SepsisSection> SEPSIS = new AqlFieldImp<SepsisSection>(
            AssessmentSection.class, "/items[openEHR-EHR-SECTION.adhoc.v1]", "sepsis", SepsisSection.class, this);

    public SelectAqlField<CovidSection> COVID = new AqlFieldImp<CovidSection>(
            AssessmentSection.class, "/items[openEHR-EHR-SECTION.adhoc.v1]", "covid", CovidSection.class, this);

    public SelectAqlField<News2Section> NEWS2 = new AqlFieldImp<News2Section>(
            AssessmentSection.class, "/items[openEHR-EHR-SECTION.adhoc.v1]", "news2", News2Section.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            AssessmentSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private AssessmentSectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static AssessmentSectionContainment getInstance() {
        return new AssessmentSectionContainment();
    }
}
