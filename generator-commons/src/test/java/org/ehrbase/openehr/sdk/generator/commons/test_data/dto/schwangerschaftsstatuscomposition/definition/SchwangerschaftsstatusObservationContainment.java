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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.schwangerschaftsstatuscomposition.definition;

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

public class SchwangerschaftsstatusObservationContainment extends Containment {
    public SelectAqlField<SchwangerschaftsstatusObservation> SCHWANGERSCHAFTSSTATUS_OBSERVATION =
            new AqlFieldImp<SchwangerschaftsstatusObservation>(
                    SchwangerschaftsstatusObservation.class,
                    "",
                    "SchwangerschaftsstatusObservation",
                    SchwangerschaftsstatusObservation.class,
                    this);

    public SelectAqlField<StatusDefiningCode2> STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode2>(
            SchwangerschaftsstatusObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value|defining_code",
            "statusDefiningCode",
            StatusDefiningCode2.class,
            this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            SchwangerschaftsstatusObservation.class,
            "/data[at0001]/events[at0002]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            SchwangerschaftsstatusObservation.class,
            "/data[at0001]/origin|value",
            "originValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Cluster> ERWEITERUNGEN = new ListAqlFieldImp<Cluster>(
            SchwangerschaftsstatusObservation.class,
            "/protocol[at0021]/items[at0022]",
            "erweiterungen",
            Cluster.class,
            this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(
            SchwangerschaftsstatusObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            SchwangerschaftsstatusObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            SchwangerschaftsstatusObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private SchwangerschaftsstatusObservationContainment() {
        super("openEHR-EHR-OBSERVATION.pregnancy_status.v0");
    }

    public static SchwangerschaftsstatusObservationContainment getInstance() {
        return new SchwangerschaftsstatusObservationContainment();
    }
}
