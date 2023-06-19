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

public class RespirationsObservationContainment extends Containment {
    public SelectAqlField<RespirationsObservation> RESPIRATIONS_OBSERVATION = new AqlFieldImp<RespirationsObservation>(
            RespirationsObservation.class, "", "RespirationsObservation", RespirationsObservation.class, this);

    public SelectAqlField<Double> RATE_MAGNITUDE = new AqlFieldImp<Double>(
            RespirationsObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude",
            "rateMagnitude",
            Double.class,
            this);

    public SelectAqlField<String> RATE_UNITS = new AqlFieldImp<String>(
            RespirationsObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units",
            "rateUnits",
            String.class,
            this);

    public SelectAqlField<Cluster> INSPIRED_OXYGEN = new AqlFieldImp<Cluster>(
            RespirationsObservation.class,
            "/data[at0001]/events[at0002]/state[at0022]/items[at0055]",
            "inspiredOxygen",
            Cluster.class,
            this);

    public SelectAqlField<Cluster> EXERTION = new AqlFieldImp<Cluster>(
            RespirationsObservation.class,
            "/data[at0001]/events[at0002]/state[at0022]/items[at0037]",
            "exertion",
            Cluster.class,
            this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            RespirationsObservation.class,
            "/data[at0001]/events[at0002]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            RespirationsObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            RespirationsObservation.class, "/protocol[at0057]/items[at0058]", "extension", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(RespirationsObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(RespirationsObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            RespirationsObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private RespirationsObservationContainment() {
        super("openEHR-EHR-OBSERVATION.respiration.v1");
    }

    public static RespirationsObservationContainment getInstance() {
        return new RespirationsObservationContainment();
    }
}
