package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.TransitionDefiningcode;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-ACTION.test_all_types.v1")
public class TestAllTypesAction {
    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/ism_transition[at0004]/transition|defining_code")
    private TransitionDefiningcode transitionDefiningcode;

    @Path("/ism_transition[at0005]/transition|defining_code")
    private TransitionDefiningcode transitionDefiningcodeTransition;

    @Path("/ism_transition[at0005]/careflow_step|defining_code")
    private CompletedDefiningcode completedDefiningcode;

    @Path("/ism_transition[at0005]/current_state|defining_code")
    private org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode completedDefiningcodeCurrentState;

    @Path("/ism_transition[at0003]/current_state|defining_code")
    private org.ehrbase.client.classgenerator.examples.shareddefinition.PlannedDefiningcode plannedDefiningcode;

    @Path("/ism_transition[at0004]/current_state|defining_code")
    private org.ehrbase.client.classgenerator.examples.shareddefinition.ActiveDefiningcode activeDefiningcode;

    @Path("/ism_transition[at0003]/careflow_step|defining_code")
    private PlannedDefiningcode plannedDefiningcodeCareflowStep;

    @Path("/ism_transition[at0004]/careflow_step|defining_code")
    private ActiveDefiningcode activeDefiningcodeCareflowStep;

    @Path("/ism_transition[at0003]/transition|defining_code")
    private TransitionDefiningcode transitionDefiningcodeTransitionIsmTransitionAt0003;

    @Path("/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]")
    private List<TestAllTypesCluster> testAllTypes;

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setTransitionDefiningcode(TransitionDefiningcode transitionDefiningcode) {
        this.transitionDefiningcode = transitionDefiningcode;
    }

    public TransitionDefiningcode getTransitionDefiningcode() {
        return this.transitionDefiningcode;
    }

    public void setTransitionDefiningcodeTransition(
            TransitionDefiningcode transitionDefiningcodeTransition) {
        this.transitionDefiningcodeTransition = transitionDefiningcodeTransition;
    }

    public TransitionDefiningcode getTransitionDefiningcodeTransition() {
        return this.transitionDefiningcodeTransition;
    }

    public void setCompletedDefiningcode(CompletedDefiningcode completedDefiningcode) {
        this.completedDefiningcode = completedDefiningcode;
    }

    public CompletedDefiningcode getCompletedDefiningcode() {
        return this.completedDefiningcode;
    }

    public void setCompletedDefiningcodeCurrentState(
            org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode completedDefiningcodeCurrentState) {
        this.completedDefiningcodeCurrentState = completedDefiningcodeCurrentState;
    }

    public org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode getCompletedDefiningcodeCurrentState(
    ) {
        return this.completedDefiningcodeCurrentState;
    }

    public void setPlannedDefiningcode(
            org.ehrbase.client.classgenerator.examples.shareddefinition.PlannedDefiningcode plannedDefiningcode) {
        this.plannedDefiningcode = plannedDefiningcode;
    }

    public org.ehrbase.client.classgenerator.examples.shareddefinition.PlannedDefiningcode getPlannedDefiningcode(
    ) {
        return this.plannedDefiningcode;
    }

    public void setActiveDefiningcode(
            org.ehrbase.client.classgenerator.examples.shareddefinition.ActiveDefiningcode activeDefiningcode) {
        this.activeDefiningcode = activeDefiningcode;
    }

    public org.ehrbase.client.classgenerator.examples.shareddefinition.ActiveDefiningcode getActiveDefiningcode(
    ) {
        return this.activeDefiningcode;
    }

    public void setPlannedDefiningcodeCareflowStep(
            PlannedDefiningcode plannedDefiningcodeCareflowStep) {
        this.plannedDefiningcodeCareflowStep = plannedDefiningcodeCareflowStep;
    }

    public PlannedDefiningcode getPlannedDefiningcodeCareflowStep() {
        return this.plannedDefiningcodeCareflowStep;
    }

    public void setActiveDefiningcodeCareflowStep(ActiveDefiningcode activeDefiningcodeCareflowStep) {
        this.activeDefiningcodeCareflowStep = activeDefiningcodeCareflowStep;
    }

    public ActiveDefiningcode getActiveDefiningcodeCareflowStep() {
        return this.activeDefiningcodeCareflowStep;
    }

    public void setTransitionDefiningcodeTransitionIsmTransitionAt0003(
            TransitionDefiningcode transitionDefiningcodeTransitionIsmTransitionAt0003) {
        this.transitionDefiningcodeTransitionIsmTransitionAt0003 = transitionDefiningcodeTransitionIsmTransitionAt0003;
    }

    public TransitionDefiningcode getTransitionDefiningcodeTransitionIsmTransitionAt0003() {
        return this.transitionDefiningcodeTransitionIsmTransitionAt0003;
    }

    public void setTestAllTypes(List<TestAllTypesCluster> testAllTypes) {
        this.testAllTypes = testAllTypes;
    }

    public List<TestAllTypesCluster> getTestAllTypes() {
        return this.testAllTypes;
    }
}
