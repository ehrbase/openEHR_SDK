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
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.travel_history.v0")
public class HistorieDerReiseObservation {
    @Path("/language")
    private Language language;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0134]/items[openEHR-EHR-CLUSTER.location.v1]")
    private StandortCluster standort;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0111]/value|defining_code")
    private AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_
            aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningcode;

    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    @Path("/protocol[at0100]/items[at0101]")
    private List<Cluster> extensionEn;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0109]")
    private List<Cluster> detaillierteAngabenZurExposition;

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setStandort(StandortCluster standort) {
        this.standort = standort;
    }

    public StandortCluster getStandort() {
        return this.standort;
    }

    public void
            setAufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningcode(
                    AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_
                            aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningcode) {
        this
                .aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningcode = aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningcode;
    }

    public AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_
            getAufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningcode() {
        return this
                .aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningcode;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setExtensionEn(List<Cluster> extensionEn) {
        this.extensionEn = extensionEn;
    }

    public List<Cluster> getExtensionEn() {
        return this.extensionEn;
    }

    public void setDetaillierteAngabenZurExposition(List<Cluster> detaillierteAngabenZurExposition) {
        this.detaillierteAngabenZurExposition = detaillierteAngabenZurExposition;
    }

    public List<Cluster> getDetaillierteAngabenZurExposition() {
        return this.detaillierteAngabenZurExposition;
    }
}
