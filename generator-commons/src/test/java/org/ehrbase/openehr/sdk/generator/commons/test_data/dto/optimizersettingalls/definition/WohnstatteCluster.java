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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingalls.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.dwelling.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.110030600+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class WohnstatteCluster implements LocatableEntity {
    /**
     * Path: Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte/Anzahl der Schlafzimmer
     * Description: Die Anzahl der Schlafzimmer innerhalb der Wohnstätte.
     */
    @Path("/items[at0028]/value|magnitude")
    private Long anzahlDerSchlafzimmerMagnitude;

    /**
     * Path: Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte/Ergänzende Details
     * Description: Weitere strukturierte Details zur Wohnstätte.
     * Comment: Dieser SLOT kann verwendet werden, um weitere Archetypen zu verschachteln, die die Wohnstätte mit ergänzenden Details beschreiben und einem lokalen Zuständigkeitsbereich angehören können.
     */
    @Path("/items[at0003]")
    private List<Cluster> erganzendeDetails;

    /**
     * Path: Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setAnzahlDerSchlafzimmerMagnitude(Long anzahlDerSchlafzimmerMagnitude) {
        this.anzahlDerSchlafzimmerMagnitude = anzahlDerSchlafzimmerMagnitude;
    }

    public Long getAnzahlDerSchlafzimmerMagnitude() {
        return this.anzahlDerSchlafzimmerMagnitude;
    }

    public void setErganzendeDetails(List<Cluster> erganzendeDetails) {
        this.erganzendeDetails = erganzendeDetails;
    }

    public List<Cluster> getErganzendeDetails() {
        return this.erganzendeDetails;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
