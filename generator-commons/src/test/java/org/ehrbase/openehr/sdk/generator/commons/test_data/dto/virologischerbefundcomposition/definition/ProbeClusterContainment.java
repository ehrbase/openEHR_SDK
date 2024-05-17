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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class ProbeClusterContainment extends Containment {
    public SelectAqlField<ProbeCluster> PROBE_CLUSTER =
            new AqlFieldImp<ProbeCluster>(ProbeCluster.class, "", "ProbeCluster", ProbeCluster.class, this);

    public SelectAqlField<String> PROBENART_VALUE = new AqlFieldImp<String>(
            ProbeCluster.class, "/items[at0029]/value|value", "probenartValue", String.class, this);

    public SelectAqlField<DvIdentifier> LABORPROBENIDENTIFIKATOR = new AqlFieldImp<DvIdentifier>(
            ProbeCluster.class, "/items[at0001]/value", "laborprobenidentifikator", DvIdentifier.class, this);

    public SelectAqlField<DvIdentifier> EXTERNER_IDENTIFIKATOR = new AqlFieldImp<DvIdentifier>(
            ProbeCluster.class, "/items[at0088]/value", "externerIdentifikator", DvIdentifier.class, this);

    public SelectAqlField<TemporalAccessor> ZEITPUNKT_DES_PROBENEINGANGS_VALUE = new AqlFieldImp<TemporalAccessor>(
            ProbeCluster.class,
            "/items[at0034]/value|value",
            "zeitpunktDesProbeneingangsValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Cluster> PHYSISCHE_EIGENSCHAFTEN = new ListAqlFieldImp<Cluster>(
            ProbeCluster.class, "/items[at0027]", "physischeEigenschaften", Cluster.class, this);

    public SelectAqlField<String> KOMMENTAR_DES_PROBENNEHMERS_VALUE = new AqlFieldImp<String>(
            ProbeCluster.class, "/items[at0079]/value|value", "kommentarDesProbennehmersValue", String.class, this);

    public SelectAqlField<String> PROBENENTNAHMESTELLE_VALUE = new AqlFieldImp<String>(
            ProbeCluster.class, "/items[at0087]/value|value", "probenentnahmestelleValue", String.class, this);

    public SelectAqlField<AnatomischeLokalisationCluster> ANATOMISCHE_LOKALISATION =
            new AqlFieldImp<AnatomischeLokalisationCluster>(
                    ProbeCluster.class,
                    "/items[openEHR-EHR-CLUSTER.anatomical_location.v1]",
                    "anatomischeLokalisation",
                    AnatomischeLokalisationCluster.class,
                    this);

    public SelectAqlField<TemporalAccessor> ZEITPUNKT_DER_PROBENENTNAHME_VALUE = new AqlFieldImp<TemporalAccessor>(
            ProbeCluster.class,
            "/items[at0015]/value|value",
            "zeitpunktDerProbenentnahmeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<DvIdentifier> IDENTIFIKATOR_DES_PROBENNEHMERS = new AqlFieldImp<DvIdentifier>(
            ProbeCluster.class, "/items[at0070]/value", "identifikatorDesProbennehmers", DvIdentifier.class, this);

    public ListSelectAqlField<Cluster> ANGABEN_ZUM_PROBENNEHMER = new ListAqlFieldImp<Cluster>(
            ProbeCluster.class, "/items[at0071]", "angabenZumProbennehmer", Cluster.class, this);

    public ListSelectAqlField<Cluster> ZUSATZLICHE_ANGABEN_ZUR_PROBENNAHME = new ListAqlFieldImp<Cluster>(
            ProbeCluster.class, "/items[at0083]", "zusatzlicheAngabenZurProbennahme", Cluster.class, this);

    public ListSelectAqlField<Cluster> BEHALTER_DETAILS =
            new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0085]", "behalterDetails", Cluster.class, this);

    public ListSelectAqlField<Cluster> ANGABEN_ZUR_VERARBEITUNG = new ListAqlFieldImp<Cluster>(
            ProbeCluster.class, "/items[at0068]", "angabenZurVerarbeitung", Cluster.class, this);

    public ListSelectAqlField<Cluster> ANGABEN_ZUM_TRANSPORT = new ListAqlFieldImp<Cluster>(
            ProbeCluster.class, "/items[at0093]", "angabenZumTransport", Cluster.class, this);

    public ListSelectAqlField<Cluster> DIGITALE_DARSTELLUNG = new ListAqlFieldImp<Cluster>(
            ProbeCluster.class, "/items[at0096]", "digitaleDarstellung", Cluster.class, this);

    public SelectAqlField<DvIdentifier> IDENTIFIKATOR_DER_UBERGEORDNETEN_PROBE = new AqlFieldImp<DvIdentifier>(
            ProbeCluster.class,
            "/items[at0003]/value",
            "identifikatorDerUbergeordnetenProbe",
            DvIdentifier.class,
            this);

    public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(
            ProbeCluster.class, "/items[at0045]/value|value", "kommentarValue", String.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT =
            new AqlFieldImp<FeederAudit>(ProbeCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private ProbeClusterContainment() {
        super("openEHR-EHR-CLUSTER.specimen.v1");
    }

    public static ProbeClusterContainment getInstance() {
        return new ProbeClusterContainment();
    }
}
