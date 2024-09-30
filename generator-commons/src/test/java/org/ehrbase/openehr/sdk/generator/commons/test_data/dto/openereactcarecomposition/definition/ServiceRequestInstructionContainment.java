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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class ServiceRequestInstructionContainment extends Containment {
    public SelectAqlField<ServiceRequestInstruction> SERVICE_REQUEST_INSTRUCTION = new AqlFieldImp<
            ServiceRequestInstruction>(
            ServiceRequestInstruction.class, "", "ServiceRequestInstruction", ServiceRequestInstruction.class, this);

    public ListSelectAqlField<ServiceRequestCurrentActivityActivity> CURRENT_ACTIVITY =
            new ListAqlFieldImp<ServiceRequestCurrentActivityActivity>(
                    ServiceRequestInstruction.class,
                    "/activities[at0001]",
                    "currentActivity",
                    ServiceRequestCurrentActivityActivity.class,
                    this);

    public SelectAqlField<Cluster> REQUESTER = new AqlFieldImp<Cluster>(
            ServiceRequestInstruction.class, "/protocol[at0008]/items[at0141]", "requester", Cluster.class, this);

    public SelectAqlField<Cluster> RECEIVER = new AqlFieldImp<Cluster>(
            ServiceRequestInstruction.class, "/protocol[at0008]/items[at0142]", "receiver", Cluster.class, this);

    public ListSelectAqlField<Cluster> DISTRIBUTION_LIST = new ListAqlFieldImp<Cluster>(
            ServiceRequestInstruction.class,
            "/protocol[at0008]/items[at0128]",
            "distributionList",
            Cluster.class,
            this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            ServiceRequestInstruction.class, "/protocol[at0008]/items[at0112]", "extension", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(ServiceRequestInstruction.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<String> NARRATIVE_VALUE = new AqlFieldImp<String>(
            ServiceRequestInstruction.class, "/narrative|value", "narrativeValue", String.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(ServiceRequestInstruction.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            ServiceRequestInstruction.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<TemporalAccessor> EXPIRY_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            ServiceRequestInstruction.class, "/expiry_time|value", "expiryTimeValue", TemporalAccessor.class, this);

    private ServiceRequestInstructionContainment() {
        super("openEHR-EHR-INSTRUCTION.service_request.v1");
    }

    public static ServiceRequestInstructionContainment getInstance() {
        return new ServiceRequestInstructionContainment();
    }
}
