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
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.living_arrangement.v0")
public class WohnsituationEvaluation {
    @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.dwelling.v0]")
    private List<WohnstatteCluster> wohnstatte;

    @Path("/data[at0001]/items[at0003]/value|value")
    private String beschreibungValue;

    @Path("/data[at0001]/items[at0007]/value|magnitude")
    private Long anzahlDerHaushaltsmitgliederMagnitude;

    @Path("/protocol[at0002]/items[at0011]")
    private List<Cluster> erweiterung;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/language")
    private Language language;

    public void setWohnstatte(List<WohnstatteCluster> wohnstatte) {
        this.wohnstatte = wohnstatte;
    }

    public List<WohnstatteCluster> getWohnstatte() {
        return this.wohnstatte;
    }

    public void setBeschreibungValue(String beschreibungValue) {
        this.beschreibungValue = beschreibungValue;
    }

    public String getBeschreibungValue() {
        return this.beschreibungValue;
    }

    public void setAnzahlDerHaushaltsmitgliederMagnitude(Long anzahlDerHaushaltsmitgliederMagnitude) {
        this.anzahlDerHaushaltsmitgliederMagnitude = anzahlDerHaushaltsmitgliederMagnitude;
    }

    public Long getAnzahlDerHaushaltsmitgliederMagnitude() {
        return this.anzahlDerHaushaltsmitgliederMagnitude;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }
}
