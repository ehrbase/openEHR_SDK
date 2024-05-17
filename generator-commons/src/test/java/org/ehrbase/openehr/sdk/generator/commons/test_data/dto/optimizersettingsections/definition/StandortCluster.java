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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingsections.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.434032200+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class StandortCluster implements LocatableEntity {
    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort/Standortbeschreibung
     * Description: Das Feld enthält die Freitextbeschreibung des Standorts, z.B. Throax-, Herz- und Gefäßchirurgie.
     */
    @Path("/items[at0046]/value|value")
    private String standortbeschreibungValue;

    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort/Details
     * Description: Für die Erfassung weiterer Angaben über das Bett oder der Adresse. Verwenden Sie dazu den Archetyp CLUSTER.device oder CLUSTER.address.
     */
    @Path("/items[at0047]")
    private List<Cluster> details;

    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setStandortbeschreibungValue(String standortbeschreibungValue) {
        this.standortbeschreibungValue = standortbeschreibungValue;
    }

    public String getStandortbeschreibungValue() {
        return this.standortbeschreibungValue;
    }

    public void setDetails(List<Cluster> details) {
        this.details = details;
    }

    public List<Cluster> getDetails() {
        return this.details;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
