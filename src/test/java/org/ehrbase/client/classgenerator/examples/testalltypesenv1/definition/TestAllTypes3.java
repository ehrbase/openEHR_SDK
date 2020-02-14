package org.ehrbase.client.classgenerator.examples.testalltypesenv1.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.TransitionDefiningcode;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-ACTION.test_all_types.v1")
public class TestAllTypes3 {
    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/ism_transition[at0004]/transition|defining_code")
    private TransitionDefiningcode transitionDefiningcode;

    @Path("/ism_transition[at0005]/transition|defining_code")
    private TransitionDefiningcode transitiondefiningcodeTransition;

    @Path("/ism_transition[at0005]/careflow_step|defining_code")
    private CompletedDefiningcode completedDefiningcode;

    @Path("/ism_transition[at0005]/current_state|defining_code")
    private org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode completeddefiningcodeCurrentState;

    @Path("/ism_transition[at0003]/current_state|defining_code")
    private org.ehrbase.client.classgenerator.examples.shareddefinition.PlannedDefiningcode plannedDefiningcode;

    @Path("/ism_transition[at0004]/current_state|defining_code")
    private org.ehrbase.client.classgenerator.examples.shareddefinition.ActiveDefiningcode activeDefiningcode;

    @Path("/ism_transition[at0003]/careflow_step|defining_code")
    private PlannedDefiningcode planneddefiningcodeCareflowStep;

    @Path("/ism_transition[at0004]/careflow_step|defining_code")
    private ActiveDefiningcode activedefiningcodeCareflowStep;

    @Path("/ism_transition[at0003]/transition|defining_code")
    private TransitionDefiningcode transitiondefiningcodetransitionIsmTransitionAt0003;

    @Path("/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]")
    private List<TestAllTypes4> testAllTypes;

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

    public void setTransitiondefiningcodeTransition(
            TransitionDefiningcode transitiondefiningcodeTransition) {
        this.transitiondefiningcodeTransition = transitiondefiningcodeTransition;
    }

    public TransitionDefiningcode getTransitiondefiningcodeTransition() {
        return this.transitiondefiningcodeTransition;
    }

    public void setCompletedDefiningcode(CompletedDefiningcode completedDefiningcode) {
        this.completedDefiningcode = completedDefiningcode;
    }

    public CompletedDefiningcode getCompletedDefiningcode() {
        return this.completedDefiningcode;
    }

    public void setCompleteddefiningcodeCurrentState(
            org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode completeddefiningcodeCurrentState) {
        this.completeddefiningcodeCurrentState = completeddefiningcodeCurrentState;
    }

    public org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode getCompleteddefiningcodeCurrentState(
    ) {
        return this.completeddefiningcodeCurrentState;
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

    public void setPlanneddefiningcodeCareflowStep(
            PlannedDefiningcode planneddefiningcodeCareflowStep) {
        this.planneddefiningcodeCareflowStep = planneddefiningcodeCareflowStep;
    }

    public PlannedDefiningcode getPlanneddefiningcodeCareflowStep() {
        return this.planneddefiningcodeCareflowStep;
    }

    public void setActivedefiningcodeCareflowStep(ActiveDefiningcode activedefiningcodeCareflowStep) {
        this.activedefiningcodeCareflowStep = activedefiningcodeCareflowStep;
    }

    public ActiveDefiningcode getActivedefiningcodeCareflowStep() {
        return this.activedefiningcodeCareflowStep;
    }

    public void setTransitiondefiningcodetransitionIsmTransitionAt0003(
            TransitionDefiningcode transitiondefiningcodetransitionIsmTransitionAt0003) {
        this.transitiondefiningcodetransitionIsmTransitionAt0003 = transitiondefiningcodetransitionIsmTransitionAt0003;
    }

    public TransitionDefiningcode getTransitiondefiningcodetransitionIsmTransitionAt0003() {
        return this.transitiondefiningcodetransitionIsmTransitionAt0003;
    }

    public void setTestAllTypes(List<TestAllTypes4> testAllTypes) {
        this.testAllTypes = testAllTypes;
    }

    public List<TestAllTypes4> getTestAllTypes() {
        return this.testAllTypes;
    }
}
