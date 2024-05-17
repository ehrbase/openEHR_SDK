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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingsections.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.444037+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ReisefallBestimmteReiseCluster implements LocatableEntity {
    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel
     * Description: Angaben zu einem einzelnen Ort, der auf einer Reise besucht wurde.
     */
    @Path("/items[at0010]")
    private List<ReisefallBestimmtesReisezielCluster> bestimmtesReiseziel;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Zusätzliche Reisedetails
     * Description: Zusätzliche strukturierte Informationen zur gesamten Reise.
     */
    @Path("/items[at0025]")
    private List<Cluster> zusaetzlicheReisedetails;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Rückreisedatum
     * Description: Das Datum, an dem die Person zu ihrem Heimatstandort zurückkehrte.
     */
    @Path("/items[at0019]/value|value")
    private TemporalAccessor rueckreisedatumValue;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setBestimmtesReiseziel(List<ReisefallBestimmtesReisezielCluster> bestimmtesReiseziel) {
        this.bestimmtesReiseziel = bestimmtesReiseziel;
    }

    public List<ReisefallBestimmtesReisezielCluster> getBestimmtesReiseziel() {
        return this.bestimmtesReiseziel;
    }

    public void setZusaetzlicheReisedetails(List<Cluster> zusaetzlicheReisedetails) {
        this.zusaetzlicheReisedetails = zusaetzlicheReisedetails;
    }

    public List<Cluster> getZusaetzlicheReisedetails() {
        return this.zusaetzlicheReisedetails;
    }

    public void setRueckreisedatumValue(TemporalAccessor rueckreisedatumValue) {
        this.rueckreisedatumValue = rueckreisedatumValue;
    }

    public TemporalAccessor getRueckreisedatumValue() {
        return this.rueckreisedatumValue;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
