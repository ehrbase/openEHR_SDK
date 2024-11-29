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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.dwelling.v0")
public class WohnstatteCluster {
    @Path("/items[at0028]/value|magnitude")
    private Long anzahlDerSchlafzimmerMagnitude;

    @Path("/items[at0003]")
    private List<Cluster> erganzendeDetails;

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
}
