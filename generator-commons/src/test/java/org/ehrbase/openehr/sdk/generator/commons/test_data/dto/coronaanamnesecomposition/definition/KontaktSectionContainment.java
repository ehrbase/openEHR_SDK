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
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class KontaktSectionContainment extends Containment {
    public SelectAqlField<KontaktSection> KONTAKT_SECTION =
            new AqlFieldImp<KontaktSection>(KontaktSection.class, "", "KontaktSection", KontaktSection.class, this);

    public SelectAqlField<PersonenkontaktObservation> PERSONENKONTAKT = new AqlFieldImp<PersonenkontaktObservation>(
            KontaktSection.class,
            "/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]",
            "personenkontakt",
            PersonenkontaktObservation.class,
            this);

    public SelectAqlField<AufenthaltInGesundheitseinrichtungObservation> AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG =
            new AqlFieldImp<AufenthaltInGesundheitseinrichtungObservation>(
                    KontaktSection.class,
                    "/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]",
                    "aufenthaltInGesundheitseinrichtung",
                    AufenthaltInGesundheitseinrichtungObservation.class,
                    this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT =
            new AqlFieldImp<FeederAudit>(KontaktSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private KontaktSectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static KontaktSectionContainment getInstance() {
        return new KontaktSectionContainment();
    }
}
