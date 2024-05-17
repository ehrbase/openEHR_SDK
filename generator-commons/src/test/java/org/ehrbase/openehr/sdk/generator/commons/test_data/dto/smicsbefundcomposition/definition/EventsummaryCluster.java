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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.eventsummary.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:57:29.110801400+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class EventsummaryCluster implements LocatableEntity {
    /**
     * Path: SmICS Befund/context/Eventsummary/Fallidentifikation
     * Description: *
     */
    @Path("/items[at0001 and name/value='Fallidentifikation']/value|value")
    private String fallidentifikationValue;

    /**
     * Path: SmICS Befund/context/Eventsummary/Fall-Art
     * Description: *
     */
    @Path("/items[at0002 and name/value='Fall-Art']/value|value")
    private String fallArtValue;

    /**
     * Path: SmICS Befund/context/Eventsummary/Beteiligte Personen
     * Description: *
     */
    @Path("/items[at0007]")
    private List<EventsummaryBeteiligtePersonenCluster> beteiligtePersonen;

    /**
     * Path: SmICS Befund/context/Eventsummary/Fall-Kategorie
     * Description: Eingruppierung des Kontaktes in Kategorien.
     */
    @Path("/items[at0004 and name/value='Fall-Kategorie']/value|value")
    private String fallKategorieValue;

    /**
     * Path: SmICS Befund/context/Eventsummary/Kommentar
     * Description: Zusätzliche Beschreibung der Aktivität, die in anderen Bereichen nicht erfasst wurden.
     */
    @Path("/items[at0006]/value|value")
    private String kommentarValue;

    /**
     * Path: SmICS Befund/context/Eventsummary/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setFallidentifikationValue(String fallidentifikationValue) {
        this.fallidentifikationValue = fallidentifikationValue;
    }

    public String getFallidentifikationValue() {
        return this.fallidentifikationValue;
    }

    public void setFallArtValue(String fallArtValue) {
        this.fallArtValue = fallArtValue;
    }

    public String getFallArtValue() {
        return this.fallArtValue;
    }

    public void setBeteiligtePersonen(List<EventsummaryBeteiligtePersonenCluster> beteiligtePersonen) {
        this.beteiligtePersonen = beteiligtePersonen;
    }

    public List<EventsummaryBeteiligtePersonenCluster> getBeteiligtePersonen() {
        return this.beteiligtePersonen;
    }

    public void setFallKategorieValue(String fallKategorieValue) {
        this.fallKategorieValue = fallKategorieValue;
    }

    public String getFallKategorieValue() {
        return this.fallKategorieValue;
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
