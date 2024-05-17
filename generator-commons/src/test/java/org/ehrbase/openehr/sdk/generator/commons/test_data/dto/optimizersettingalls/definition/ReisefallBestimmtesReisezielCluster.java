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
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.088064500+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ReisefallBestimmtesReisezielCluster implements LocatableEntity {
    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel/Land
     * Description: Das besuchte Land.
     */
    @Path("/items[at0011]/value|value")
    private String landValue;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel/Bundesland / Region
     * Description: Die besuchte Region.
     */
    @Path("/items[at0012]/value|value")
    private String bundeslandRegionValue;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel/Stadt
     * Description: Die besuchte Stadt.
     */
    @Path("/items[at0013]/value|value")
    private String stadtValue;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel/Bestimmte Gegend
     * Description: Eine bestimmte Gegend, die besucht wurde.
     */
    @Path("/items[at0031]/value|value")
    private String bestimmteGegendValue;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel/Zusätzliche Angaben zum Zielort
     * Description: Zusätzliche strukturierte Details zur Reise nach, von und am Zielort.
     */
    @Path("/items[at0024]")
    private List<Cluster> zusatzlicheAngabenZumZielort;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setLandValue(String landValue) {
        this.landValue = landValue;
    }

    public String getLandValue() {
        return this.landValue;
    }

    public void setBundeslandRegionValue(String bundeslandRegionValue) {
        this.bundeslandRegionValue = bundeslandRegionValue;
    }

    public String getBundeslandRegionValue() {
        return this.bundeslandRegionValue;
    }

    public void setStadtValue(String stadtValue) {
        this.stadtValue = stadtValue;
    }

    public String getStadtValue() {
        return this.stadtValue;
    }

    public void setBestimmteGegendValue(String bestimmteGegendValue) {
        this.bestimmteGegendValue = bestimmteGegendValue;
    }

    public String getBestimmteGegendValue() {
        return this.bestimmteGegendValue;
    }

    public void setZusatzlicheAngabenZumZielort(List<Cluster> zusatzlicheAngabenZumZielort) {
        this.zusatzlicheAngabenZumZielort = zusatzlicheAngabenZumZielort;
    }

    public List<Cluster> getZusatzlicheAngabenZumZielort() {
        return this.zusatzlicheAngabenZumZielort;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
