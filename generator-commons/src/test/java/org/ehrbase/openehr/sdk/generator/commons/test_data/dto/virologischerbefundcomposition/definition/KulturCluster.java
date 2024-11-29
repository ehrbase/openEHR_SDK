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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.virologischerbefundcomposition.definition;

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
        date = "2020-12-10T13:06:13.769035+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class KulturCluster implements LocatableEntity {
    /**
     * Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus
     * Description: Ergebnis eines Labortests für einen bestimmten Analytwert.
     * Comment: Beispiele: 'Natrium', 'Leukozytenzahl', 'T3'. Üblicherweise über eine externe Terminologie codiert.
     */
    @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Virus']")
    private List<ProVirusCluster> proVirus;

    /**
     * Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setProVirus(List<ProVirusCluster> proVirus) {
        this.proVirus = proVirus;
    }

    public List<ProVirusCluster> getProVirus() {
        return this.proVirus;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
