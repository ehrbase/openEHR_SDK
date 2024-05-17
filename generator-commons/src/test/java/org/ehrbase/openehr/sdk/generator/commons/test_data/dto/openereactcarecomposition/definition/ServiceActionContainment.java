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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Transition;

public class ServiceActionContainment extends Containment {
    public SelectAqlField<ServiceAction> SERVICE_ACTION =
            new AqlFieldImp<ServiceAction>(ServiceAction.class, "", "ServiceAction", ServiceAction.class, this);

    public SelectAqlField<DvCodedText> SERVICE_NAME = new AqlFieldImp<DvCodedText>(
            ServiceAction.class, "/description[at0001]/items[at0011]/value", "serviceName", DvCodedText.class, this);

    public SelectAqlField<String> DESCRIPTION_VALUE = new AqlFieldImp<String>(
            ServiceAction.class,
            "/description[at0001]/items[at0013]/value|value",
            "descriptionValue",
            String.class,
            this);

    public ListSelectAqlField<Cluster> SERVICE_DETAIL = new ListAqlFieldImp<Cluster>(
            ServiceAction.class, "/description[at0001]/items[at0027]", "serviceDetail", Cluster.class, this);

    public ListSelectAqlField<Cluster> MULTIMEDIA = new ListAqlFieldImp<Cluster>(
            ServiceAction.class, "/description[at0001]/items[at0029]", "multimedia", Cluster.class, this);

    public ListSelectAqlField<Cluster> REQUESTOR = new ListAqlFieldImp<Cluster>(
            ServiceAction.class, "/protocol[at0015]/items[at0017]", "requestor", Cluster.class, this);

    public ListSelectAqlField<Cluster> RECEIVER = new ListAqlFieldImp<Cluster>(
            ServiceAction.class, "/protocol[at0015]/items[at0019]", "receiver", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(ServiceAction.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(ServiceAction.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT =
            new AqlFieldImp<FeederAudit>(ServiceAction.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            ServiceAction.class, "/time|value", "timeValue", TemporalAccessor.class, this);

    public SelectAqlField<CareflowStepDefiningCode> CAREFLOW_STEP_DEFINING_CODE =
            new AqlFieldImp<CareflowStepDefiningCode>(
                    ServiceAction.class,
                    "/ism_transition/careflow_step|defining_code",
                    "careflowStepDefiningCode",
                    CareflowStepDefiningCode.class,
                    this);

    public SelectAqlField<CurrentStateDefiningCode> CURRENT_STATE_DEFINING_CODE =
            new AqlFieldImp<CurrentStateDefiningCode>(
                    ServiceAction.class,
                    "/ism_transition/current_state|defining_code",
                    "currentStateDefiningCode",
                    CurrentStateDefiningCode.class,
                    this);

    public SelectAqlField<Transition> TRANSITION_DEFINING_CODE = new AqlFieldImp<Transition>(
            ServiceAction.class,
            "/ism_transition/transition|defining_code",
            "transitionDefiningCode",
            Transition.class,
            this);

    private ServiceActionContainment() {
        super("openEHR-EHR-ACTION.service.v0");
    }

    public static ServiceActionContainment getInstance() {
        return new ServiceActionContainment();
    }
}
