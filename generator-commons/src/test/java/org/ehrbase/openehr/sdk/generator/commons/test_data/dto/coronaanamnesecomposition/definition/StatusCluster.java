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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.problem_qualifier.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:12.624027600+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class StatusCluster implements LocatableEntity {
    /**
     * Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Status/Diagnosestatus
     * Description: Stadium oder Phase des diagnostischen Prozesses.
     */
    @Path("/items[at0004]/value|defining_code")
    private DiagnosestatusDefiningCode diagnosestatusDefiningCode;

    /**
     * Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Status/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setDiagnosestatusDefiningCode(DiagnosestatusDefiningCode diagnosestatusDefiningCode) {
        this.diagnosestatusDefiningCode = diagnosestatusDefiningCode;
    }

    public DiagnosestatusDefiningCode getDiagnosestatusDefiningCode() {
        return this.diagnosestatusDefiningCode;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
