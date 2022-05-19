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
package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-10-08T15:38:06.346881500+02:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_QUANTITY")
public class TestAllTypesChoiceDvQuantity implements RMEntity, TestAllTypesChoiceChoice {
    /**
     * Path: Test all types/Test all types/choice/choice
     * Description: *
     */
    @Path("|magnitude")
    private Double choiceMagnitude;

    /**
     * Path: Test all types/Test all types/choice/choice
     * Description: *
     */
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
