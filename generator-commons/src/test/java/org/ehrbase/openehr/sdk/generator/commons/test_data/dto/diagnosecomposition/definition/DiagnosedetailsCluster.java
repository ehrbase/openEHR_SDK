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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.diagnose_details.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:12.349026300+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class DiagnosedetailsCluster implements LocatableEntity {
    /**
     * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Begründung von Ausnahmen
     * Description: Das Auftreten einer Diagnose muss in manchen Fällen zu Abrechnungszwecken begründet werden, z.B. für geschlechtsspezifische Plausibilitätsprüfungen.
     */
    @Path("/items[at0001]/value|value")
    private String begrundungVonAusnahmenValue;

    /**
     * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Diagnosetyp
     * Description: Art der Diagnose. Bei der Angabe des Diagnosetyps ist darauf zu achten, dass dieser auch im richtigen Kontext verwendet wird. Zum Beispiel wird es bei der Beschreibung einer Diagnose im Rahmen einer Überweisung nicht den Diagnosetyp "Entlassdiagnose" geben.
     */
    @Path("/items[at0002]/value|value")
    private String diagnosetypValue;

    /**
     * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Vorhanden bei Aufnahme
     * Description: Ist die Diagnose bei der Aufnahme in die Gesundheiteinrichtung vorhanden?
     */
    @Path("/items[at0016]/value|value")
    private Boolean vorhandenBeiAufnahmeValue;

    /**
     * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Vorhanden bei Entlassung
     * Description: Ist die Diagnose bei Entlassung aus der Gesundheiteinrichtung vorhanden?
     */
    @Path("/items[at0017]/value|value")
    private Boolean vorhandenBeiEntlassungValue;

    /**
     * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setBegrundungVonAusnahmenValue(String begrundungVonAusnahmenValue) {
        this.begrundungVonAusnahmenValue = begrundungVonAusnahmenValue;
    }

    public String getBegrundungVonAusnahmenValue() {
        return this.begrundungVonAusnahmenValue;
    }

    public void setDiagnosetypValue(String diagnosetypValue) {
        this.diagnosetypValue = diagnosetypValue;
    }

    public String getDiagnosetypValue() {
        return this.diagnosetypValue;
    }

    public void setVorhandenBeiAufnahmeValue(Boolean vorhandenBeiAufnahmeValue) {
        this.vorhandenBeiAufnahmeValue = vorhandenBeiAufnahmeValue;
    }

    public Boolean isVorhandenBeiAufnahmeValue() {
        return this.vorhandenBeiAufnahmeValue;
    }

    public void setVorhandenBeiEntlassungValue(Boolean vorhandenBeiEntlassungValue) {
        this.vorhandenBeiEntlassungValue = vorhandenBeiEntlassungValue;
    }

    public Boolean isVorhandenBeiEntlassungValue() {
        return this.vorhandenBeiEntlassungValue;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
