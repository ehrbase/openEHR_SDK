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
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class Covid19ExposureEvaluationContainment extends Containment {
    public SelectAqlField<Covid19ExposureEvaluation> COVID19_EXPOSURE_EVALUATION = new AqlFieldImp<
            Covid19ExposureEvaluation>(
            Covid19ExposureEvaluation.class, "", "Covid19ExposureEvaluation", Covid19ExposureEvaluation.class, this);

    public SelectAqlField<HealthRiskDefiningCode> HEALTH_RISK_DEFINING_CODE = new AqlFieldImp<HealthRiskDefiningCode>(
            Covid19ExposureEvaluation.class,
            "/data[at0001]/items[at0002.1]/value|defining_code",
            "healthRiskDefiningCode",
            HealthRiskDefiningCode.class,
            this);

    public SelectAqlField<RiskFactorDefiningCode> CARE_HOME_HAS_SUSPECTED_CONFIRMED_COVID19_RISK_FACTOR_DEFINING_CODE =
            new AqlFieldImp<RiskFactorDefiningCode>(
                    Covid19ExposureEvaluation.class,
                    "/data[at0001]/items[at0016]/items[at0013.1]/value|defining_code",
                    "careHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode",
                    RiskFactorDefiningCode.class,
                    this);

    public SelectAqlField<PresenceDefiningCode2> CARE_HOME_HAS_SUSPECTED_CONFIRMED_COVID19_PRESENCE_DEFINING_CODE =
            new AqlFieldImp<PresenceDefiningCode2>(
                    Covid19ExposureEvaluation.class,
                    "/data[at0001]/items[at0016]/items[at0017.1]/value|defining_code",
                    "careHomeHasSuspectedConfirmedCovid19PresenceDefiningCode",
                    PresenceDefiningCode2.class,
                    this);

    public ListSelectAqlField<Cluster> CARE_HOME_HAS_SUSPECTED_CONFIRMED_COVID19_DETAIL = new ListAqlFieldImp<Cluster>(
            Covid19ExposureEvaluation.class,
            "/data[at0001]/items[at0016]/items[at0027.1]",
            "careHomeHasSuspectedConfirmedCovid19Detail",
            Cluster.class,
            this);

    public SelectAqlField<RiskFactorDefiningCode2> CONTACT_WITH_CONFIRMED_CASE_RISK_FACTOR_DEFINING_CODE =
            new AqlFieldImp<RiskFactorDefiningCode2>(
                    Covid19ExposureEvaluation.class,
                    "/data[at0001]/items[at0016]/items[at0013.1]/value|defining_code",
                    "contactWithConfirmedCaseRiskFactorDefiningCode",
                    RiskFactorDefiningCode2.class,
                    this);

    public SelectAqlField<PresenceDefiningCode2> CONTACT_WITH_CONFIRMED_CASE_PRESENCE_DEFINING_CODE =
            new AqlFieldImp<PresenceDefiningCode2>(
                    Covid19ExposureEvaluation.class,
                    "/data[at0001]/items[at0016]/items[at0017.1]/value|defining_code",
                    "contactWithConfirmedCasePresenceDefiningCode",
                    PresenceDefiningCode2.class,
                    this);

    public ListSelectAqlField<Cluster> CONTACT_WITH_CONFIRMED_CASE_DETAIL = new ListAqlFieldImp<Cluster>(
            Covid19ExposureEvaluation.class,
            "/data[at0001]/items[at0016]/items[at0027.1]",
            "contactWithConfirmedCaseDetail",
            Cluster.class,
            this);

    public SelectAqlField<RiskFactorDefiningCode3> OTHER_RESIDENTS_HOUSEHOLD_MEMBERS_UNWELL_RISK_FACTOR_DEFINING_CODE =
            new AqlFieldImp<RiskFactorDefiningCode3>(
                    Covid19ExposureEvaluation.class,
                    "/data[at0001]/items[at0016]/items[at0013.1]/value|defining_code",
                    "otherResidentsHouseholdMembersUnwellRiskFactorDefiningCode",
                    RiskFactorDefiningCode3.class,
                    this);

    public SelectAqlField<PresenceDefiningCode2> OTHER_RESIDENTS_HOUSEHOLD_MEMBERS_UNWELL_PRESENCE_DEFINING_CODE =
            new AqlFieldImp<PresenceDefiningCode2>(
                    Covid19ExposureEvaluation.class,
                    "/data[at0001]/items[at0016]/items[at0017.1]/value|defining_code",
                    "otherResidentsHouseholdMembersUnwellPresenceDefiningCode",
                    PresenceDefiningCode2.class,
                    this);

    public ListSelectAqlField<Cluster> OTHER_RESIDENTS_HOUSEHOLD_MEMBERS_UNWELL_DETAIL = new ListAqlFieldImp<Cluster>(
            Covid19ExposureEvaluation.class,
            "/data[at0001]/items[at0016]/items[at0027.1]",
            "otherResidentsHouseholdMembersUnwellDetail",
            Cluster.class,
            this);

    public SelectAqlField<RiskAssessmentDefiningCode> RISK_ASSESSMENT_DEFINING_CODE =
            new AqlFieldImp<RiskAssessmentDefiningCode>(
                    Covid19ExposureEvaluation.class,
                    "/data[at0001]/items[at0003.1]/value|defining_code",
                    "riskAssessmentDefiningCode",
                    RiskAssessmentDefiningCode.class,
                    this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            Covid19ExposureEvaluation.class, "/protocol[at0010]/items[at0011]", "extension", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(Covid19ExposureEvaluation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(Covid19ExposureEvaluation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            Covid19ExposureEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private Covid19ExposureEvaluationContainment() {
        super("openEHR-EHR-EVALUATION.health_risk-covid.v0");
    }

    public static Covid19ExposureEvaluationContainment getInstance() {
        return new Covid19ExposureEvaluationContainment();
    }
}
