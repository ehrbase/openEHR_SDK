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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.definition;

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

public class BodyTemperatureObservationContainment extends Containment {
    public SelectAqlField<BodyTemperatureObservation> BODY_TEMPERATURE_OBSERVATION = new AqlFieldImp<
            BodyTemperatureObservation>(
            BodyTemperatureObservation.class, "", "BodyTemperatureObservation", BodyTemperatureObservation.class, this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            BodyTemperatureObservation.class,
            "/data[at0002]/origin|value",
            "originValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Cluster> STRUCTURED_MEASUREMENT_LOCATION = new ListAqlFieldImp<Cluster>(
            BodyTemperatureObservation.class,
            "/protocol[at0020]/items[at0064]",
            "structuredMeasurementLocation",
            Cluster.class,
            this);

    public SelectAqlField<Cluster> DEVICE = new AqlFieldImp<Cluster>(
            BodyTemperatureObservation.class, "/protocol[at0020]/items[at0059]", "device", Cluster.class, this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            BodyTemperatureObservation.class, "/protocol[at0020]/items[at0062]", "extension", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(
            BodyTemperatureObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(BodyTemperatureObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            BodyTemperatureObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<BodyTemperatureLocationOfMeasurementChoice> LOCATION_OF_MEASUREMENT =
            new AqlFieldImp<BodyTemperatureLocationOfMeasurementChoice>(
                    BodyTemperatureObservation.class,
                    "/protocol[at0020]/items[at0021]/value",
                    "locationOfMeasurement",
                    BodyTemperatureLocationOfMeasurementChoice.class,
                    this);

    public ListSelectAqlField<BodyTemperatureAnyEventChoice> ANY_EVENT =
            new ListAqlFieldImp<BodyTemperatureAnyEventChoice>(
                    BodyTemperatureObservation.class,
                    "/data[at0002]/events[at0003]",
                    "anyEvent",
                    BodyTemperatureAnyEventChoice.class,
                    this);

    private BodyTemperatureObservationContainment() {
        super("openEHR-EHR-OBSERVATION.body_temperature.v2");
    }

    public static BodyTemperatureObservationContainment getInstance() {
        return new BodyTemperatureObservationContainment();
    }
}
