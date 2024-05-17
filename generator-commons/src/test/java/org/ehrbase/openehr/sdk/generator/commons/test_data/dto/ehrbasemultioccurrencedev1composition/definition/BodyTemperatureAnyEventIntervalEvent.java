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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Choice;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.IntervalEventEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.MathFunction;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:11.014497+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("INTERVAL_EVENT")
public class BodyTemperatureAnyEventIntervalEvent implements IntervalEventEntity, BodyTemperatureAnyEventChoice {
    /**
     * Path: Encounter/Body temperature/Any event/Temperature
     * Description: The measured body temperature (as a surrogate for the core of the body).
     *
     */
    @Path("/data[at0001]/items[at0004]/value|magnitude")
    private Double temperatureMagnitude;

    /**
     * Path: Encounter/Body temperature/Any event/Temperature
     * Description: The measured body temperature (as a surrogate for the core of the body).
     *
     */
    @Path("/data[at0001]/items[at0004]/value|units")
    private String temperatureUnits;

    /**
     * Path: Encounter/Body temperature/Any event/Description of thermal stress
     * Description: Description of the conditions applied to the subject that might influence
     *                         their measured body temperature.
     *
     */
    @Path("/state[at0029]/items[at0041]/value|value")
    private String descriptionOfThermalStressValue;

    /**
     * Path: Encounter/Body temperature/Any event/Current day of menstrual cycle
     * Description: Number of days since onset of last normal menstrual period.
     */
    @Path("/state[at0029]/items[at0065]/value|magnitude")
    private Long currentDayOfMenstrualCycleMagnitude;

    /**
     * Path: Encounter/Body temperature/Any event/Environmental conditions
     * Description: Details about the environmental conditions at the time of temperature
     *                         measurement.
     *
     */
    @Path("/state[at0029]/items[at0056]")
    private List<Cluster> environmentalConditions;

    /**
     * Path: Encounter/Body temperature/Any event/Exertion
     * Description: Details about the exertion of the person at the time of temperature
     *                         measurement.
     *
     */
    @Path("/state[at0029]/items[at0057]")
    private Cluster exertion;

    /**
     * Path: Encounter/Body temperature/Any event/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Encounter/Body temperature/Any event/time
     */
    @Path("/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: Encounter/Body temperature/Any event/width
     */
    @Path("/width|value")
    private TemporalAmount widthValue;

    /**
     * Path: Encounter/Body temperature/Any event/math_function
     */
    @Path("/math_function|defining_code")
    private MathFunction mathFunctionDefiningCode;

    /**
     * Path: Encounter/Body temperature/Any event/sample_count
     */
    @Path("/sample_count")
    private Long sampleCount;

    /**
     * Path: Encounter/Body temperature/Any event/value
     */
    @Path("/state[at0029]/items[at0030]/value")
    @Choice
    private BodyTemperatureBodyExposureChoice bodyExposure;

    public void setTemperatureMagnitude(Double temperatureMagnitude) {
        this.temperatureMagnitude = temperatureMagnitude;
    }

    public Double getTemperatureMagnitude() {
        return this.temperatureMagnitude;
    }

    public void setTemperatureUnits(String temperatureUnits) {
        this.temperatureUnits = temperatureUnits;
    }

    public String getTemperatureUnits() {
        return this.temperatureUnits;
    }

    public void setDescriptionOfThermalStressValue(String descriptionOfThermalStressValue) {
        this.descriptionOfThermalStressValue = descriptionOfThermalStressValue;
    }

    public String getDescriptionOfThermalStressValue() {
        return this.descriptionOfThermalStressValue;
    }

    public void setCurrentDayOfMenstrualCycleMagnitude(Long currentDayOfMenstrualCycleMagnitude) {
        this.currentDayOfMenstrualCycleMagnitude = currentDayOfMenstrualCycleMagnitude;
    }

    public Long getCurrentDayOfMenstrualCycleMagnitude() {
        return this.currentDayOfMenstrualCycleMagnitude;
    }

    public void setEnvironmentalConditions(List<Cluster> environmentalConditions) {
        this.environmentalConditions = environmentalConditions;
    }

    public List<Cluster> getEnvironmentalConditions() {
        return this.environmentalConditions;
    }

    public void setExertion(Cluster exertion) {
        this.exertion = exertion;
    }

    public Cluster getExertion() {
        return this.exertion;
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

    public void setBodyExposure(BodyTemperatureBodyExposureChoice bodyExposure) {
        this.bodyExposure = bodyExposure;
    }

    public BodyTemperatureBodyExposureChoice getBodyExposure() {
        return this.bodyExposure;
    }
}
