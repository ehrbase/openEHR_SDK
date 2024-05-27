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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_panel.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:58:09.941429500+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AntibiogrammCluster implements LocatableEntity {
    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat
     * Description: Ergebnis eines Labortests für einen bestimmten Analytwert.
     * Comment: Beispiele: 'Natrium', 'Leukozytenzahl', 'T3'. Üblicherweise über eine externe Terminologie codiert.
     */
    @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]")
    private List<LaboranalytResultatCluster> laboranalytResultat;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Antibiogramm/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setLaboranalytResultat(List<LaboranalytResultatCluster> laboranalytResultat) {
        this.laboranalytResultat = laboranalytResultat;
    }

    public List<LaboranalytResultatCluster> getLaboranalytResultat() {
        return this.laboranalytResultat;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
