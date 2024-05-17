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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;

@Entity
public class ReisefallBestimmtesReisezielCluster {
    @Path("/items[at0031]/value|value")
    private String bestimmteGegendValue;

    @Path("/items[at0024]")
    private List<Cluster> zusatzlicheAngabenZumZielort;

    @Path("/items[at0012]/value|value")
    private String bundeslandRegionValue;

    @Path("/items[at0011]/value|value")
    private String landValue;

    @Path("/items[at0013]/value|value")
    private String stadtValue;

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

    public void setBundeslandRegionValue(String bundeslandRegionValue) {
        this.bundeslandRegionValue = bundeslandRegionValue;
    }

    public String getBundeslandRegionValue() {
        return this.bundeslandRegionValue;
    }

    public void setLandValue(String landValue) {
        this.landValue = landValue;
    }

    public String getLandValue() {
        return this.landValue;
    }

    public void setStadtValue(String stadtValue) {
        this.stadtValue = stadtValue;
    }

    public String getStadtValue() {
        return this.stadtValue;
    }
}
