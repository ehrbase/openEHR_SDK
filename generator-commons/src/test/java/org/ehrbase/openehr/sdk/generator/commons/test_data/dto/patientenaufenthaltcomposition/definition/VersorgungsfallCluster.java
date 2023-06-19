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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:12.891029400+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class VersorgungsfallCluster implements LocatableEntity {
    /**
     * Path: Patientenaufenthalt/context/Versorgungsfall/Zugehörige Versorgungsfall-Kennung
     * Description: Der Bezeichner/die Kennung dieses Falls.
     */
    @Path("/items[at0001 and name/value='Zugehörige Versorgungsfall-Kennung']/value|value")
    private String zugehorigeVersorgungsfallKennungValue;

    /**
     * Path: Patientenaufenthalt/context/Versorgungsfall/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setZugehorigeVersorgungsfallKennungValue(String zugehorigeVersorgungsfallKennungValue) {
        this.zugehorigeVersorgungsfallKennungValue = zugehorigeVersorgungsfallKennungValue;
    }

    public String getZugehorigeVersorgungsfallKennungValue() {
        return this.zugehorigeVersorgungsfallKennungValue;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
