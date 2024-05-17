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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class EventsummaryClusterContainment extends Containment {
    public SelectAqlField<EventsummaryCluster> EVENTSUMMARY_CLUSTER = new AqlFieldImp<EventsummaryCluster>(
            EventsummaryCluster.class, "", "EventsummaryCluster", EventsummaryCluster.class, this);

    public SelectAqlField<String> FALLIDENTIFIKATION_VALUE = new AqlFieldImp<String>(
            EventsummaryCluster.class, "/items[at0001]/value|value", "fallidentifikationValue", String.class, this);

    public SelectAqlField<String> FALL_ART_VALUE = new AqlFieldImp<String>(
            EventsummaryCluster.class, "/items[at0002]/value|value", "fallArtValue", String.class, this);

    public ListSelectAqlField<EventsummaryBeteiligtePersonenCluster> BETEILIGTE_PERSONEN =
            new ListAqlFieldImp<EventsummaryBeteiligtePersonenCluster>(
                    EventsummaryCluster.class,
                    "/items[at0007]",
                    "beteiligtePersonen",
                    EventsummaryBeteiligtePersonenCluster.class,
                    this);

    public SelectAqlField<String> FALL_KATEGORIE_VALUE = new AqlFieldImp<String>(
            EventsummaryCluster.class, "/items[at0004]/value|value", "fallKategorieValue", String.class, this);

    public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(
            EventsummaryCluster.class, "/items[at0006]/value|value", "kommentarValue", String.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            EventsummaryCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private EventsummaryClusterContainment() {
        super("openEHR-EHR-CLUSTER.eventsummary.v0");
    }

    public static EventsummaryClusterContainment getInstance() {
        return new EventsummaryClusterContainment();
    }
}
