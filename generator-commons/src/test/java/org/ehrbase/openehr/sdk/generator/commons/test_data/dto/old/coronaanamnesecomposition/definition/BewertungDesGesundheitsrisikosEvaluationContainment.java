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

public class BewertungDesGesundheitsrisikosEvaluationContainment extends Containment {
    public SelectAqlField<BewertungDesGesundheitsrisikosEvaluation> BEWERTUNG_DES_GESUNDHEITSRISIKOS_EVALUATION =
            new AqlFieldImp<BewertungDesGesundheitsrisikosEvaluation>(
                    BewertungDesGesundheitsrisikosEvaluation.class,
                    "",
                    "BewertungDesGesundheitsrisikosEvaluation",
                    BewertungDesGesundheitsrisikosEvaluation.class,
                    this);

    public SelectAqlField<TemporalAccessor> LETZTE_AKTUALISIERUNG_VALUE = new AqlFieldImp<TemporalAccessor>(
            BewertungDesGesundheitsrisikosEvaluation.class,
            "/protocol[at0010]/items[at0024]/value|value",
            "letzteAktualisierungValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<BewertungDesGesundheitsrisikosRisikofaktorChoice> RISIKOFAKTOR =
            new AqlFieldImp<BewertungDesGesundheitsrisikosRisikofaktorChoice>(
                    BewertungDesGesundheitsrisikosEvaluation.class,
                    "/data[at0001]/items[at0016]/items[at0013]/value",
                    "risikofaktor",
                    BewertungDesGesundheitsrisikosRisikofaktorChoice.class,
                    this);

    public SelectAqlField<String> RISIKOBEWERTUNG_VALUE = new AqlFieldImp<String>(
            BewertungDesGesundheitsrisikosEvaluation.class,
            "/data[at0001]/items[at0003]/value|value",
            "risikobewertungValue",
            String.class,
            this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            BewertungDesGesundheitsrisikosEvaluation.class,
            "/protocol[at0010]/items[at0011]",
            "erweiterung",
            Cluster.class,
            this);

    public SelectAqlField<String> GESUNDHEITSRISIKO_VALUE = new AqlFieldImp<String>(
            BewertungDesGesundheitsrisikosEvaluation.class,
            "/data[at0001]/items[at0002]/value|value",
            "gesundheitsrisikoValue",
            String.class,
            this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            BewertungDesGesundheitsrisikosEvaluation.class, "/language", "language", Language.class, this);

    public SelectAqlField<BewertungDesGesundheitsrisikosVorhandenseinChoice> VORHANDENSEIN =
            new AqlFieldImp<BewertungDesGesundheitsrisikosVorhandenseinChoice>(
                    BewertungDesGesundheitsrisikosEvaluation.class,
                    "/data[at0001]/items[at0016]/items[at0017]/value",
                    "vorhandensein",
                    BewertungDesGesundheitsrisikosVorhandenseinChoice.class,
                    this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(
            BewertungDesGesundheitsrisikosEvaluation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<String> BEWERTUNGSMETHODE_VALUE = new AqlFieldImp<String>(
            BewertungDesGesundheitsrisikosEvaluation.class,
            "/protocol[at0010]/items[at0025]/value|value",
            "bewertungsmethodeValue",
            String.class,
            this);

    public SelectAqlField<BewertungDesGesundheitsrisikosDetailsChoice> DETAILS =
            new AqlFieldImp<BewertungDesGesundheitsrisikosDetailsChoice>(
                    BewertungDesGesundheitsrisikosEvaluation.class,
                    "/data[at0001]/items[at0016]/items[at0027]",
                    "details",
                    BewertungDesGesundheitsrisikosDetailsChoice.class,
                    this);

    private BewertungDesGesundheitsrisikosEvaluationContainment() {
        super("openEHR-EHR-EVALUATION.health_risk.v1");
    }

    public static BewertungDesGesundheitsrisikosEvaluationContainment getInstance() {
        return new BewertungDesGesundheitsrisikosEvaluationContainment();
    }
}
