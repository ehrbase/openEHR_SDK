package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.TransitionDefiningcode;

import java.time.temporal.TemporalAccessor;

public class TestAllTypesActionContainment extends Containment {
  public SelectAqlField<TestAllTypesAction> TEST_ALL_TYPES_ACTION = new AqlFieldImp<TestAllTypesAction>(TestAllTypesAction.class, "", "TestAllTypesAction", TestAllTypesAction.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(TestAllTypesAction.class, "/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE = new AqlFieldImp<TransitionDefiningcode>(TestAllTypesAction.class, "/ism_transition[at0004]/transition|defining_code", "transitionDefiningcode", TransitionDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_TRANSITION = new AqlFieldImp<TransitionDefiningcode>(TestAllTypesAction.class, "/ism_transition[at0005]/transition|defining_code", "transitionDefiningcodeTransition", TransitionDefiningcode.class, this);

  public SelectAqlField<CompletedDefiningcode> COMPLETED_DEFININGCODE = new AqlFieldImp<CompletedDefiningcode>(TestAllTypesAction.class, "/ism_transition[at0005]/careflow_step|defining_code", "completedDefiningcode", CompletedDefiningcode.class, this);

  public SelectAqlField<org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode> COMPLETED_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode>(TestAllTypesAction.class, "/ism_transition[at0005]/current_state|defining_code", "completedDefiningcodeCurrentState", org.ehrbase.client.classgenerator.examples.shareddefinition.CompletedDefiningcode.class, this);

  public SelectAqlField<org.ehrbase.client.classgenerator.examples.shareddefinition.PlannedDefiningcode> PLANNED_DEFININGCODE = new AqlFieldImp<org.ehrbase.client.classgenerator.examples.shareddefinition.PlannedDefiningcode>(TestAllTypesAction.class, "/ism_transition[at0003]/current_state|defining_code", "plannedDefiningcode", org.ehrbase.client.classgenerator.examples.shareddefinition.PlannedDefiningcode.class, this);

  public SelectAqlField<org.ehrbase.client.classgenerator.examples.shareddefinition.ActiveDefiningcode> ACTIVE_DEFININGCODE = new AqlFieldImp<org.ehrbase.client.classgenerator.examples.shareddefinition.ActiveDefiningcode>(TestAllTypesAction.class, "/ism_transition[at0004]/current_state|defining_code", "activeDefiningcode", org.ehrbase.client.classgenerator.examples.shareddefinition.ActiveDefiningcode.class, this);

  public SelectAqlField<PlannedDefiningcode> PLANNED_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<PlannedDefiningcode>(TestAllTypesAction.class, "/ism_transition[at0003]/careflow_step|defining_code", "plannedDefiningcodeCareflowStep", PlannedDefiningcode.class, this);

  public SelectAqlField<ActiveDefiningcode> ACTIVE_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<ActiveDefiningcode>(TestAllTypesAction.class, "/ism_transition[at0004]/careflow_step|defining_code", "activeDefiningcodeCareflowStep", ActiveDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_TRANSITION_ISM_TRANSITION_AT0003 = new AqlFieldImp<TransitionDefiningcode>(TestAllTypesAction.class, "/ism_transition[at0003]/transition|defining_code", "transitionDefiningcodeTransitionIsmTransitionAt0003", TransitionDefiningcode.class, this);

  public ListSelectAqlField<TestAllTypesCluster> TEST_ALL_TYPES = new ListAqlFieldImp<TestAllTypesCluster>(TestAllTypesAction.class, "/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]", "testAllTypes", TestAllTypesCluster.class, this);

  private TestAllTypesActionContainment() {
    super("openEHR-EHR-ACTION.test_all_types.v1");
  }

  public static TestAllTypesActionContainment getInstance() {
    return new TestAllTypesActionContainment();
  }
}
