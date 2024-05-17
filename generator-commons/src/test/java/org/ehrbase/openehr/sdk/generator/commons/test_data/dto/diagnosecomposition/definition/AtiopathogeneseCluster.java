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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.etiology.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:12.351025200+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AtiopathogeneseCluster implements LocatableEntity {
    /**
     * Path: COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/Ätiologie der Krankheit
     * Description: Identifizierung der Ursache der Krankheit oder des abnormalen Zustands.
     * Comment: Dies könnte eine andere Krankheit sein, ein ungesundes Verhalten, ein Gen oder eine andere Grundursache für die Krankheit, die der Patient hat.
     * Es ist möglich, dieses Elements für eine Krankheit, die mehrere Ursachen hat, zu wiederholen.
     * Wo dies möglich ist, ist die Kodierung dieses Elements über eine Terminologiedatenbank zu bevorzugen.
     * Beispiele für die Eingabe könnten sein: Alkoholismus (bei Leberzirrhose), Diabetes Mellitus (bei chronischer Nierenerkrankung), Atemwegsinfektion (bei Fieber) oder Rauchen (bei Lungenkrebs).
     */
    @Path("/items[at0001]")
    private List<AtiopathogeneseAtiologieDerKrankheitElement> atiologieDerKrankheit;

    /**
     * Path: COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/Beschreibung des Entstehens
     * Description: Weitere Beschreibung der Ursachen oder Gründe für das Entstehen (Ätiologie) eines spezifischen Problems/einer Diagnose.
     * Comment: z.B. Lokale Entzündungsreaktion mit Schädigung und Zerstörung der Leberzellen.
     */
    @Path("/items[at0017]")
    private List<AtiopathogeneseBeschreibungDesEntstehensElement> beschreibungDesEntstehens;

    /**
     * Path: COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setAtiologieDerKrankheit(List<AtiopathogeneseAtiologieDerKrankheitElement> atiologieDerKrankheit) {
        this.atiologieDerKrankheit = atiologieDerKrankheit;
    }

    public List<AtiopathogeneseAtiologieDerKrankheitElement> getAtiologieDerKrankheit() {
        return this.atiologieDerKrankheit;
    }

    public void setBeschreibungDesEntstehens(
            List<AtiopathogeneseBeschreibungDesEntstehensElement> beschreibungDesEntstehens) {
        this.beschreibungDesEntstehens = beschreibungDesEntstehens;
    }

    public List<AtiopathogeneseBeschreibungDesEntstehensElement> getBeschreibungDesEntstehens() {
        return this.beschreibungDesEntstehens;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
