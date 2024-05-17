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

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class CovidSectionContainment extends Containment {
    public SelectAqlField<CovidSection> COVID_SECTION =
            new AqlFieldImp<CovidSection>(CovidSection.class, "", "CovidSection", CovidSection.class, this);

    public SelectAqlField<CovidSymptomsObservation> COVID_SYMPTOMS = new AqlFieldImp<CovidSymptomsObservation>(
            CovidSection.class,
            "/items[openEHR-EHR-OBSERVATION.story.v1]",
            "covidSymptoms",
            CovidSymptomsObservation.class,
            this);

    public SelectAqlField<Covid19ExposureEvaluation> COVID19_EXPOSURE = new AqlFieldImp<Covid19ExposureEvaluation>(
            CovidSection.class,
            "/items[openEHR-EHR-EVALUATION.health_risk-covid.v0]",
            "covid19Exposure",
            Covid19ExposureEvaluation.class,
            this);

    public SelectAqlField<LatestCovid19TestObservation> LATEST_COVID19_TEST =
            new AqlFieldImp<LatestCovid19TestObservation>(
                    CovidSection.class,
                    "/items[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]",
                    "latestCovid19Test",
                    LatestCovid19TestObservation.class,
                    this);

    public SelectAqlField<CovidNotesEvaluation> COVID_NOTES = new AqlFieldImp<CovidNotesEvaluation>(
            CovidSection.class,
            "/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1]",
            "covidNotes",
            CovidNotesEvaluation.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT =
            new AqlFieldImp<FeederAudit>(CovidSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private CovidSectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static CovidSectionContainment getInstance() {
        return new CovidSectionContainment();
    }
}
