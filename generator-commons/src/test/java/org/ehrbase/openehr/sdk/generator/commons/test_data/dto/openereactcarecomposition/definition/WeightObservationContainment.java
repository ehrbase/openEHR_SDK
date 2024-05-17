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
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class WeightObservationContainment extends Containment {
    public SelectAqlField<WeightObservation> WEIGHT_OBSERVATION = new AqlFieldImp<WeightObservation>(
            WeightObservation.class, "", "WeightObservation", WeightObservation.class, this);

    public SelectAqlField<Double> WEIGHT_MAGNITUDE = new AqlFieldImp<Double>(
            WeightObservation.class,
            "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude",
            "weightMagnitude",
            Double.class,
            this);

    public SelectAqlField<String> WEIGHT_UNITS = new AqlFieldImp<String>(
            WeightObservation.class,
            "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units",
            "weightUnits",
            String.class,
            this);

    public SelectAqlField<ItemTree> STATE_STRUCTURE = new AqlFieldImp<ItemTree>(
            WeightObservation.class,
            "/data[at0002]/events[at0003]/state[at0008]",
            "stateStructure",
            ItemTree.class,
            this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            WeightObservation.class,
            "/data[at0002]/events[at0003]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            WeightObservation.class, "/data[at0002]/origin|value", "originValue", TemporalAccessor.class, this);

    public SelectAqlField<Cluster> DEVICE = new AqlFieldImp<Cluster>(
            WeightObservation.class, "/protocol[at0015]/items[at0020]", "device", Cluster.class, this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            WeightObservation.class, "/protocol[at0015]/items[at0027]", "extension", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(WeightObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(WeightObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            WeightObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private WeightObservationContainment() {
        super("openEHR-EHR-OBSERVATION.body_weight.v2");
    }

    public static WeightObservationContainment getInstance() {
        return new WeightObservationContainment();
    }
}
