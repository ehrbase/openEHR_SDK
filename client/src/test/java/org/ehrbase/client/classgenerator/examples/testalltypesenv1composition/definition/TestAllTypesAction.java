package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.TransitionDefiningcode;

import java.time.temporal.TemporalAccessor;
import java.util.List;

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
    private org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode completedDefiningcodeCurrentState;

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
    private org.ehrbase.client.classgenerator.examples.shareddefinition.PlannedDefiningcode plannedDefiningcodeCurrentState;

    @Path("/ism_transition[at0004]/current_state|defining_code")
    private org.ehrbase.client.classgenerator.examples.shareddefinition.ActiveDefiningcode activeDefiningcode;

    @Path("/ism_transition[at0004]/careflow_step|defining_code")
    private ActiveDefiningcode activeDefiningcodeCareflowStep;

    @Path("/subject")
    private PartyProxy subject;

    public void setTransitionDefiningcode(TransitionDefiningcode transitionDefiningcode) {
        this.transitionDefiningcode = transitionDefiningcode;
    }

    public TransitionDefiningcode getTransitionDefiningcode() {
        return this.transitionDefiningcode;
    }

    public void setTransitionDefiningcodeCompleted(
            TransitionDefiningcode transitionDefiningcodeCompleted) {
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

    public void setCompletedDefiningcodeCurrentState(
            org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode completedDefiningcodeCurrentState) {
        this.completedDefiningcodeCurrentState = completedDefiningcodeCurrentState;
    }

    public org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode getCompletedDefiningcodeCurrentState(
    ) {
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

    public void setTransitionDefiningcodePlanned(
            TransitionDefiningcode transitionDefiningcodePlanned) {
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

    public void setPlannedDefiningcodeCurrentState(
            org.ehrbase.client.classgenerator.examples.shareddefinition.PlannedDefiningcode plannedDefiningcodeCurrentState) {
        this.plannedDefiningcodeCurrentState = plannedDefiningcodeCurrentState;
    }

    public org.ehrbase.client.classgenerator.examples.shareddefinition.PlannedDefiningcode getPlannedDefiningcodeCurrentState(
    ) {
        return this.plannedDefiningcodeCurrentState;
    }

    public void setActiveDefiningcode(
            org.ehrbase.client.classgenerator.examples.shareddefinition.ActiveDefiningcode activeDefiningcode) {
        this.activeDefiningcode = activeDefiningcode;
    }

    public org.ehrbase.client.classgenerator.examples.shareddefinition.ActiveDefiningcode getActiveDefiningcode(
    ) {
        return this.activeDefiningcode;
    }

    public void setActiveDefiningcodeCareflowStep(ActiveDefiningcode activeDefiningcodeCareflowStep) {
        this.activeDefiningcodeCareflowStep = activeDefiningcodeCareflowStep;
    }

    public ActiveDefiningcode getActiveDefiningcodeCareflowStep() {
        return this.activeDefiningcodeCareflowStep;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }
}
