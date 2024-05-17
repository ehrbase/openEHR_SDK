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
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.436778200+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BackgroundSection implements LocatableEntity {
    /**
     * Path: open_eREACT-Care/Background/Height
     * Description: Height, or body length, is measured from crown of head to sole of foot.
     * Comment: Height is measured with the individual in a standing position and body length in a recumbent position.
     */
    @Path("/items[openEHR-EHR-OBSERVATION.height.v2 and name/value='Height']")
    private HeightObservation height;

    /**
     * Path: open_eREACT-Care/Background/Weight
     * Description: Measurement of the body weight of an individual.
     */
    @Path("/items[openEHR-EHR-OBSERVATION.body_weight.v2 and name/value='Weight']")
    private WeightObservation weight;

    /**
     * Path: open_eREACT-Care/Background/Frailty
     * Description: An assessment scale used to screen for frailty and to broadly stratify degrees of fitness and frailty in an older adult.
     * Comment: Also known as the Rockwood Clinical Frailty Scale.
     */
    @Path("/items[openEHR-EHR-OBSERVATION.clinical_frailty_scale.v1 and name/value='Frailty']")
    private FrailtyObservation frailty;

    /**
     * Path: open_eREACT-Care/Background/Past history
     * Description: Narrative summary or overview about a patient, specifically from the perspective of a healthcare provider, and with or without associated interpretations.
     */
    @Path("/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Past history']")
    private PastHistoryEvaluation pastHistory;

    /**
     * Path: open_eREACT-Care/Background/Medication
     * Description: Narrative summary or overview about a patient, specifically from the perspective of a healthcare provider, and with or without associated interpretations.
     */
    @Path("/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Medication']")
    private MedicationEvaluation medication;

    /**
     * Path: open_eREACT-Care/Background/Allergies
     * Description: Narrative summary or overview about a patient, specifically from the perspective of a healthcare provider, and with or without associated interpretations.
     */
    @Path("/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Allergies']")
    private AllergiesEvaluation allergies;

    /**
     * Path: open_eREACT-Care/Background/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setHeight(HeightObservation height) {
        this.height = height;
    }

    public HeightObservation getHeight() {
        return this.height;
    }

    public void setWeight(WeightObservation weight) {
        this.weight = weight;
    }

    public WeightObservation getWeight() {
        return this.weight;
    }

    public void setFrailty(FrailtyObservation frailty) {
        this.frailty = frailty;
    }

    public FrailtyObservation getFrailty() {
        return this.frailty;
    }

    public void setPastHistory(PastHistoryEvaluation pastHistory) {
        this.pastHistory = pastHistory;
    }

    public PastHistoryEvaluation getPastHistory() {
        return this.pastHistory;
    }

    public void setMedication(MedicationEvaluation medication) {
        this.medication = medication;
    }

    public MedicationEvaluation getMedication() {
        return this.medication;
    }

    public void setAllergies(AllergiesEvaluation allergies) {
        this.allergies = allergies;
    }

    public AllergiesEvaluation getAllergies() {
        return this.allergies;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
