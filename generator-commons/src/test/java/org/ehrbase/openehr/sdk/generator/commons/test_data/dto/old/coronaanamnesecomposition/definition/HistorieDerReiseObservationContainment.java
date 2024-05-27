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

public class HistorieDerReiseObservationContainment extends Containment {
    public SelectAqlField<HistorieDerReiseObservation> HISTORIE_DER_REISE_OBSERVATION =
            new AqlFieldImp<HistorieDerReiseObservation>(
                    HistorieDerReiseObservation.class,
                    "",
                    "HistorieDerReiseObservation",
                    HistorieDerReiseObservation.class,
                    this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(HistorieDerReiseObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<StandortCluster> STANDORT = new AqlFieldImp<StandortCluster>(
            HistorieDerReiseObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0134]/items[openEHR-EHR-CLUSTER.location.v1]",
            "standort",
            StandortCluster.class,
            this);

    public SelectAqlField<AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_>
            AUFENTHALT_IN_DEN_LETZTEN14_TAGE_IN_EINEM_DER_RISIKOGEBIETE_FUR_CORONAINFEKTION_ODER_KONTAKT_ZU_MENSCHEN_DIE_DORT_WAREN_DEFININGCODE =
                    new AqlFieldImp<AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_>(
                            HistorieDerReiseObservation.class,
                            "/data[at0001]/events[at0002]/data[at0003]/items[at0111]/value|defining_code",
                            "aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningcode",
                            AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_.class,
                            this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            HistorieDerReiseObservation.class,
            "/data[at0001]/events[at0002]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(
            HistorieDerReiseObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            HistorieDerReiseObservation.class,
            "/data[at0001]/origin|value",
            "originValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Cluster> EXTENSION_EN = new ListAqlFieldImp<Cluster>(
            HistorieDerReiseObservation.class, "/protocol[at0100]/items[at0101]", "extensionEn", Cluster.class, this);

    public ListSelectAqlField<Cluster> DETAILLIERTE_ANGABEN_ZUR_EXPOSITION = new ListAqlFieldImp<Cluster>(
            HistorieDerReiseObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0109]",
            "detaillierteAngabenZurExposition",
            Cluster.class,
            this);

    private HistorieDerReiseObservationContainment() {
        super("openEHR-EHR-OBSERVATION.travel_history.v0");
    }

    public static HistorieDerReiseObservationContainment getInstance() {
        return new HistorieDerReiseObservationContainment();
    }
}
