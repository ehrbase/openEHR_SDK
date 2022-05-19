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
package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition.GeccoSerologischerBefundKategorieElement;
import org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition.GeccoSerologischerBefundKategorieLoincElement;
import org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition.StatusDefiningCode;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

public class GECCOSerologischerBefundCompositionContainment extends Containment {
    public SelectAqlField<GECCOSerologischerBefundComposition> G_E_C_C_O_SEROLOGISCHER_BEFUND_COMPOSITION =
            new AqlFieldImp<GECCOSerologischerBefundComposition>(
                    GECCOSerologischerBefundComposition.class,
                    "",
                    "GECCOSerologischerBefundComposition",
                    GECCOSerologischerBefundComposition.class,
                    this);

    public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(
            GECCOSerologischerBefundComposition.class,
            "/category|defining_code",
            "categoryDefiningCode",
            Category.class,
            this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            GECCOSerologischerBefundComposition.class,
            "/context/other_context[at0001]/items[at0002]",
            "erweiterung",
            Cluster.class,
            this);

    public SelectAqlField<StatusDefiningCode> STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode>(
            GECCOSerologischerBefundComposition.class,
            "/context/other_context[at0001]/items[at0004]/value|defining_code",
            "statusDefiningCode",
            StatusDefiningCode.class,
            this);

    public ListSelectAqlField<GeccoSerologischerBefundKategorieElement> KATEGORIE =
            new ListAqlFieldImp<GeccoSerologischerBefundKategorieElement>(
                    GECCOSerologischerBefundComposition.class,
                    "/context/other_context[at0001]/items[at0005]",
                    "kategorie",
                    GeccoSerologischerBefundKategorieElement.class,
                    this);

    public ListSelectAqlField<GeccoSerologischerBefundKategorieLoincElement> KATEGORIE_LOINC =
            new ListAqlFieldImp<GeccoSerologischerBefundKategorieLoincElement>(
                    GECCOSerologischerBefundComposition.class,
                    "/context/other_context[at0001]/items[at0005]",
                    "kategorieLoinc",
                    GeccoSerologischerBefundKategorieLoincElement.class,
                    this);

    public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            GECCOSerologischerBefundComposition.class,
            "/context/start_time|value",
            "startTimeValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(
            GECCOSerologischerBefundComposition.class,
            "/context/participations",
            "participations",
            Participation.class,
            this);

    public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            GECCOSerologischerBefundComposition.class,
            "/context/end_time|value",
            "endTimeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(
            GECCOSerologischerBefundComposition.class, "/context/location", "location", String.class, this);

    public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(
            GECCOSerologischerBefundComposition.class,
            "/context/health_care_facility",
            "healthCareFacility",
            PartyIdentified.class,
            this);

    public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(
            GECCOSerologischerBefundComposition.class,
            "/context/setting|defining_code",
            "settingDefiningCode",
            Setting.class,
            this);

    public ListSelectAqlField<BefundObservation> BEFUND = new ListAqlFieldImp<BefundObservation>(
            GECCOSerologischerBefundComposition.class,
            "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]",
            "befund",
            BefundObservation.class,
            this);

    public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(
            GECCOSerologischerBefundComposition.class, "/composer", "composer", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            GECCOSerologischerBefundComposition.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            GECCOSerologischerBefundComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(
            GECCOSerologischerBefundComposition.class, "/territory", "territory", Territory.class, this);

    private GECCOSerologischerBefundCompositionContainment() {
        super("openEHR-EHR-COMPOSITION.registereintrag.v1");
    }

    public static GECCOSerologischerBefundCompositionContainment getInstance() {
        return new GECCOSerologischerBefundCompositionContainment();
    }
}
