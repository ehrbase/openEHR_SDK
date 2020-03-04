package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class BodyTemperatureLocationOfMeasurementDvtext implements BodyTemperatureLocationOfMeasurementChoice {
    @Path("|value")
    private String locationOfMeasurementValue;

    public void setLocationOfMeasurementValue(String locationOfMeasurementValue) {
        this.locationOfMeasurementValue = locationOfMeasurementValue;
    }

    public String getLocationOfMeasurementValue() {
        return this.locationOfMeasurementValue;
    }
}
