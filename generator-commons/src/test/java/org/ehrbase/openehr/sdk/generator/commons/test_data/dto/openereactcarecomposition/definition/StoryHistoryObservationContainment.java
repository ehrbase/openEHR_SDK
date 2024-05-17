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
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class StoryHistoryObservationContainment extends Containment {
    public SelectAqlField<StoryHistoryObservation> STORY_HISTORY_OBSERVATION = new AqlFieldImp<StoryHistoryObservation>(
            StoryHistoryObservation.class, "", "StoryHistoryObservation", StoryHistoryObservation.class, this);

    public ListSelectAqlField<StoryHistorySoftSignsElement> SOFT_SIGNS =
            new ListAqlFieldImp<StoryHistorySoftSignsElement>(
                    StoryHistoryObservation.class,
                    "/data[at0001]/events[at0002]/data[at0003]/items[at0004]",
                    "softSigns",
                    StoryHistorySoftSignsElement.class,
                    this);

    public SelectAqlField<String> NOTES_VALUE = new AqlFieldImp<String>(
            StoryHistoryObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|value",
            "notesValue",
            String.class,
            this);

    public ListSelectAqlField<Cluster> STRUCTURED_DETAIL = new ListAqlFieldImp<Cluster>(
            StoryHistoryObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0006]",
            "structuredDetail",
            Cluster.class,
            this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            StoryHistoryObservation.class,
            "/data[at0001]/events[at0002]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            StoryHistoryObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            StoryHistoryObservation.class, "/protocol[at0007]/items[at0008]", "extension", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(StoryHistoryObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(StoryHistoryObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            StoryHistoryObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private StoryHistoryObservationContainment() {
        super("openEHR-EHR-OBSERVATION.story.v1");
    }

    public static StoryHistoryObservationContainment getInstance() {
        return new StoryHistoryObservationContainment();
    }
}
