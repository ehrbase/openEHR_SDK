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
package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:58:09.984434300+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ErregerdetailsResistenzmechanismusCluster implements LocatableEntity {
    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Resistenzmechanismus/Resistenzmechanismus Bezeichnung
     * Description: Bezeichnung des Resistenzmechanismus.
     * Comment: Ein bestes Beispiel wäre, die Untersuchung eines „Staphylococcus aureus“ der hier die Bezeichnung des Keims ist.
     */
    @Path("/items[at0017]/value|value")
    private String resistenzmechanismusBezeichnungValue;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Resistenzmechanismus/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setResistenzmechanismusBezeichnungValue(String resistenzmechanismusBezeichnungValue) {
        this.resistenzmechanismusBezeichnungValue = resistenzmechanismusBezeichnungValue;
    }

    public String getResistenzmechanismusBezeichnungValue() {
        return this.resistenzmechanismusBezeichnungValue;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
