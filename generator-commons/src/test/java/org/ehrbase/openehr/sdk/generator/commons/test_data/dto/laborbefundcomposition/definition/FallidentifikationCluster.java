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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-06-10T14:23:37.469641+07:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0")
public class FallidentifikationCluster implements LocatableEntity {
    /**
     * Path: Laborbefund/context/Fallidentifikation/Fall-Kennung
     * Description: Der Bezeichner/die Kennung dieses Falls.
     */
    @Path("/items[at0001]/value|value")
    private String fallKennungValue;

    /**
     * Path: Laborbefund/context/Baum/Fallidentifikation/Fall-Kennung/null_flavour
     */
    @Path("/items[at0001]/null_flavour|defining_code")
    private NullFlavour fallKennungNullFlavourDefiningCode;

    /**
     * Path: Laborbefund/context/Fallidentifikation/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setFallKennungValue(String fallKennungValue) {
        this.fallKennungValue = fallKennungValue;
    }

    public String getFallKennungValue() {
        return this.fallKennungValue;
    }

    public void setFallKennungNullFlavourDefiningCode(NullFlavour fallKennungNullFlavourDefiningCode) {
        this.fallKennungNullFlavourDefiningCode = fallKennungNullFlavourDefiningCode;
    }

    public NullFlavour getFallKennungNullFlavourDefiningCode() {
        return this.fallKennungNullFlavourDefiningCode;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
