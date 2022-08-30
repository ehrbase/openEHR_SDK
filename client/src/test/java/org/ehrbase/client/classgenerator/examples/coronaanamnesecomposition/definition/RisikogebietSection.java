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
package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:12.569028100+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class RisikogebietSection implements LocatableEntity {
    /**
     * Path: Bericht/Risikogebiet/Historie der Reise
     * Description: Einzelheiten einer Reise im Hinblick auf die Exposition gegenüber potenziellen Risiken.
     */
    @Path("/items[openEHR-EHR-OBSERVATION.travel_history.v0]")
    private List<HistorieDerReiseObservation> historieDerReise;

    /**
     * Path: Bericht/Risikogebiet/Reisefall
     * Description: Details zur Reise während eines bestimmten Zeitraums.
     */
    @Path("/items[openEHR-EHR-OBSERVATION.travel_event.v0]")
    private List<ReisefallObservation> reisefall;

    /**
     * Path: Bericht/Risikogebiet/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setHistorieDerReise(List<HistorieDerReiseObservation> historieDerReise) {
        this.historieDerReise = historieDerReise;
    }

    public List<HistorieDerReiseObservation> getHistorieDerReise() {
        return this.historieDerReise;
    }

    public void setReisefall(List<ReisefallObservation> reisefall) {
        this.reisefall = reisefall;
    }

    public List<ReisefallObservation> getReisefall() {
        return this.reisefall;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
