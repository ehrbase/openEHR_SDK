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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingalls.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.EntryEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.travel_history.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.079029300+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class HistorieDerReiseObservation implements EntryEntity {
    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Aufenthalt in den letzten 14 Tage in einem der Risikogebiete für Coronainfektion oder Kontakt zu Menschen, die dort waren
     * Description: Ist der Patient in letzter Zeit gereist? Die Definition des Begriffs "kürzlich" kann je nach den Umständen der umfangreicheren Patientengeschichte und dem bekannten Infektionsrisiko variieren.
     */
    @Path(
            "/data[at0001]/events[at0002]/data[at0003]/items[at0111 and name/value='Aufenthalt in den letzten 14 Tage in einem der Risikogebiete für Coronainfektion oder Kontakt zu Menschen, die dort waren']/value|defining_code")
    private AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_
            aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode;

    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort/Standortbeschreibung
     * Description: Das Feld enthält die Freitextbeschreibung des Standorts, z.B. Throax-, Herz- und Gefäßchirurgie.
     */
    @Path(
            "/data[at0001]/events[at0002]/data[at0003]/items[at0134]/items[openEHR-EHR-CLUSTER.location.v1]/items[at0046]/value|value")
    private String standortbeschreibungValue;

    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort/Details
     * Description: Für die Erfassung weiterer Angaben über das Bett oder der Adresse. Verwenden Sie dazu den Archetyp CLUSTER.device oder CLUSTER.address.
     */
    @Path(
            "/data[at0001]/events[at0002]/data[at0003]/items[at0134]/items[openEHR-EHR-CLUSTER.location.v1]/items[at0047]")
    private List<Cluster> details;

    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Detaillierte Angaben zur Exposition
     * Description: *
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0109]")
    private List<Cluster> detaillierteAngabenZurExposition;

    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/time
     */
    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/origin
     */
    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/*Extension(en)
     * Description: *
     */
    @Path("/protocol[at0100]/items[at0101]")
    private List<Cluster> extensionEn;

    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Bericht/Risikogebiet/Historie der Reise/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void
            setAufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode(
                    AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_
                            aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode) {
        this
                .aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode = aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode;
    }

    public AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_
            getAufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode() {
        return this
                .aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode;
    }

    public void setStandortbeschreibungValue(String standortbeschreibungValue) {
        this.standortbeschreibungValue = standortbeschreibungValue;
    }

    public String getStandortbeschreibungValue() {
        return this.standortbeschreibungValue;
    }

    public void setDetails(List<Cluster> details) {
        this.details = details;
    }

    public List<Cluster> getDetails() {
        return this.details;
    }

    public void setDetaillierteAngabenZurExposition(List<Cluster> detaillierteAngabenZurExposition) {
        this.detaillierteAngabenZurExposition = detaillierteAngabenZurExposition;
    }

    public List<Cluster> getDetaillierteAngabenZurExposition() {
        return this.detaillierteAngabenZurExposition;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setExtensionEn(List<Cluster> extensionEn) {
        this.extensionEn = extensionEn;
    }

    public List<Cluster> getExtensionEn() {
        return this.extensionEn;
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

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
