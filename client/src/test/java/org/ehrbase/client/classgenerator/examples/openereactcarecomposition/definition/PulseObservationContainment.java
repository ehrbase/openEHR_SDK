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
package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class PulseObservationContainment extends Containment {
    public SelectAqlField<PulseObservation> PULSE_OBSERVATION = new AqlFieldImp<PulseObservation>(
            PulseObservation.class, "", "PulseObservation", PulseObservation.class, this);

    public SelectAqlField<Double> PULSE_RATE_MAGNITUDE = new AqlFieldImp<Double>(
            PulseObservation.class,
            "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude",
            "pulseRateMagnitude",
            Double.class,
            this);

    public SelectAqlField<String> PULSE_RATE_UNITS = new AqlFieldImp<String>(
            PulseObservation.class,
            "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units",
            "pulseRateUnits",
            String.class,
            this);

    public ListSelectAqlField<Cluster> EXERTION = new ListAqlFieldImp<Cluster>(
            PulseObservation.class,
            "/data[at0002]/events[at0003]/state[at0012]/items[at1017]",
            "exertion",
            Cluster.class,
            this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            PulseObservation.class,
            "/data[at0002]/events[at0003]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            PulseObservation.class, "/data[at0002]/origin|value", "originValue", TemporalAccessor.class, this);

    public SelectAqlField<Cluster> DEVICE = new AqlFieldImp<Cluster>(
            PulseObservation.class, "/protocol[at0010]/items[at1013]", "device", Cluster.class, this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            PulseObservation.class, "/protocol[at0010]/items[at1056]", "extension", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(PulseObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(PulseObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            PulseObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private PulseObservationContainment() {
        super("openEHR-EHR-OBSERVATION.pulse.v1");
    }

    public static PulseObservationContainment getInstance() {
        return new PulseObservationContainment();
    }
}
