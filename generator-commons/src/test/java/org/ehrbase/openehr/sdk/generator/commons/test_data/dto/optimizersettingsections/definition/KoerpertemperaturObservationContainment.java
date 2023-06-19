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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingsections.definition;

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

public class KoerpertemperaturObservationContainment extends Containment {
    public SelectAqlField<KoerpertemperaturObservation> KOERPERTEMPERATUR_OBSERVATION =
            new AqlFieldImp<KoerpertemperaturObservation>(
                    KoerpertemperaturObservation.class,
                    "",
                    "KoerpertemperaturObservation",
                    KoerpertemperaturObservation.class,
                    this);

    public SelectAqlField<Double> TEMPERATUR_MAGNITUDE = new AqlFieldImp<Double>(
            KoerpertemperaturObservation.class,
            "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude",
            "temperaturMagnitude",
            Double.class,
            this);

    public SelectAqlField<String> TEMPERATUR_UNITS = new AqlFieldImp<String>(
            KoerpertemperaturObservation.class,
            "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units",
            "temperaturUnits",
            String.class,
            this);

    public ListSelectAqlField<KoerpertemperaturStoerfaktorenElement> STOERFAKTOREN =
            new ListAqlFieldImp<KoerpertemperaturStoerfaktorenElement>(
                    KoerpertemperaturObservation.class,
                    "/data[at0002]/events[at0003]/state[at0029]/items[at0066]",
                    "stoerfaktoren",
                    KoerpertemperaturStoerfaktorenElement.class,
                    this);

    public ListSelectAqlField<Cluster> UMGEBUNGSBEDINGUNGEN = new ListAqlFieldImp<Cluster>(
            KoerpertemperaturObservation.class,
            "/data[at0002]/events[at0003]/state[at0029]/items[at0056]",
            "umgebungsbedingungen",
            Cluster.class,
            this);

    public SelectAqlField<Cluster> BETAETIGUNG = new AqlFieldImp<Cluster>(
            KoerpertemperaturObservation.class,
            "/data[at0002]/events[at0003]/state[at0029]/items[at0057]",
            "betaetigung",
            Cluster.class,
            this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            KoerpertemperaturObservation.class,
            "/data[at0002]/events[at0003]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            KoerpertemperaturObservation.class,
            "/data[at0002]/origin|value",
            "originValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Cluster> STRUKTURIERTE_LOKALISATION_DER_MESSUNG = new ListAqlFieldImp<Cluster>(
            KoerpertemperaturObservation.class,
            "/protocol[at0020]/items[at0064]",
            "strukturierteLokalisationDerMessung",
            Cluster.class,
            this);

    public SelectAqlField<Cluster> GERAET = new AqlFieldImp<Cluster>(
            KoerpertemperaturObservation.class, "/protocol[at0020]/items[at0059]", "geraet", Cluster.class, this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            KoerpertemperaturObservation.class, "/protocol[at0020]/items[at0062]", "erweiterung", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(
            KoerpertemperaturObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            KoerpertemperaturObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            KoerpertemperaturObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private KoerpertemperaturObservationContainment() {
        super("openEHR-EHR-OBSERVATION.body_temperature.v2");
    }

    public static KoerpertemperaturObservationContainment getInstance() {
        return new KoerpertemperaturObservationContainment();
    }
}
