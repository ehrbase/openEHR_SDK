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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class FragebogenZumMedikationsScreeningObservationContainment extends Containment {
    public SelectAqlField<FragebogenZumMedikationsScreeningObservation>
            FRAGEBOGEN_ZUM_MEDIKATIONS_SCREENING_OBSERVATION =
                    new AqlFieldImp<FragebogenZumMedikationsScreeningObservation>(
                            FragebogenZumMedikationsScreeningObservation.class,
                            "",
                            "FragebogenZumMedikationsScreeningObservation",
                            FragebogenZumMedikationsScreeningObservation.class,
                            this);

    public SelectAqlField<DvCodedText> MEDIKAMENTE_IN_VERWENDUNG = new AqlFieldImp<DvCodedText>(
            FragebogenZumMedikationsScreeningObservation.class,
            "/data[at0022]/events[at0023]/data[at0001]/items[at0027]/value",
            "medikamenteInVerwendung",
            DvCodedText.class,
            this);

    public SelectAqlField<String> NAME_DER_MEDIKAMENTENKLASSE_VALUE = new AqlFieldImp<String>(
            FragebogenZumMedikationsScreeningObservation.class,
            "/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0002]/value|value",
            "nameDerMedikamentenklasseValue",
            String.class,
            this);

    public SelectAqlField<MedikamentenklasseInVerwendungDefiningCode> MEDIKAMENTENKLASSE_IN_VERWENDUNG_DEFINING_CODE =
            new AqlFieldImp<MedikamentenklasseInVerwendungDefiningCode>(
                    FragebogenZumMedikationsScreeningObservation.class,
                    "/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0003]/value|defining_code",
                    "medikamentenklasseInVerwendungDefiningCode",
                    MedikamentenklasseInVerwendungDefiningCode.class,
                    this);

    public ListSelectAqlField<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster> SPEZIFISCHE_MEDIKAMENTE =
            new ListAqlFieldImp<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster>(
                    FragebogenZumMedikationsScreeningObservation.class,
                    "/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0008]",
                    "spezifischeMedikamente",
                    FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster.class,
                    this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            FragebogenZumMedikationsScreeningObservation.class,
            "/data[at0022]/events[at0023]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            FragebogenZumMedikationsScreeningObservation.class,
            "/data[at0022]/origin|value",
            "originValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            FragebogenZumMedikationsScreeningObservation.class,
            "/protocol[at0005]/items[at0019]",
            "erweiterung",
            Cluster.class,
            this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(
            FragebogenZumMedikationsScreeningObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            FragebogenZumMedikationsScreeningObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            FragebogenZumMedikationsScreeningObservation.class,
            "/feeder_audit",
            "feederAudit",
            FeederAudit.class,
            this);

    private FragebogenZumMedikationsScreeningObservationContainment() {
        super("openEHR-EHR-OBSERVATION.medication_use.v0");
    }

    public static FragebogenZumMedikationsScreeningObservationContainment getInstance() {
        return new FragebogenZumMedikationsScreeningObservationContainment();
    }
}
