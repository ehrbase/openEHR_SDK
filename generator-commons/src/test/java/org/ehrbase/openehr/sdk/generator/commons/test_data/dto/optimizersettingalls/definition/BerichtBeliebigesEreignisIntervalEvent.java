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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingalls.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.IntervalEventEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.MathFunction;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.130031100+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("INTERVAL_EVENT")
public class BerichtBeliebigesEreignisIntervalEvent implements IntervalEventEntity, BerichtBeliebigesEreignisChoice {
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

    /**
     * Path: Bericht/Beliebiges Ereignis/width
     */
    @Path("/width|value")
    private TemporalAmount widthValue;

    /**
     * Path: Bericht/Beliebiges Ereignis/math_function
     */
    @Path("/math_function|defining_code")
    private MathFunction mathFunctionDefiningCode;

    /**
     * Path: Bericht/Beliebiges Ereignis/sample_count
     */
    @Path("/sample_count")
    private Long sampleCount;

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

    public void setWidthValue(TemporalAmount widthValue) {
        this.widthValue = widthValue;
    }

    public TemporalAmount getWidthValue() {
        return this.widthValue;
    }

    public void setMathFunctionDefiningCode(MathFunction mathFunctionDefiningCode) {
        this.mathFunctionDefiningCode = mathFunctionDefiningCode;
    }

    public MathFunction getMathFunctionDefiningCode() {
        return this.mathFunctionDefiningCode;
    }

    public void setSampleCount(Long sampleCount) {
        this.sampleCount = sampleCount;
    }

    public Long getSampleCount() {
        return this.sampleCount;
    }
}
