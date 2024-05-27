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
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class AcvpuScaleObservationContainment extends Containment {
    public SelectAqlField<AcvpuScaleObservation> ACVPU_SCALE_OBSERVATION = new AqlFieldImp<AcvpuScaleObservation>(
            AcvpuScaleObservation.class, "", "AcvpuScaleObservation", AcvpuScaleObservation.class, this);

    public SelectAqlField<AcvpuDefiningCode> ACVPU_DEFINING_CODE = new AqlFieldImp<AcvpuDefiningCode>(
            AcvpuScaleObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code",
            "acvpuDefiningCode",
            AcvpuDefiningCode.class,
            this);

    public SelectAqlField<ItemTree> TREE = new AqlFieldImp<ItemTree>(
            AcvpuScaleObservation.class, "/data[at0001]/events[at0002]/state[at0013]", "tree", ItemTree.class, this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            AcvpuScaleObservation.class,
            "/data[at0001]/events[at0002]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            AcvpuScaleObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            AcvpuScaleObservation.class, "/protocol[at0009]/items[at0011]", "extension", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(AcvpuScaleObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(AcvpuScaleObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            AcvpuScaleObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private AcvpuScaleObservationContainment() {
        super("openEHR-EHR-OBSERVATION.acvpu.v0");
    }

    public static AcvpuScaleObservationContainment getInstance() {
        return new AcvpuScaleObservationContainment();
    }
}
