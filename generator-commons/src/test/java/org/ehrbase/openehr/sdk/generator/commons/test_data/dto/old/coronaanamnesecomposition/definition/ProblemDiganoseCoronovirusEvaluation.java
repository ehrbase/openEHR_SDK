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
@Archetype("openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1")
public class ProblemDiganoseCoronovirusEvaluation {
    @Path("/protocol[at0032]/items[at0071]")
    private List<Cluster> erweiterung;

    @Path("/data[at0001]/items[at0069]/value|value")
    private String kommentarValue;

    @Path("/data[at0001]/items[at0002]/value|value")
    private String nameDesProblemsDerDiagnoseValue;

    @Path("/data[at0001]/items[at0039]")
    private List<Cluster> anatomischeStelleStrukturiert;

    @Path("/language")
    private Language language;

    @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v1 and name/value='Status']")
    private StatusCluster status;

    @Path("/protocol[at0032]/items[at0070]/value|value")
    private TemporalAccessor zuletztAktualisiertValue;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/items[at0043]")
    private List<Cluster> spezifischeAngaben;

    @Path("/data[at0001]/items[at0073]/value")
    @Choice
    private StatusDiagnostischeSicherheitChoice diagnostischeSicherheit;

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
    }

    public void setKommentarValue(String kommentarValue) {
        this.kommentarValue = kommentarValue;
    }

    public String getKommentarValue() {
        return this.kommentarValue;
    }

    public void setNameDesProblemsDerDiagnoseValue(String nameDesProblemsDerDiagnoseValue) {
        this.nameDesProblemsDerDiagnoseValue = nameDesProblemsDerDiagnoseValue;
    }

    public String getNameDesProblemsDerDiagnoseValue() {
        return this.nameDesProblemsDerDiagnoseValue;
    }

    public void setAnatomischeStelleStrukturiert(List<Cluster> anatomischeStelleStrukturiert) {
        this.anatomischeStelleStrukturiert = anatomischeStelleStrukturiert;
    }

    public List<Cluster> getAnatomischeStelleStrukturiert() {
        return this.anatomischeStelleStrukturiert;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setStatus(StatusCluster status) {
        this.status = status;
    }

    public StatusCluster getStatus() {
        return this.status;
    }

    public void setZuletztAktualisiertValue(TemporalAccessor zuletztAktualisiertValue) {
        this.zuletztAktualisiertValue = zuletztAktualisiertValue;
    }

    public TemporalAccessor getZuletztAktualisiertValue() {
        return this.zuletztAktualisiertValue;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setSpezifischeAngaben(List<Cluster> spezifischeAngaben) {
        this.spezifischeAngaben = spezifischeAngaben;
    }

    public List<Cluster> getSpezifischeAngaben() {
        return this.spezifischeAngaben;
    }

    public void setDiagnostischeSicherheit(StatusDiagnostischeSicherheitChoice diagnostischeSicherheit) {
        this.diagnostischeSicherheit = diagnostischeSicherheit;
    }

    public StatusDiagnostischeSicherheitChoice getDiagnostischeSicherheit() {
        return this.diagnostischeSicherheit;
    }
}
