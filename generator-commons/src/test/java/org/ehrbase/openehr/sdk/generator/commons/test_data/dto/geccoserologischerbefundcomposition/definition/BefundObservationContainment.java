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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.geccoserologischerbefundcomposition.definition;

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

public class BefundObservationContainment extends Containment {
    public SelectAqlField<BefundObservation> BEFUND_OBSERVATION = new AqlFieldImp<BefundObservation>(
            BefundObservation.class, "", "BefundObservation", BefundObservation.class, this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            BefundObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

    public SelectAqlField<Cluster> LABOR_WELCHES_DEN_UNTERSUCHUNGSAUFTRAG_ANNIMMT = new AqlFieldImp<Cluster>(
            BefundObservation.class,
            "/protocol[at0004]/items[at0017]",
            "laborWelchesDenUntersuchungsauftragAnnimmt",
            Cluster.class,
            this);

    public SelectAqlField<AnforderungDefiningCode> ANFORDERUNG_DEFINING_CODE = new AqlFieldImp<AnforderungDefiningCode>(
            BefundObservation.class,
            "/protocol[at0004]/items[at0094]/items[at0106]/value|defining_code",
            "anforderungDefiningCode",
            AnforderungDefiningCode.class,
            this);

    public SelectAqlField<Cluster> EINSENDER = new AqlFieldImp<Cluster>(
            BefundObservation.class, "/protocol[at0004]/items[at0094]/items[at0090]", "einsender", Cluster.class, this);

    public ListSelectAqlField<Cluster> VERTEILERLISTE = new ListAqlFieldImp<Cluster>(
            BefundObservation.class,
            "/protocol[at0004]/items[at0094]/items[at0035]",
            "verteilerliste",
            Cluster.class,
            this);

    public ListSelectAqlField<Cluster> TEST_DETAILS = new ListAqlFieldImp<Cluster>(
            BefundObservation.class, "/protocol[at0004]/items[at0110]", "testDetails", Cluster.class, this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            BefundObservation.class, "/protocol[at0004]/items[at0117]", "erweiterung", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(BefundObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(BefundObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            BefundObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public ListSelectAqlField<BefundJedesEreignisChoice> JEDES_EREIGNIS =
            new ListAqlFieldImp<BefundJedesEreignisChoice>(
                    BefundObservation.class,
                    "/data[at0001]/events[at0002]",
                    "jedesEreignis",
                    BefundJedesEreignisChoice.class,
                    this);

    private BefundObservationContainment() {
        super("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
    }

    public static BefundObservationContainment getInstance() {
        return new BefundObservationContainment();
    }
}
