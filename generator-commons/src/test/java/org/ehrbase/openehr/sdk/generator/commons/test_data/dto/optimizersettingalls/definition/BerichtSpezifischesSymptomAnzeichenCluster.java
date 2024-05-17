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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingalls.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.131030600+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BerichtSpezifischesSymptomAnzeichenCluster implements LocatableEntity {
    /**
     * Path: Bericht/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens.
     * Description: Name des Symptoms oder Anzeichens, das geprüft wird.
     */
    @Path("/items[at0004]/value|value")
    private String bezeichnungDesSymptomsOderAnzeichensValue;

    /**
     * Path: Bericht/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Vorhanden?
     * Description: Das Symptom oder Anzeichen ist vorhanden.
     */
    @Path("/items[at0005]/value|defining_code")
    private VorhandenDefiningCode2 vorhandenDefiningCode;

    /**
     * Path: Bericht/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen
     * Description: Zusätzliche strukturierte Informationen zu einem bestimmten Symptom oder Anzeichen.
     */
    @Path("/items[at0026]")
    private List<Cluster> detaillierteAngabenZumSymptomAnzeichen;

    /**
     * Path: Bericht/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Kommentar
     * Description: Zusätzliche Informationen über das spezifische Symptom oder Anzeichen, die nicht in anderen Bereichen dargestellt wurden.
     */
    @Path("/items[at0035]/value|value")
    private String kommentarValue;

    /**
     * Path: Bericht/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setBezeichnungDesSymptomsOderAnzeichensValue(String bezeichnungDesSymptomsOderAnzeichensValue) {
        this.bezeichnungDesSymptomsOderAnzeichensValue = bezeichnungDesSymptomsOderAnzeichensValue;
    }

    public String getBezeichnungDesSymptomsOderAnzeichensValue() {
        return this.bezeichnungDesSymptomsOderAnzeichensValue;
    }

    public void setVorhandenDefiningCode(VorhandenDefiningCode2 vorhandenDefiningCode) {
        this.vorhandenDefiningCode = vorhandenDefiningCode;
    }

    public VorhandenDefiningCode2 getVorhandenDefiningCode() {
        return this.vorhandenDefiningCode;
    }

    public void setDetaillierteAngabenZumSymptomAnzeichen(List<Cluster> detaillierteAngabenZumSymptomAnzeichen) {
        this.detaillierteAngabenZumSymptomAnzeichen = detaillierteAngabenZumSymptomAnzeichen;
    }

    public List<Cluster> getDetaillierteAngabenZumSymptomAnzeichen() {
        return this.detaillierteAngabenZumSymptomAnzeichen;
    }

    public void setKommentarValue(String kommentarValue) {
        this.kommentarValue = kommentarValue;
    }

    public String getKommentarValue() {
        return this.kommentarValue;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
