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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.coronaanamnesecomposition.definition;

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

public class ReisefallObservationContainment extends Containment {
    public SelectAqlField<ReisefallObservation> REISEFALL_OBSERVATION = new AqlFieldImp<ReisefallObservation>(
            ReisefallObservation.class, "", "ReisefallObservation", ReisefallObservation.class, this);

    public ListSelectAqlField<ReisefallBeliebigesIntervallereignisIntervalEvent> BELIEBIGES_INTERVALLEREIGNIS =
            new ListAqlFieldImp<ReisefallBeliebigesIntervallereignisIntervalEvent>(
                    ReisefallObservation.class,
                    "/data[at0001]/events[at0002]",
                    "beliebigesIntervallereignis",
                    ReisefallBeliebigesIntervallereignisIntervalEvent.class,
                    this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            ReisefallObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            ReisefallObservation.class, "/protocol[at0007]/items[at0021]", "erweiterung", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(ReisefallObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(ReisefallObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            ReisefallObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private ReisefallObservationContainment() {
        super("openEHR-EHR-OBSERVATION.travel_event.v0");
    }

    public static ReisefallObservationContainment getInstance() {
        return new ReisefallObservationContainment();
    }
}
