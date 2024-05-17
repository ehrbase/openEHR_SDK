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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingalls.definition;

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

public class DienstleistungInstructionContainment extends Containment {
    public SelectAqlField<DienstleistungInstruction> DIENSTLEISTUNG_INSTRUCTION = new AqlFieldImp<
            DienstleistungInstruction>(
            DienstleistungInstruction.class, "", "DienstleistungInstruction", DienstleistungInstruction.class, this);

    public ListSelectAqlField<DienstleistungAktuelleAktivitatActivity> AKTUELLE_AKTIVITAT =
            new ListAqlFieldImp<DienstleistungAktuelleAktivitatActivity>(
                    DienstleistungInstruction.class,
                    "/activities[at0001]",
                    "aktuelleAktivitat",
                    DienstleistungAktuelleAktivitatActivity.class,
                    this);

    public SelectAqlField<Cluster> EINSENDER = new AqlFieldImp<Cluster>(
            DienstleistungInstruction.class, "/protocol[at0008]/items[at0141]", "einsender", Cluster.class, this);

    public SelectAqlField<Cluster> EMPFANGER = new AqlFieldImp<Cluster>(
            DienstleistungInstruction.class, "/protocol[at0008]/items[at0142]", "empfanger", Cluster.class, this);

    public ListSelectAqlField<Cluster> VERTEILERLISTE = new ListAqlFieldImp<Cluster>(
            DienstleistungInstruction.class, "/protocol[at0008]/items[at0128]", "verteilerliste", Cluster.class, this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            DienstleistungInstruction.class, "/protocol[at0008]/items[at0112]", "erweiterung", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(DienstleistungInstruction.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<String> NARRATIVE_VALUE = new AqlFieldImp<String>(
            DienstleistungInstruction.class, "/narrative|value", "narrativeValue", String.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(DienstleistungInstruction.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            DienstleistungInstruction.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<TemporalAccessor> EXPIRY_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            DienstleistungInstruction.class, "/expiry_time|value", "expiryTimeValue", TemporalAccessor.class, this);

    private DienstleistungInstructionContainment() {
        super("openEHR-EHR-INSTRUCTION.service_request.v1");
    }

    public static DienstleistungInstructionContainment getInstance() {
        return new DienstleistungInstructionContainment();
    }
}
