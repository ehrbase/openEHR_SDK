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
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.occupation_summary.v1")
public class GesundheitsbezogenerBerufEvaluation {
    @Path("/data[at0001]/items[at0004]/value|value")
    private String beschaftigungsstatusValue;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/language")
    private Language language;

    @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.occupation_record.v1]")
    private List<BeschaftigungCluster> beschaftigung;

    @Path("/data[at0001]/items[at0005]")
    private List<Cluster> zusatzlicheAngaben;

    @Path("/protocol[at0007]/items[at0008]")
    private List<Cluster> erweiterung;

    public void setBeschaftigungsstatusValue(String beschaftigungsstatusValue) {
        this.beschaftigungsstatusValue = beschaftigungsstatusValue;
    }

    public String getBeschaftigungsstatusValue() {
        return this.beschaftigungsstatusValue;
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

    public void setBeschaftigung(List<BeschaftigungCluster> beschaftigung) {
        this.beschaftigung = beschaftigung;
    }

    public List<BeschaftigungCluster> getBeschaftigung() {
        return this.beschaftigung;
    }

    public void setZusatzlicheAngaben(List<Cluster> zusatzlicheAngaben) {
        this.zusatzlicheAngaben = zusatzlicheAngaben;
    }

    public List<Cluster> getZusatzlicheAngaben() {
        return this.zusatzlicheAngaben;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
    }
}
