/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class ResponseSectionContainment extends Containment {
    public SelectAqlField<ResponseSection> RESPONSE_SECTION =
            new AqlFieldImp<ResponseSection>(ResponseSection.class, "", "ResponseSection", ResponseSection.class, this);

    public SelectAqlField<RecommendationEvaluation> RECOMMENDATION = new AqlFieldImp<RecommendationEvaluation>(
            ResponseSection.class,
            "/items[openEHR-EHR-EVALUATION.recommendation.v1]",
            "recommendation",
            RecommendationEvaluation.class,
            this);

    public ListSelectAqlField<ServiceRequestInstruction> SERVICE_REQUEST =
            new ListAqlFieldImp<ServiceRequestInstruction>(
                    ResponseSection.class,
                    "/items[openEHR-EHR-INSTRUCTION.service_request.v1]",
                    "serviceRequest",
                    ServiceRequestInstruction.class,
                    this);

    public ListSelectAqlField<ServiceAction> SERVICE = new ListAqlFieldImp<ServiceAction>(
            ResponseSection.class, "/items[openEHR-EHR-ACTION.service.v0]", "service", ServiceAction.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            ResponseSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private ResponseSectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static ResponseSectionContainment getInstance() {
        return new ResponseSectionContainment();
    }
}
