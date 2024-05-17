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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address_cc.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:12.612025+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AdresseCluster implements LocatableEntity {
    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Adresse/Stadt
     * Description: Der Name der Stadt, des Ortes, des Dorfes oder einer anderen Gemeinde oder eines Lieferzentrums.
     */
    @Path("/items[at0012]/value|value")
    private String stadtValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Adresse/Land
     * Description: Land - eine Nation, wie allgemein verstanden oder allgemein akzeptiert.
     */
    @Path("/items[at0015]/value|value")
    private String landValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Adresse/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setStadtValue(String stadtValue) {
        this.stadtValue = stadtValue;
    }

    public String getStadtValue() {
        return this.stadtValue;
    }

    public void setLandValue(String landValue) {
        this.landValue = landValue;
    }

    public String getLandValue() {
        return this.landValue;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
