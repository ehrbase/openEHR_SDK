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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition.MathFunctionDefiningcode;

@Entity
@OptionFor("INTERVAL_EVENT")
public class GeschichteHistorieBeliebigesEreignisIntervalEvent implements GeschichteHistorieBeliebigesEreignisChoice {
    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/data[at0003]/items[at0004]")
    private List<GeschichteHistorieGeschichteElement> geschichte;

    @Path("/math_function|defining_code")
    private MathFunctionDefiningcode mathFunctionDefiningcode;

    @Path("/width|value")
    private TemporalAmount widthValue;

    @Path("/data[at0003]/items[at0006]")
    private List<Cluster> strukturierteAngabe;

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setGeschichte(List<GeschichteHistorieGeschichteElement> geschichte) {
        this.geschichte = geschichte;
    }

    public List<GeschichteHistorieGeschichteElement> getGeschichte() {
        return this.geschichte;
    }

    public void setMathFunctionDefiningcode(MathFunctionDefiningcode mathFunctionDefiningcode) {
        this.mathFunctionDefiningcode = mathFunctionDefiningcode;
    }

    public MathFunctionDefiningcode getMathFunctionDefiningcode() {
        return this.mathFunctionDefiningcode;
    }

    public void setWidthValue(TemporalAmount widthValue) {
        this.widthValue = widthValue;
    }

    public TemporalAmount getWidthValue() {
        return this.widthValue;
    }

    public void setStrukturierteAngabe(List<Cluster> strukturierteAngabe) {
        this.strukturierteAngabe = strukturierteAngabe;
    }

    public List<Cluster> getStrukturierteAngabe() {
        return this.strukturierteAngabe;
    }
}
