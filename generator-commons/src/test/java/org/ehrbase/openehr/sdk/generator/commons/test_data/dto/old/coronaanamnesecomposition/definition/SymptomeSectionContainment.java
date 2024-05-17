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

import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class SymptomeSectionContainment extends Containment {
    public SelectAqlField<SymptomeSection> SYMPTOME_SECTION =
            new AqlFieldImp<SymptomeSection>(SymptomeSection.class, "", "SymptomeSection", SymptomeSection.class, this);

    public SelectAqlField<WeitereSymptomeObservation> WEITERE_SYMPTOME = new AqlFieldImp<WeitereSymptomeObservation>(
            SymptomeSection.class,
            "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']",
            "weitereSymptome",
            WeitereSymptomeObservation.class,
            this);

    public SelectAqlField<FieberOderErhohteKorpertemperaturObservation> FIEBER_ODER_ERHOHTE_KORPERTEMPERATUR =
            new AqlFieldImp<FieberOderErhohteKorpertemperaturObservation>(
                    SymptomeSection.class,
                    "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']",
                    "fieberOderErhohteKorpertemperatur",
                    FieberOderErhohteKorpertemperaturObservation.class,
                    this);

    public SelectAqlField<GestorterGeschmackssinnObservation> GESTORTER_GESCHMACKSSINN =
            new AqlFieldImp<GestorterGeschmackssinnObservation>(
                    SymptomeSection.class,
                    "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']",
                    "gestorterGeschmackssinn",
                    GestorterGeschmackssinnObservation.class,
                    this);

    public SelectAqlField<KorpertemperaturObservation> KORPERTEMPERATUR = new AqlFieldImp<KorpertemperaturObservation>(
            SymptomeSection.class,
            "/items[openEHR-EHR-OBSERVATION.body_temperature.v2]",
            "korpertemperatur",
            KorpertemperaturObservation.class,
            this);

    public SelectAqlField<HeiserkeitObservation> HEISERKEIT = new AqlFieldImp<HeiserkeitObservation>(
            SymptomeSection.class,
            "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']",
            "heiserkeit",
            HeiserkeitObservation.class,
            this);

    public SelectAqlField<HustenObservation> HUSTEN = new AqlFieldImp<HustenObservation>(
            SymptomeSection.class,
            "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']",
            "husten",
            HustenObservation.class,
            this);

    public SelectAqlField<GestorterGeruchssinnObservation> GESTORTER_GERUCHSSINN =
            new AqlFieldImp<GestorterGeruchssinnObservation>(
                    SymptomeSection.class,
                    "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']",
                    "gestorterGeruchssinn",
                    GestorterGeruchssinnObservation.class,
                    this);

    public SelectAqlField<DurchfallObservation> DURCHFALL = new AqlFieldImp<DurchfallObservation>(
            SymptomeSection.class,
            "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']",
            "durchfall",
            DurchfallObservation.class,
            this);

    public SelectAqlField<SchnupfenObservation> SCHNUPFEN = new AqlFieldImp<SchnupfenObservation>(
            SymptomeSection.class,
            "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']",
            "schnupfen",
            SchnupfenObservation.class,
            this);

    private SymptomeSectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static SymptomeSectionContainment getInstance() {
        return new SymptomeSectionContainment();
    }
}
