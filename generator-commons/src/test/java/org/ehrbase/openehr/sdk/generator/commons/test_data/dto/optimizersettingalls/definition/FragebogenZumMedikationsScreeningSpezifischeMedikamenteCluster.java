/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingalls.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.099065600+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster implements LocatableEntity {
    /**
     * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Spezifische Medikamentenklasse/Spezifische Medikamente/Name des Medikaments
     * Description: Name des Medikaments oder der Medikamentenunterklasse.
     */
    @Path("/items[at0021]/value|value")
    private String nameDesMedikamentsValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Spezifische Medikamentenklasse/Spezifische Medikamente/Medikament in Verwendung?
     * Description: Wendet die Person das Medikament oder Unterklasse des Medikaments bei oder während des genannten Ereignisses an?
     */
    @Path("/items[at0024]/value|defining_code")
    private MedikamentInVerwendungDefiningCode medikamentInVerwendungDefiningCode;

    /**
     * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Spezifische Medikamentenklasse/Spezifische Medikamente/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setNameDesMedikamentsValue(String nameDesMedikamentsValue) {
        this.nameDesMedikamentsValue = nameDesMedikamentsValue;
    }

    public String getNameDesMedikamentsValue() {
        return this.nameDesMedikamentsValue;
    }

    public void setMedikamentInVerwendungDefiningCode(
            MedikamentInVerwendungDefiningCode medikamentInVerwendungDefiningCode) {
        this.medikamentInVerwendungDefiningCode = medikamentInVerwendungDefiningCode;
    }

    public MedikamentInVerwendungDefiningCode getMedikamentInVerwendungDefiningCode() {
        return this.medikamentInVerwendungDefiningCode;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
