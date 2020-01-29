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
    private TransitionDefiningcode transitionDefiningcode2;

    @Path("/ism_transition[at0005]/careflow_step|defining_code")
    private CompletedDefiningcode completedDefiningcode;

    @Path("/ism_transition[at0005]/current_state|defining_code")
    private org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode completedDefiningcode2;

    @Path("/ism_transition[at0003]/current_state|defining_code")
    private org.ehrbase.client.classgenerator.examples.shareddefinition.PlannedDefiningcode plannedDefiningcode;

    @Path("/ism_transition[at0004]/current_state|defining_code")
    private org.ehrbase.client.classgenerator.examples.shareddefinition.ActiveDefiningcode activeDefiningcode;

    @Path("/ism_transition[at0003]/careflow_step|defining_code")
    private PlannedDefiningcode plannedDefiningcode2;

    @Path("/ism_transition[at0004]/careflow_step|defining_code")
    private ActiveDefiningcode activeDefiningcode2;

    @Path("/ism_transition[at0003]/transition|defining_code")
    private TransitionDefiningcode transitionDefiningcode3;

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

    public void setTransitionDefiningcode2(TransitionDefiningcode transitionDefiningcode2) {
        this.transitionDefiningcode2 = transitionDefiningcode2;
    }

    public TransitionDefiningcode getTransitionDefiningcode2() {
        return this.transitionDefiningcode2;
    }

    public void setCompletedDefiningcode(CompletedDefiningcode completedDefiningcode) {
        this.completedDefiningcode = completedDefiningcode;
    }

    public CompletedDefiningcode getCompletedDefiningcode() {
        return this.completedDefiningcode;
    }

    public void setCompletedDefiningcode2(
            org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode completedDefiningcode2) {
        this.completedDefiningcode2 = completedDefiningcode2;
    }

    public org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode getCompletedDefiningcode2(
    ) {
        return this.completedDefiningcode2;
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

    public void setPlannedDefiningcode2(PlannedDefiningcode plannedDefiningcode2) {
        this.plannedDefiningcode2 = plannedDefiningcode2;
    }

    public PlannedDefiningcode getPlannedDefiningcode2() {
        return this.plannedDefiningcode2;
    }

    public void setActiveDefiningcode2(ActiveDefiningcode activeDefiningcode2) {
        this.activeDefiningcode2 = activeDefiningcode2;
    }

    public ActiveDefiningcode getActiveDefiningcode2() {
        return this.activeDefiningcode2;
    }

    public void setTransitionDefiningcode3(TransitionDefiningcode transitionDefiningcode3) {
        this.transitionDefiningcode3 = transitionDefiningcode3;
    }

    public TransitionDefiningcode getTransitionDefiningcode3() {
        return this.transitionDefiningcode3;
    }

    public void setTestAllTypes(List<TestAllTypes4> testAllTypes) {
        this.testAllTypes = testAllTypes;
    }

    public List<TestAllTypes4> getTestAllTypes() {
        return this.testAllTypes;
    }
}
