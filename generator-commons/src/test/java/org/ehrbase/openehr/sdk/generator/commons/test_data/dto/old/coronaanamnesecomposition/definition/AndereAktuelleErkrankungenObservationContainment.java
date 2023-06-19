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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition.Language;

public class AndereAktuelleErkrankungenObservationContainment extends Containment {
    public SelectAqlField<AndereAktuelleErkrankungenObservation> ANDERE_AKTUELLE_ERKRANKUNGEN_OBSERVATION =
            new AqlFieldImp<AndereAktuelleErkrankungenObservation>(
                    AndereAktuelleErkrankungenObservation.class,
                    "",
                    "AndereAktuelleErkrankungenObservation",
                    AndereAktuelleErkrankungenObservation.class,
                    this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            AndereAktuelleErkrankungenObservation.class, "/language", "language", Language.class, this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            AndereAktuelleErkrankungenObservation.class,
            "/protocol[at0007]/items[at0021]",
            "erweiterung",
            Cluster.class,
            this);

    public SelectAqlField<String> BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE = new AqlFieldImp<String>(
            AndereAktuelleErkrankungenObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
            "bezeichnungDesSymptomsOderAnzeichensValue",
            String.class,
            this);

    public SelectAqlField<VorhandenDefiningcode> VORHANDEN_DEFININGCODE = new AqlFieldImp<VorhandenDefiningcode>(
            AndereAktuelleErkrankungenObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
            "vorhandenDefiningcode",
            VorhandenDefiningcode.class,
            this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            AndereAktuelleErkrankungenObservation.class,
            "/data[at0001]/events[at0002]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(
            AndereAktuelleErkrankungenObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            AndereAktuelleErkrankungenObservation.class,
            "/data[at0001]/origin|value",
            "originValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Cluster> ANZEICHEN = new ListAqlFieldImp<Cluster>(
            AndereAktuelleErkrankungenObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
            "anzeichen",
            Cluster.class,
            this);

    private AndereAktuelleErkrankungenObservationContainment() {
        super("openEHR-EHR-OBSERVATION.symptom_sign_screening.v0");
    }

    public static AndereAktuelleErkrankungenObservationContainment getInstance() {
        return new AndereAktuelleErkrankungenObservationContainment();
    }
}
