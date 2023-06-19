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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:57:29.118797400+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class EventsummaryBeteiligtePersonenCluster implements LocatableEntity {
    /**
     * Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/Art der Person
     * Description: *
     */
    @Path("/items[at0011]/value|value")
    private String artDerPersonValue;

    /**
     * Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/ID der Person
     * Description: *
     */
    @Path("/items[at0010]")
    private List<EventsummaryIdDerPersonElement> idDerPerson;

    /**
     * Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setArtDerPersonValue(String artDerPersonValue) {
        this.artDerPersonValue = artDerPersonValue;
    }

    public String getArtDerPersonValue() {
        return this.artDerPersonValue;
    }

    public void setIdDerPerson(List<EventsummaryIdDerPersonElement> idDerPerson) {
        this.idDerPerson = idDerPerson;
    }

    public List<EventsummaryIdDerPersonElement> getIdDerPerson() {
        return this.idDerPerson;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
