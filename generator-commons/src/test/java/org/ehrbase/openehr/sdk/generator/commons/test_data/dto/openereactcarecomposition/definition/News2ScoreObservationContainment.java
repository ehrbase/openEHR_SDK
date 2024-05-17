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
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class News2ScoreObservationContainment extends Containment {
    public SelectAqlField<News2ScoreObservation> NEWS2_SCORE_OBSERVATION = new AqlFieldImp<News2ScoreObservation>(
            News2ScoreObservation.class, "", "News2ScoreObservation", News2ScoreObservation.class, this);

    public SelectAqlField<DvOrdinal> RESPIRATION_RATE = new AqlFieldImp<DvOrdinal>(
            News2ScoreObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value",
            "respirationRate",
            DvOrdinal.class,
            this);

    public SelectAqlField<DvOrdinal> SPO_SCALE1 = new AqlFieldImp<DvOrdinal>(
            News2ScoreObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0029]/value",
            "spoScale1",
            DvOrdinal.class,
            this);

    public SelectAqlField<DvOrdinal> SPO_SCALE2 = new AqlFieldImp<DvOrdinal>(
            News2ScoreObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0047]/value",
            "spoScale2",
            DvOrdinal.class,
            this);

    public SelectAqlField<DvOrdinal> AIR_OR_OXYGEN = new AqlFieldImp<DvOrdinal>(
            News2ScoreObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0034]/value",
            "airOrOxygen",
            DvOrdinal.class,
            this);

    public SelectAqlField<DvOrdinal> SYSTOLIC_BLOOD_PRESSURE = new AqlFieldImp<DvOrdinal>(
            News2ScoreObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value",
            "systolicBloodPressure",
            DvOrdinal.class,
            this);

    public SelectAqlField<DvOrdinal> PULSE = new AqlFieldImp<DvOrdinal>(
            News2ScoreObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value",
            "pulse",
            DvOrdinal.class,
            this);

    public SelectAqlField<DvOrdinal> CONSCIOUSNESS = new AqlFieldImp<DvOrdinal>(
            News2ScoreObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value",
            "consciousness",
            DvOrdinal.class,
            this);

    public SelectAqlField<DvOrdinal> TEMPERATURE = new AqlFieldImp<DvOrdinal>(
            News2ScoreObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value",
            "temperature",
            DvOrdinal.class,
            this);

    public SelectAqlField<Long> TOTAL_SCORE_MAGNITUDE = new AqlFieldImp<Long>(
            News2ScoreObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0028]/value|magnitude",
            "totalScoreMagnitude",
            Long.class,
            this);

    public SelectAqlField<ClinicalRiskCategoryDefiningCode> CLINICAL_RISK_CATEGORY_DEFINING_CODE =
            new AqlFieldImp<ClinicalRiskCategoryDefiningCode>(
                    News2ScoreObservation.class,
                    "/data[at0001]/events[at0002]/data[at0003]/items[at0056]/value|defining_code",
                    "clinicalRiskCategoryDefiningCode",
                    ClinicalRiskCategoryDefiningCode.class,
                    this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            News2ScoreObservation.class,
            "/data[at0001]/events[at0002]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            News2ScoreObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            News2ScoreObservation.class, "/protocol[at0045]/items[at0046]", "extension", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(News2ScoreObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(News2ScoreObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            News2ScoreObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private News2ScoreObservationContainment() {
        super("openEHR-EHR-OBSERVATION.news2.v1");
    }

    public static News2ScoreObservationContainment getInstance() {
        return new News2ScoreObservationContainment();
    }
}
