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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:11.103497600+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class SauerstoffsattigungCluster implements LocatableEntity {
    /**
     * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/untersuchter Analyt
     * Description: Die Bezeichnung des Analyt-Resultats
     */
    @Path("/items[at0024]/value|defining_code")
    private UntersuchterAnalytDefiningCode4 untersuchterAnalytDefiningCode;

    /**
     * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/Analyt-Resultat
     * Description: (Mess-)Wert des Analyt-Resultats.
     */
    @Path("/items[at0001]/value|magnitude")
    private Double analytResultatMagnitude;

    /**
     * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/Analyt-Resultat
     * Description: (Mess-)Wert des Analyt-Resultats.
     */
    @Path("/items[at0001]/value|units")
    private String analytResultatUnits;

    /**
     * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/Analyseergebnis-Details
     * Description: Weitere Details zu einem einzelnen Ergebnis.
     */
    @Path("/items[at0014]")
    private List<Cluster> analyseergebnisDetails;

    /**
     * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/Ergebnis-Status
     * Description: Status des Analyseergebnisses.
     */
    @Path("/items[at0005]/value|value")
    private String ergebnisStatusValue;

    /**
     * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setUntersuchterAnalytDefiningCode(UntersuchterAnalytDefiningCode4 untersuchterAnalytDefiningCode) {
        this.untersuchterAnalytDefiningCode = untersuchterAnalytDefiningCode;
    }

    public UntersuchterAnalytDefiningCode4 getUntersuchterAnalytDefiningCode() {
        return this.untersuchterAnalytDefiningCode;
    }

    public void setAnalytResultatMagnitude(Double analytResultatMagnitude) {
        this.analytResultatMagnitude = analytResultatMagnitude;
    }

    public Double getAnalytResultatMagnitude() {
        return this.analytResultatMagnitude;
    }

    public void setAnalytResultatUnits(String analytResultatUnits) {
        this.analytResultatUnits = analytResultatUnits;
    }

    public String getAnalytResultatUnits() {
        return this.analytResultatUnits;
    }

    public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
        this.analyseergebnisDetails = analyseergebnisDetails;
    }

    public List<Cluster> getAnalyseergebnisDetails() {
        return this.analyseergebnisDetails;
    }

    public void setErgebnisStatusValue(String ergebnisStatusValue) {
        this.ergebnisStatusValue = ergebnisStatusValue;
    }

    public String getErgebnisStatusValue() {
        return this.ergebnisStatusValue;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
