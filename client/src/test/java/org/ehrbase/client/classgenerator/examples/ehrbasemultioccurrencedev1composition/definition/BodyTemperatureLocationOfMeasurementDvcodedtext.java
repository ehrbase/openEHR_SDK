package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class BodyTemperatureLocationOfMeasurementDvcodedtext implements BodyTemperatureLocationOfMeasurementChoice {
    @Path("|defining_code")
    private LocationOfMeasurementDefiningcode locationOfMeasurementDefiningcode;

    public void setLocationOfMeasurementDefiningcode(
            LocationOfMeasurementDefiningcode locationOfMeasurementDefiningcode) {
        this.locationOfMeasurementDefiningcode = locationOfMeasurementDefiningcode;
    }

    public LocationOfMeasurementDefiningcode getLocationOfMeasurementDefiningcode() {
        return this.locationOfMeasurementDefiningcode;
    }
}
