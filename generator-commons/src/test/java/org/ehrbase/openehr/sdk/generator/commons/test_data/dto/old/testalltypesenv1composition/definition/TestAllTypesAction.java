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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.testalltypesenv1composition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition.Language;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition.TransitionDefiningcode;

@Entity
@Archetype("openEHR-EHR-ACTION.test_all_types.v1")
public class TestAllTypesAction {
    @Path("/ism_transition[at0004]/transition|defining_code")
    private TransitionDefiningcode transitionDefiningcode;

    @Path("/ism_transition[at0005]/transition|defining_code")
    private TransitionDefiningcode transitionDefiningcodeCompleted;

    @Path("/ism_transition[at0005]/careflow_step|defining_code")
    private CompletedDefiningcode completedDefiningcode;

    @Path("/ism_transition[at0005]/current_state|defining_code")
    private CompletedDefiningcode2 completedDefiningcodeCurrentState;

    @Path("/language")
    private Language language;

    @Path("/ism_transition[at0003]/careflow_step|defining_code")
    private PlannedDefiningcode plannedDefiningcode;

    @Path("/ism_transition[at0003]/transition|defining_code")
    private TransitionDefiningcode transitionDefiningcodePlanned;

    @Path("/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]")
    private List<TestAllTypesCluster> testAllTypes;

    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/ism_transition[at0003]/current_state|defining_code")
    private PlannedDefiningcode2 plannedDefiningcodeCurrentState;

    @Path("/ism_transition[at0004]/current_state|defining_code")
    private ActiveDefiningcode activeDefiningcode;

    @Path("/ism_transition[at0004]/careflow_step|defining_code")
    private ActiveDefiningcode2 activeDefiningcodeCareflowStep;

    @Path("/subject")
    private PartyProxy subject;

    public void setTransitionDefiningcode(TransitionDefiningcode transitionDefiningcode) {
        this.transitionDefiningcode = transitionDefiningcode;
    }

    public TransitionDefiningcode getTransitionDefiningcode() {
        return this.transitionDefiningcode;
    }

    public void setTransitionDefiningcodeCompleted(TransitionDefiningcode transitionDefiningcodeCompleted) {
        this.transitionDefiningcodeCompleted = transitionDefiningcodeCompleted;
    }

    public TransitionDefiningcode getTransitionDefiningcodeCompleted() {
        return this.transitionDefiningcodeCompleted;
    }

    public void setCompletedDefiningcode(CompletedDefiningcode completedDefiningcode) {
        this.completedDefiningcode = completedDefiningcode;
    }

    public CompletedDefiningcode getCompletedDefiningcode() {
        return this.completedDefiningcode;
    }

    public void setCompletedDefiningcodeCurrentState(CompletedDefiningcode2 completedDefiningcodeCurrentState) {
        this.completedDefiningcodeCurrentState = completedDefiningcodeCurrentState;
    }

    public CompletedDefiningcode2 getCompletedDefiningcodeCurrentState() {
        return this.completedDefiningcodeCurrentState;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setPlannedDefiningcode(PlannedDefiningcode plannedDefiningcode) {
        this.plannedDefiningcode = plannedDefiningcode;
    }

    public PlannedDefiningcode getPlannedDefiningcode() {
        return this.plannedDefiningcode;
    }

    public void setTransitionDefiningcodePlanned(TransitionDefiningcode transitionDefiningcodePlanned) {
        this.transitionDefiningcodePlanned = transitionDefiningcodePlanned;
    }

    public TransitionDefiningcode getTransitionDefiningcodePlanned() {
        return this.transitionDefiningcodePlanned;
    }

    public void setTestAllTypes(List<TestAllTypesCluster> testAllTypes) {
        this.testAllTypes = testAllTypes;
    }

    public List<TestAllTypesCluster> getTestAllTypes() {
        return this.testAllTypes;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setPlannedDefiningcodeCurrentState(PlannedDefiningcode2 plannedDefiningcodeCurrentState) {
        this.plannedDefiningcodeCurrentState = plannedDefiningcodeCurrentState;
    }

    public PlannedDefiningcode2 getPlannedDefiningcodeCurrentState() {
        return this.plannedDefiningcodeCurrentState;
    }

    public void setActiveDefiningcode(ActiveDefiningcode activeDefiningcode) {
        this.activeDefiningcode = activeDefiningcode;
    }

    public ActiveDefiningcode getActiveDefiningcode() {
        return this.activeDefiningcode;
    }

    public void setActiveDefiningcodeCareflowStep(ActiveDefiningcode2 activeDefiningcodeCareflowStep) {
        this.activeDefiningcodeCareflowStep = activeDefiningcodeCareflowStep;
    }

    public ActiveDefiningcode2 getActiveDefiningcodeCareflowStep() {
        return this.activeDefiningcodeCareflowStep;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }
}
