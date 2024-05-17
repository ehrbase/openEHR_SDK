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
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.PointEventEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.365035100+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("POINT_EVENT")
public class GeschichteHistorieBeliebigesEreignisPointEvent
        implements PointEventEntity, GeschichteHistorieBeliebigesEreignisChoice {
    /**
     * Path: Bericht/Geschichte/Historie/Beliebiges Ereignis/Geschichte
     * Description: Beschreibung der Geschichte oder der klinischen Vorgeschichte für das Fachgebiet der Pflege.
     */
    @Path("/data[at0003]/items[at0004]")
    private List<GeschichteHistorieGeschichteElement> geschichte;

    /**
     * Path: Bericht/Geschichte/Historie/Beliebiges Ereignis/Strukturierte Angabe
     * Description: Strukturierte Angaben über die Geschichte der Person oder des Patienten.
     * Comment: Zum Beispiel: ein spezifisches Symptom wie Übelkeit oder Schmerzen; ein Ereignis wie ein Sturz vom Fahrrad; oder ein Anliegen wie der Wunsch, mit dem Tabakkonsum aufzuhören.
     */
    @Path("/data[at0003]/items[at0006]")
    private List<Cluster> strukturierteAngabe;

    /**
     * Path: Bericht/Geschichte/Historie/Beliebiges Ereignis/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Bericht/Geschichte/Historie/Beliebiges Ereignis/time
     */
    @Path("/time|value")
    private TemporalAccessor timeValue;

    public void setGeschichte(List<GeschichteHistorieGeschichteElement> geschichte) {
        this.geschichte = geschichte;
    }

    public List<GeschichteHistorieGeschichteElement> getGeschichte() {
        return this.geschichte;
    }

    public void setStrukturierteAngabe(List<Cluster> strukturierteAngabe) {
        this.strukturierteAngabe = strukturierteAngabe;
    }

    public List<Cluster> getStrukturierteAngabe() {
        return this.strukturierteAngabe;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }
}
