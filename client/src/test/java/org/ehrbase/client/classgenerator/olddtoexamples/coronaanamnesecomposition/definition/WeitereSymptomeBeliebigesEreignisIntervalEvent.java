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
package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.MathFunctionDefiningcode;

@Entity
@OptionFor("INTERVAL_EVENT")
public class WeitereSymptomeBeliebigesEreignisIntervalEvent implements WeitereSymptomeBeliebigesEreignisChoice {
    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/math_function|defining_code")
    private MathFunctionDefiningcode mathFunctionDefiningcode;

    @Path("/data[at0003]/items[at0022]")
    private List<WeitereSymptomeSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen;

    @Path("/width|value")
    private TemporalAmount widthValue;

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setMathFunctionDefiningcode(MathFunctionDefiningcode mathFunctionDefiningcode) {
        this.mathFunctionDefiningcode = mathFunctionDefiningcode;
    }

    public MathFunctionDefiningcode getMathFunctionDefiningcode() {
        return this.mathFunctionDefiningcode;
    }

    public void setSpezifischesSymptomAnzeichen(
            List<WeitereSymptomeSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen) {
        this.spezifischesSymptomAnzeichen = spezifischesSymptomAnzeichen;
    }

    public List<WeitereSymptomeSpezifischesSymptomAnzeichenCluster> getSpezifischesSymptomAnzeichen() {
        return this.spezifischesSymptomAnzeichen;
    }

    public void setWidthValue(TemporalAmount widthValue) {
        this.widthValue = widthValue;
    }

    public TemporalAmount getWidthValue() {
        return this.widthValue;
    }
}
