package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.TransitionDefiningcode;

public class TestAllTypesActionContainment extends Containment {
  public SelectAqlField<TestAllTypesAction> TEST_ALL_TYPES_ACTION =
      new AqlFieldImp<TestAllTypesAction>(
          TestAllTypesAction.class, "", "TestAllTypesAction", TestAllTypesAction.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE =
      new AqlFieldImp<TransitionDefiningcode>(
          TestAllTypesAction.class,
          "/ism_transition[at0004]/transition|defining_code",
          "transitionDefiningcode",
          TransitionDefiningcode.class,
          this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_COMPLETED =
      new AqlFieldImp<TransitionDefiningcode>(
          TestAllTypesAction.class,
          "/ism_transition[at0005]/transition|defining_code",
          "transitionDefiningcodeCompleted",
          TransitionDefiningcode.class,
          this);

  public SelectAqlField<CompletedDefiningcode> COMPLETED_DEFININGCODE =
      new AqlFieldImp<CompletedDefiningcode>(
          TestAllTypesAction.class,
          "/ism_transition[at0005]/careflow_step|defining_code",
          "completedDefiningcode",
          CompletedDefiningcode.class,
          this);

  public SelectAqlField<CompletedDefiningcode2> COMPLETED_DEFININGCODE_CURRENT_STATE =
      new AqlFieldImp<CompletedDefiningcode2>(
          TestAllTypesAction.class,
          "/ism_transition[at0005]/current_state|defining_code",
          "completedDefiningcodeCurrentState",
          CompletedDefiningcode2.class,
          this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          TestAllTypesAction.class, "/language", "language", Language.class, this);

  public SelectAqlField<PlannedDefiningcode> PLANNED_DEFININGCODE =
      new AqlFieldImp<PlannedDefiningcode>(
          TestAllTypesAction.class,
          "/ism_transition[at0003]/careflow_step|defining_code",
          "plannedDefiningcode",
          PlannedDefiningcode.class,
          this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_PLANNED =
      new AqlFieldImp<TransitionDefiningcode>(
          TestAllTypesAction.class,
          "/ism_transition[at0003]/transition|defining_code",
          "transitionDefiningcodePlanned",
          TransitionDefiningcode.class,
          this);

  public ListSelectAqlField<TestAllTypesCluster> TEST_ALL_TYPES =
      new ListAqlFieldImp<TestAllTypesCluster>(
          TestAllTypesAction.class,
          "/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]",
          "testAllTypes",
          TestAllTypesCluster.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          TestAllTypesAction.class, "/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<PlannedDefiningcode2> PLANNED_DEFININGCODE_CURRENT_STATE =
      new AqlFieldImp<PlannedDefiningcode2>(
          TestAllTypesAction.class,
          "/ism_transition[at0003]/current_state|defining_code",
          "plannedDefiningcodeCurrentState",
          PlannedDefiningcode2.class,
          this);

  public SelectAqlField<ActiveDefiningcode> ACTIVE_DEFININGCODE =
      new AqlFieldImp<ActiveDefiningcode>(
          TestAllTypesAction.class,
          "/ism_transition[at0004]/current_state|defining_code",
          "activeDefiningcode",
          ActiveDefiningcode.class,
          this);

  public SelectAqlField<ActiveDefiningcode2> ACTIVE_DEFININGCODE_CAREFLOW_STEP =
      new AqlFieldImp<ActiveDefiningcode2>(
          TestAllTypesAction.class,
          "/ism_transition[at0004]/careflow_step|defining_code",
          "activeDefiningcodeCareflowStep",
          ActiveDefiningcode2.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          TestAllTypesAction.class, "/subject", "subject", PartyProxy.class, this);

  private TestAllTypesActionContainment() {
    super("openEHR-EHR-ACTION.test_all_types.v1");
  }

  public static TestAllTypesActionContainment getInstance() {
    return new TestAllTypesActionContainment();
  }
}
