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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.test_all_types.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-10-08T15:38:06.373885500+02:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class TestAllTypesCluster implements LocatableEntity {
    /**
     * Path: Test all types/Test all types/section 2/section 3/Test all types/Test all types/cluster 5/cluster 6/boolean 2
     * Description: *
     */
    @Path("/items[at0001]/items[at0002]/items[at0003]/value|value")
    private Boolean boolean2Value;

    /**
     * Path: Test all types/Test all types/section 2/section 3/Test all types/Test all types/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setBoolean2Value(Boolean boolean2Value) {
        this.boolean2Value = boolean2Value;
    }

    public Boolean isBoolean2Value() {
        return this.boolean2Value;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
