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
@Archetype("openEHR-EHR-INSTRUCTION.service_request.v1")
public class DienstleistungInstruction {
    @Path("/narrative|value")
    private String narrativeValue;

    @Path("/language")
    private Language language;

    @Path("/protocol[at0008]/items[at0142]")
    private Cluster empfanger;

    @Path("/protocol[at0008]/items[at0112]")
    private List<Cluster> erweiterung;

    @Path("/protocol[at0008]/items[at0141]")
    private Cluster einsender;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/activities[at0001]")
    private List<DienstleistungAktuelleAktivitatActivity> aktuelleAktivitat;

    @Path("/protocol[at0008]/items[at0128]")
    private List<Cluster> verteilerliste;

    public void setNarrativeValue(String narrativeValue) {
        this.narrativeValue = narrativeValue;
    }

    public String getNarrativeValue() {
        return this.narrativeValue;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setEmpfanger(Cluster empfanger) {
        this.empfanger = empfanger;
    }

    public Cluster getEmpfanger() {
        return this.empfanger;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
    }

    public void setEinsender(Cluster einsender) {
        this.einsender = einsender;
    }

    public Cluster getEinsender() {
        return this.einsender;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setAktuelleAktivitat(List<DienstleistungAktuelleAktivitatActivity> aktuelleAktivitat) {
        this.aktuelleAktivitat = aktuelleAktivitat;
    }

    public List<DienstleistungAktuelleAktivitatActivity> getAktuelleAktivitat() {
        return this.aktuelleAktivitat;
    }

    public void setVerteilerliste(List<Cluster> verteilerliste) {
        this.verteilerliste = verteilerliste;
    }

    public List<Cluster> getVerteilerliste() {
        return this.verteilerliste;
    }
}
