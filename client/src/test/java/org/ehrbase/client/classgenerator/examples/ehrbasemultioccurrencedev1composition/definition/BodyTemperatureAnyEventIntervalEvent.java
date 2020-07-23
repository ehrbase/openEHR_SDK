package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.MathFunctionDefiningcode;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;

@Entity
@OptionFor("INTERVAL_EVENT")
public class BodyTemperatureAnyEventIntervalEvent implements BodyTemperatureAnyEventChoice {
    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/data[at0001]/items[at0004]/value|magnitude")
    private Double temperatureMagnitude;

    @Path("/data[at0001]/items[at0004]/value|units")
    private String temperatureUnits;

    @Path("/state[at0029]/items[at0041]/value|value")
    private String descriptionOfThermalStressValue;

    @Path("/state[at0029]/items[at0057]")
    private Cluster exertion;

    @Path("/state[at0029]/items[at0065]/value|magnitude")
    private Long currentDayOfMenstrualCycleMagnitude;

    @Path("/state[at0029]/items[at0056]")
    private List<Cluster> environmentalConditions;

    @Path("/width|value")
    private TemporalAmount widthValue;

    @Path("/state[at0029]/items[at0030]/value")
    @Choice
    private BodyTemperatureBodyExposureChoiceState bodyExposure;

    @Path("/math_function|defining_code")
    private MathFunctionDefiningcode mathFunctionDefiningcode;

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

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

    public void setExertion(Cluster exertion) {
        this.exertion = exertion;
    }

    public Cluster getExertion() {
        return this.exertion;
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

    public void setWidthValue(TemporalAmount widthValue) {
        this.widthValue = widthValue;
    }

    public TemporalAmount getWidthValue() {
        return this.widthValue;
    }

    public void setBodyExposure(BodyTemperatureBodyExposureChoiceState bodyExposure) {
        this.bodyExposure = bodyExposure;
    }

    public BodyTemperatureBodyExposureChoiceState getBodyExposure() {
        return this.bodyExposure;
    }

    public void setMathFunctionDefiningcode(MathFunctionDefiningcode mathFunctionDefiningcode) {
        this.mathFunctionDefiningcode = mathFunctionDefiningcode;
    }

    public MathFunctionDefiningcode getMathFunctionDefiningcode() {
        return this.mathFunctionDefiningcode;
    }
}
