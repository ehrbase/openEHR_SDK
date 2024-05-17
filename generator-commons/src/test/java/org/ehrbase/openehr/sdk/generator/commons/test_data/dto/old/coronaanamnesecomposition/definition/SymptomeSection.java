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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class SymptomeSection {
    @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']")
    private WeitereSymptomeObservation weitereSymptome;

    @Path(
            "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']")
    private FieberOderErhohteKorpertemperaturObservation fieberOderErhohteKorpertemperatur;

    @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']")
    private GestorterGeschmackssinnObservation gestorterGeschmackssinn;

    @Path("/items[openEHR-EHR-OBSERVATION.body_temperature.v2]")
    private KorpertemperaturObservation korpertemperatur;

    @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']")
    private HeiserkeitObservation heiserkeit;

    @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']")
    private HustenObservation husten;

    @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']")
    private GestorterGeruchssinnObservation gestorterGeruchssinn;

    @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']")
    private DurchfallObservation durchfall;

    @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']")
    private SchnupfenObservation schnupfen;

    public void setWeitereSymptome(WeitereSymptomeObservation weitereSymptome) {
        this.weitereSymptome = weitereSymptome;
    }

    public WeitereSymptomeObservation getWeitereSymptome() {
        return this.weitereSymptome;
    }

    public void setFieberOderErhohteKorpertemperatur(
            FieberOderErhohteKorpertemperaturObservation fieberOderErhohteKorpertemperatur) {
        this.fieberOderErhohteKorpertemperatur = fieberOderErhohteKorpertemperatur;
    }

    public FieberOderErhohteKorpertemperaturObservation getFieberOderErhohteKorpertemperatur() {
        return this.fieberOderErhohteKorpertemperatur;
    }

    public void setGestorterGeschmackssinn(GestorterGeschmackssinnObservation gestorterGeschmackssinn) {
        this.gestorterGeschmackssinn = gestorterGeschmackssinn;
    }

    public GestorterGeschmackssinnObservation getGestorterGeschmackssinn() {
        return this.gestorterGeschmackssinn;
    }

    public void setKorpertemperatur(KorpertemperaturObservation korpertemperatur) {
        this.korpertemperatur = korpertemperatur;
    }

    public KorpertemperaturObservation getKorpertemperatur() {
        return this.korpertemperatur;
    }

    public void setHeiserkeit(HeiserkeitObservation heiserkeit) {
        this.heiserkeit = heiserkeit;
    }

    public HeiserkeitObservation getHeiserkeit() {
        return this.heiserkeit;
    }

    public void setHusten(HustenObservation husten) {
        this.husten = husten;
    }

    public HustenObservation getHusten() {
        return this.husten;
    }

    public void setGestorterGeruchssinn(GestorterGeruchssinnObservation gestorterGeruchssinn) {
        this.gestorterGeruchssinn = gestorterGeruchssinn;
    }

    public GestorterGeruchssinnObservation getGestorterGeruchssinn() {
        return this.gestorterGeruchssinn;
    }

    public void setDurchfall(DurchfallObservation durchfall) {
        this.durchfall = durchfall;
    }

    public DurchfallObservation getDurchfall() {
        return this.durchfall;
    }

    public void setSchnupfen(SchnupfenObservation schnupfen) {
        this.schnupfen = schnupfen;
    }

    public SchnupfenObservation getSchnupfen() {
        return this.schnupfen;
    }
}
