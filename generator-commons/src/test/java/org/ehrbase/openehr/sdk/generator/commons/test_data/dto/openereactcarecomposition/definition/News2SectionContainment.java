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
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class News2SectionContainment extends Containment {
    public SelectAqlField<News2Section> NEWS2_SECTION =
            new AqlFieldImp<News2Section>(News2Section.class, "", "News2Section", News2Section.class, this);

    public SelectAqlField<TemperatureObservation> TEMPERATURE = new AqlFieldImp<TemperatureObservation>(
            News2Section.class,
            "/items[openEHR-EHR-OBSERVATION.body_temperature.v1]",
            "temperature",
            TemperatureObservation.class,
            this);

    public SelectAqlField<PulseOximetryObservation> PULSE_OXIMETRY = new AqlFieldImp<PulseOximetryObservation>(
            News2Section.class,
            "/items[openEHR-EHR-OBSERVATION.pulse_oximetry.v1]",
            "pulseOximetry",
            PulseOximetryObservation.class,
            this);

    public SelectAqlField<PulseObservation> PULSE = new AqlFieldImp<PulseObservation>(
            News2Section.class, "/items[openEHR-EHR-OBSERVATION.pulse.v1]", "pulse", PulseObservation.class, this);

    public SelectAqlField<RespirationsObservation> RESPIRATIONS = new AqlFieldImp<RespirationsObservation>(
            News2Section.class,
            "/items[openEHR-EHR-OBSERVATION.respiration.v1]",
            "respirations",
            RespirationsObservation.class,
            this);

    public SelectAqlField<AcvpuScaleObservation> ACVPU_SCALE = new AqlFieldImp<AcvpuScaleObservation>(
            News2Section.class,
            "/items[openEHR-EHR-OBSERVATION.acvpu.v0]",
            "acvpuScale",
            AcvpuScaleObservation.class,
            this);

    public SelectAqlField<BloodPressureObservation> BLOOD_PRESSURE = new AqlFieldImp<BloodPressureObservation>(
            News2Section.class,
            "/items[openEHR-EHR-OBSERVATION.blood_pressure.v2]",
            "bloodPressure",
            BloodPressureObservation.class,
            this);

    public SelectAqlField<News2ScoreObservation> NEWS2_SCORE = new AqlFieldImp<News2ScoreObservation>(
            News2Section.class,
            "/items[openEHR-EHR-OBSERVATION.news2.v1]",
            "news2Score",
            News2ScoreObservation.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT =
            new AqlFieldImp<FeederAudit>(News2Section.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private News2SectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static News2SectionContainment getInstance() {
        return new News2SectionContainment();
    }
}
