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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.diagnosecomposition.definition;

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
        date = "2020-12-10T13:06:12.339026+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class FallidentifikationCluster implements LocatableEntity {
    /**
     * Path: COVID-19-Diagnose/context/Fallidentifikation/Fall-Kennung
     * Description: Der Bezeichner/die Kennung dieses Falls.
     */
    @Path("/items[at0001]/value|value")
    private String fallKennungValue;

    /**
     * Path: COVID-19-Diagnose/context/Fallidentifikation/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setFallKennungValue(String fallKennungValue) {
        this.fallKennungValue = fallKennungValue;
    }

    public String getFallKennungValue() {
        return this.fallKennungValue;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
