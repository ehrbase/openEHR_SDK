package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;

public class TestAllTypesActionContainment extends Containment {
  public SelectAqlField<TestAllTypesAction> TEST_ALL_TYPES_ACTION = new AqlFieldImp<TestAllTypesAction>(TestAllTypesAction.class, "", "TestAllTypesAction", TestAllTypesAction.class, this);

  public ListSelectAqlField<TestAllTypesCluster> TEST_ALL_TYPES = new ListAqlFieldImp<TestAllTypesCluster>(TestAllTypesAction.class, "/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]", "testAllTypes", TestAllTypesCluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(TestAllTypesAction.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(TestAllTypesAction.class, "/language", "language", Language.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(TestAllTypesAction.class, "/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<CareflowStepDefiningCode> CAREFLOW_STEP_DEFINING_CODE = new AqlFieldImp<CareflowStepDefiningCode>(TestAllTypesAction.class, "/ism_transition/careflow_step|defining_code", "careflowStepDefiningCode", CareflowStepDefiningCode.class, this);

  public SelectAqlField<CurrentStateDefiningCode> CURRENT_STATE_DEFINING_CODE = new AqlFieldImp<CurrentStateDefiningCode>(TestAllTypesAction.class, "/ism_transition/current_state|defining_code", "currentStateDefiningCode", CurrentStateDefiningCode.class, this);

  public SelectAqlField<CodePhrase> TRANSITION_DEFINING_CODE = new AqlFieldImp<CodePhrase>(TestAllTypesAction.class, "/ism_transition/transition|defining_code", "transitionDefiningCode", CodePhrase.class, this);

  private TestAllTypesActionContainment() {
    super("openEHR-EHR-ACTION.test_all_types.v1");
  }

  public static TestAllTypesActionContainment getInstance() {
    return new TestAllTypesActionContainment();
  }
}
