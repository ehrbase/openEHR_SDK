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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.stationarerversorgungsfallcomposition.definition;

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

public class AufnahmedatenAdminEntryContainment extends Containment {
    public SelectAqlField<AufnahmedatenAdminEntry> AUFNAHMEDATEN_ADMIN_ENTRY = new AqlFieldImp<AufnahmedatenAdminEntry>(
            AufnahmedatenAdminEntry.class, "", "AufnahmedatenAdminEntry", AufnahmedatenAdminEntry.class, this);

    public SelectAqlField<String> VERSORGUNGSFALLGRUND_VALUE = new AqlFieldImp<String>(
            AufnahmedatenAdminEntry.class,
            "/data[at0001]/items[at0013]/value|value",
            "versorgungsfallgrundValue",
            String.class,
            this);

    public SelectAqlField<String> ART_DER_AUFNAHME_VALUE = new AqlFieldImp<String>(
            AufnahmedatenAdminEntry.class,
            "/data[at0001]/items[at0049]/value|value",
            "artDerAufnahmeValue",
            String.class,
            this);

    public SelectAqlField<TemporalAccessor> DATUM_UHRZEIT_DER_AUFNAHME_VALUE = new AqlFieldImp<TemporalAccessor>(
            AufnahmedatenAdminEntry.class,
            "/data[at0001]/items[at0071]/value|value",
            "datumUhrzeitDerAufnahmeValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Cluster> ZUGEWIESENER_PATIENTENSTANDORT = new ListAqlFieldImp<Cluster>(
            AufnahmedatenAdminEntry.class,
            "/data[at0001]/items[at0131]",
            "zugewiesenerPatientenstandort",
            Cluster.class,
            this);

    public ListSelectAqlField<Cluster> VORHERIGER_PATIENTENSTANDORT = new ListAqlFieldImp<Cluster>(
            AufnahmedatenAdminEntry.class,
            "/data[at0001]/items[at0132]",
            "vorherigerPatientenstandort",
            Cluster.class,
            this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(AufnahmedatenAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(AufnahmedatenAdminEntry.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            AufnahmedatenAdminEntry.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private AufnahmedatenAdminEntryContainment() {
        super("openEHR-EHR-ADMIN_ENTRY.admission.v0");
    }

    public static AufnahmedatenAdminEntryContainment getInstance() {
        return new AufnahmedatenAdminEntryContainment();
    }
}
