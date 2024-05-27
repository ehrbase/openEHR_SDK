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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.EntryEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Transition;

@Entity
@Archetype("openEHR-EHR-ACTION.test_all_types.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-10-08T15:38:06.372885200+02:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class TestAllTypesAction implements EntryEntity {
    /**
     * Path: Test all types/Test all types/section 2/section 3/Test all types/Test all types
     * Description: unknown
     */
    @Path("/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]")
    private List<TestAllTypesCluster> testAllTypes;

    /**
     * Path: Test all types/Test all types/section 2/section 3/Test all types/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: Test all types/Test all types/section 2/section 3/Test all types/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Test all types/Test all types/section 2/section 3/Test all types/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Test all types/Test all types/section 2/section 3/Test all types/time
     */
    @Path("/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: Test all types/Test all types/section 2/section 3/Test all types/ism_transition/Careflow_step
     */
    @Path("/ism_transition/careflow_step|defining_code")
    private CareflowStepDefiningCode careflowStepDefiningCode;

    /**
     * Path: Test all types/Test all types/section 2/section 3/Test all types/ism_transition/Current_state
     */
    @Path("/ism_transition/current_state|defining_code")
    private CurrentStateDefiningCode currentStateDefiningCode;

    /**
     * Path: Test all types/Test all types/section 2/section 3/Test all types/ism_transition/transition
     */
    @Path("/ism_transition/transition|defining_code")
    private Transition transitionDefiningCode;

    public void setTestAllTypes(List<TestAllTypesCluster> testAllTypes) {
        this.testAllTypes = testAllTypes;
    }

    public List<TestAllTypesCluster> getTestAllTypes() {
        return this.testAllTypes;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setCareflowStepDefiningCode(CareflowStepDefiningCode careflowStepDefiningCode) {
        this.careflowStepDefiningCode = careflowStepDefiningCode;
    }

    public CareflowStepDefiningCode getCareflowStepDefiningCode() {
        return this.careflowStepDefiningCode;
    }

    public void setCurrentStateDefiningCode(CurrentStateDefiningCode currentStateDefiningCode) {
        this.currentStateDefiningCode = currentStateDefiningCode;
    }

    public CurrentStateDefiningCode getCurrentStateDefiningCode() {
        return this.currentStateDefiningCode;
    }

    public void setTransitionDefiningCode(Transition transitionDefiningCode) {
        this.transitionDefiningCode = transitionDefiningCode;
    }

    public Transition getTransitionDefiningCode() {
        return this.transitionDefiningCode;
    }
}
