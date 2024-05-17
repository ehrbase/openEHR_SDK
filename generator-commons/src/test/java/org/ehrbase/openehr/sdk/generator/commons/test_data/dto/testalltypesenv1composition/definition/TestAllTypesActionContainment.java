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
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Transition;

public class TestAllTypesActionContainment extends Containment {
    public SelectAqlField<TestAllTypesAction> TEST_ALL_TYPES_ACTION = new AqlFieldImp<TestAllTypesAction>(
            TestAllTypesAction.class, "", "TestAllTypesAction", TestAllTypesAction.class, this);

    public ListSelectAqlField<TestAllTypesCluster> TEST_ALL_TYPES = new ListAqlFieldImp<TestAllTypesCluster>(
            TestAllTypesAction.class,
            "/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]",
            "testAllTypes",
            TestAllTypesCluster.class,
            this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(TestAllTypesAction.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(TestAllTypesAction.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            TestAllTypesAction.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            TestAllTypesAction.class, "/time|value", "timeValue", TemporalAccessor.class, this);

    public SelectAqlField<CareflowStepDefiningCode> CAREFLOW_STEP_DEFINING_CODE =
            new AqlFieldImp<CareflowStepDefiningCode>(
                    TestAllTypesAction.class,
                    "/ism_transition/careflow_step|defining_code",
                    "careflowStepDefiningCode",
                    CareflowStepDefiningCode.class,
                    this);

    public SelectAqlField<CurrentStateDefiningCode> CURRENT_STATE_DEFINING_CODE =
            new AqlFieldImp<CurrentStateDefiningCode>(
                    TestAllTypesAction.class,
                    "/ism_transition/current_state|defining_code",
                    "currentStateDefiningCode",
                    CurrentStateDefiningCode.class,
                    this);

    public SelectAqlField<Transition> TRANSITION_DEFINING_CODE = new AqlFieldImp<Transition>(
            TestAllTypesAction.class,
            "/ism_transition/transition|defining_code",
            "transitionDefiningCode",
            Transition.class,
            this);

    private TestAllTypesActionContainment() {
        super("openEHR-EHR-ACTION.test_all_types.v1");
    }

    public static TestAllTypesActionContainment getInstance() {
        return new TestAllTypesActionContainment();
    }
}
