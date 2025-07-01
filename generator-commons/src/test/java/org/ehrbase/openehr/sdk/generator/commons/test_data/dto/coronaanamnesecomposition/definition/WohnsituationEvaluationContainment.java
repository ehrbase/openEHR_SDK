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
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class WohnsituationEvaluationContainment extends Containment {
    public SelectAqlField<WohnsituationEvaluation> WOHNSITUATION_EVALUATION = new AqlFieldImp<WohnsituationEvaluation>(
            WohnsituationEvaluation.class, "", "WohnsituationEvaluation", WohnsituationEvaluation.class, this);

    public SelectAqlField<String> BESCHREIBUNG_VALUE = new AqlFieldImp<String>(
            WohnsituationEvaluation.class,
            "/data[at0001]/items[at0003]/value|value",
            "beschreibungValue",
            String.class,
            this);

    public SelectAqlField<Long> ANZAHL_DER_HAUSHALTSMITGLIEDER_MAGNITUDE = new AqlFieldImp<Long>(
            WohnsituationEvaluation.class,
            "/data[at0001]/items[at0007]/value|magnitude",
            "anzahlDerHaushaltsmitgliederMagnitude",
            Long.class,
            this);

    public ListSelectAqlField<WohnstatteCluster> WOHNSTATTE = new ListAqlFieldImp<WohnstatteCluster>(
            WohnsituationEvaluation.class,
            "/data[at0001]/items[openEHR-EHR-CLUSTER.dwelling.v0]",
            "wohnstatte",
            WohnstatteCluster.class,
            this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            WohnsituationEvaluation.class, "/protocol[at0002]/items[at0011]", "erweiterung", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(WohnsituationEvaluation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(WohnsituationEvaluation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            WohnsituationEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private WohnsituationEvaluationContainment() {
        super("openEHR-EHR-EVALUATION.living_arrangement.v0");
    }

    public static WohnsituationEvaluationContainment getInstance() {
        return new WohnsituationEvaluationContainment();
    }
}
