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
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-05-19T16:20:30.017760700+02:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class GeccoSerologischerBefundKategorieLoincElement implements LocatableEntity {
    /**
     * Path: GECCO_Serologischer Befund/context/Kategorie (LOINC) Description: Die Klassifikation des
     * Registereintrags (z.B. Typ der Observation des FHIR-Profils).
     */
    @Path("/value|defining_code")
    private KategorieLoincDefiningCode value;

    /** Path: GECCO_Serologischer Befund/context/feeder_audit */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setValue(KategorieLoincDefiningCode value) {
        this.value = value;
    }

    public KategorieLoincDefiningCode getValue() {
        return this.value;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
