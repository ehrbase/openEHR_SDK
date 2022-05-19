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
package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class RisikogebietSectionContainment extends Containment {
    public SelectAqlField<RisikogebietSection> RISIKOGEBIET_SECTION = new AqlFieldImp<RisikogebietSection>(
            RisikogebietSection.class, "", "RisikogebietSection", RisikogebietSection.class, this);

    public ListSelectAqlField<HistorieDerReiseObservation> HISTORIE_DER_REISE =
            new ListAqlFieldImp<HistorieDerReiseObservation>(
                    RisikogebietSection.class,
                    "/items[openEHR-EHR-OBSERVATION.travel_history.v0]",
                    "historieDerReise",
                    HistorieDerReiseObservation.class,
                    this);

    public ListSelectAqlField<ReisefallObservation> REISEFALL = new ListAqlFieldImp<ReisefallObservation>(
            RisikogebietSection.class,
            "/items[openEHR-EHR-OBSERVATION.travel_event.v0]",
            "reisefall",
            ReisefallObservation.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            RisikogebietSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private RisikogebietSectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static RisikogebietSectionContainment getInstance() {
        return new RisikogebietSectionContainment();
    }
}
