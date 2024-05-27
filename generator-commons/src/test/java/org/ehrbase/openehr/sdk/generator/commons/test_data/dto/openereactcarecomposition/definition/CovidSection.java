/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.620776800+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class CovidSection implements LocatableEntity {
    /**
     * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms
     * Description: The subjective clinical history of the subject of care as recorded directly by the subject, or reported to a clinician by the subject or a carer.
     */
    @Path("/items[openEHR-EHR-OBSERVATION.story.v1 and name/value='Covid symptoms']")
    private CovidSymptomsObservation covidSymptoms;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure
     * Description: Assessment of the potential and likelihood of Covid-19 infection as determined by identified risk factors.
     */
    @Path("/items[openEHR-EHR-EVALUATION.health_risk-covid.v0 and name/value='Covid-19 exposure']")
    private Covid19ExposureEvaluation covid19Exposure;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test
     * Description: The result, including findings and the laboratory's interpretation, of an investigation performed on specimens collected from an individual or related to that individual.
     */
    @Path("/items[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Latest Covid-19 test']")
    private LatestCovid19TestObservation latestCovid19Test;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Covid notes
     * Description: Narrative summary or overview about a patient, specifically from the perspective of a healthcare provider, and with or without associated interpretations.
     */
    @Path("/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Covid notes']")
    private CovidNotesEvaluation covidNotes;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setCovidSymptoms(CovidSymptomsObservation covidSymptoms) {
        this.covidSymptoms = covidSymptoms;
    }

    public CovidSymptomsObservation getCovidSymptoms() {
        return this.covidSymptoms;
    }

    public void setCovid19Exposure(Covid19ExposureEvaluation covid19Exposure) {
        this.covid19Exposure = covid19Exposure;
    }

    public Covid19ExposureEvaluation getCovid19Exposure() {
        return this.covid19Exposure;
    }

    public void setLatestCovid19Test(LatestCovid19TestObservation latestCovid19Test) {
        this.latestCovid19Test = latestCovid19Test;
    }

    public LatestCovid19TestObservation getLatestCovid19Test() {
        return this.latestCovid19Test;
    }

    public void setCovidNotes(CovidNotesEvaluation covidNotes) {
        this.covidNotes = covidNotes;
    }

    public CovidNotesEvaluation getCovidNotes() {
        return this.covidNotes;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
