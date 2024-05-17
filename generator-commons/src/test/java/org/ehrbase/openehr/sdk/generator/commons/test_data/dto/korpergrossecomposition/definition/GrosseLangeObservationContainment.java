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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.korpergrossecomposition.definition;

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

public class GrosseLangeObservationContainment extends Containment {
    public SelectAqlField<GrosseLangeObservation> GROSSE_LANGE_OBSERVATION = new AqlFieldImp<GrosseLangeObservation>(
            GrosseLangeObservation.class, "", "GrosseLangeObservation", GrosseLangeObservation.class, this);

    public SelectAqlField<Double> GROSSE_LANGE_MAGNITUDE = new AqlFieldImp<Double>(
            GrosseLangeObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude",
            "grosseLangeMagnitude",
            Double.class,
            this);

    public SelectAqlField<String> GROSSE_LANGE_UNITS = new AqlFieldImp<String>(
            GrosseLangeObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units",
            "grosseLangeUnits",
            String.class,
            this);

    public SelectAqlField<ItemTree> TREE = new AqlFieldImp<ItemTree>(
            GrosseLangeObservation.class, "/data[at0001]/events[at0002]/state[at0013]", "tree", ItemTree.class, this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            GrosseLangeObservation.class,
            "/data[at0001]/events[at0002]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            GrosseLangeObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

    public SelectAqlField<Cluster> GERAT = new AqlFieldImp<Cluster>(
            GrosseLangeObservation.class, "/protocol[at0007]/items[at0011]", "gerat", Cluster.class, this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            GrosseLangeObservation.class, "/protocol[at0007]/items[at0022]", "erweiterung", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(GrosseLangeObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(GrosseLangeObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            GrosseLangeObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private GrosseLangeObservationContainment() {
        super("openEHR-EHR-OBSERVATION.height.v2");
    }

    public static GrosseLangeObservationContainment getInstance() {
        return new GrosseLangeObservationContainment();
    }
}
