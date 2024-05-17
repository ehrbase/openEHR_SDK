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
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Choice;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.health_risk.v1")
public class BewertungDesGesundheitsrisikosEvaluation {
    @Path("/protocol[at0010]/items[at0024]/value|value")
    private TemporalAccessor letzteAktualisierungValue;

    @Path("/data[at0001]/items[at0016]/items[at0013]/value")
    @Choice
    private BewertungDesGesundheitsrisikosRisikofaktorChoice risikofaktor;

    @Path("/data[at0001]/items[at0003]/value|value")
    private String risikobewertungValue;

    @Path("/protocol[at0010]/items[at0011]")
    private List<Cluster> erweiterung;

    @Path("/data[at0001]/items[at0002]/value|value")
    private String gesundheitsrisikoValue;

    @Path("/language")
    private Language language;

    @Path("/data[at0001]/items[at0016]/items[at0017]/value")
    @Choice
    private BewertungDesGesundheitsrisikosVorhandenseinChoice vorhandensein;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/protocol[at0010]/items[at0025]/value|value")
    private String bewertungsmethodeValue;

    @Path("/data[at0001]/items[at0016]/items[at0027]")
    @Choice
    private BewertungDesGesundheitsrisikosDetailsChoice details;

    public void setLetzteAktualisierungValue(TemporalAccessor letzteAktualisierungValue) {
        this.letzteAktualisierungValue = letzteAktualisierungValue;
    }

    public TemporalAccessor getLetzteAktualisierungValue() {
        return this.letzteAktualisierungValue;
    }

    public void setRisikofaktor(BewertungDesGesundheitsrisikosRisikofaktorChoice risikofaktor) {
        this.risikofaktor = risikofaktor;
    }

    public BewertungDesGesundheitsrisikosRisikofaktorChoice getRisikofaktor() {
        return this.risikofaktor;
    }

    public void setRisikobewertungValue(String risikobewertungValue) {
        this.risikobewertungValue = risikobewertungValue;
    }

    public String getRisikobewertungValue() {
        return this.risikobewertungValue;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
    }

    public void setGesundheitsrisikoValue(String gesundheitsrisikoValue) {
        this.gesundheitsrisikoValue = gesundheitsrisikoValue;
    }

    public String getGesundheitsrisikoValue() {
        return this.gesundheitsrisikoValue;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setVorhandensein(BewertungDesGesundheitsrisikosVorhandenseinChoice vorhandensein) {
        this.vorhandensein = vorhandensein;
    }

    public BewertungDesGesundheitsrisikosVorhandenseinChoice getVorhandensein() {
        return this.vorhandensein;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setBewertungsmethodeValue(String bewertungsmethodeValue) {
        this.bewertungsmethodeValue = bewertungsmethodeValue;
    }

    public String getBewertungsmethodeValue() {
        return this.bewertungsmethodeValue;
    }

    public void setDetails(BewertungDesGesundheitsrisikosDetailsChoice details) {
        this.details = details;
    }

    public BewertungDesGesundheitsrisikosDetailsChoice getDetails() {
        return this.details;
    }
}
