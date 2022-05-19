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
package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.133030700+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("POINT_EVENT")
public class BerichtBeliebigesEreignisPointEvent implements PointEventEntity, BerichtBeliebigesEreignisChoice {
    /**
     * Path: Bericht/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen
     * Description: Gruppierung von Datenelementen bezogen auf Screening auf ein einzelnes Symptom oder Anzeichen.
     */
    @Path("/data[at0003]/items[at0022]")
    private List<BerichtSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen;

    /**
     * Path: Bericht/Beliebiges Ereignis/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Bericht/Beliebiges Ereignis/time
     */
    @Path("/time|value")
    private TemporalAccessor timeValue;

    public void setSpezifischesSymptomAnzeichen(
            List<BerichtSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen) {
        this.spezifischesSymptomAnzeichen = spezifischesSymptomAnzeichen;
    }

    public List<BerichtSpezifischesSymptomAnzeichenCluster> getSpezifischesSymptomAnzeichen() {
        return this.spezifischesSymptomAnzeichen;
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
