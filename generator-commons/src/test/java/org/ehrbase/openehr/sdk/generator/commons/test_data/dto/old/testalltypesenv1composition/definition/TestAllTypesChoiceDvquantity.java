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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.testalltypesenv1composition.definition;

import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;

@Entity
@OptionFor("DV_QUANTITY")
public class TestAllTypesChoiceDvquantity implements TestAllTypesChoiceChoice {
    @Path("|magnitude")
    private Double choiceMagnitude;

    @Path("|units")
    private String choiceUnits;

    public void setChoiceMagnitude(Double choiceMagnitude) {
        this.choiceMagnitude = choiceMagnitude;
    }

    public Double getChoiceMagnitude() {
        return this.choiceMagnitude;
    }

    public void setChoiceUnits(String choiceUnits) {
        this.choiceUnits = choiceUnits;
    }

    public String getChoiceUnits() {
        return this.choiceUnits;
    }
}
