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

import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.RMEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:57:29.126799900+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_IDENTIFIER")
public class EventsummaryIdDerPersonDvIdentifier implements RMEntity, EventsummaryIdDerPersonChoice {
    /**
     * Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/ID der Person/ID der Person
     * Description: *
     */
    @Path("")
    private List<DvIdentifier> idDerPerson;

    public void setIdDerPerson(List<DvIdentifier> idDerPerson) {
        this.idDerPerson = idDerPerson;
    }

    public List<DvIdentifier> getIdDerPerson() {
        return this.idDerPerson;
    }
}
