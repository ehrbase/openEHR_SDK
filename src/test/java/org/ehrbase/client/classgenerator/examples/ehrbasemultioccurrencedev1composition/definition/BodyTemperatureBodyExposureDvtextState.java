package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class BodyTemperatureBodyExposureDvtextState implements BodyTemperatureBodyExposureChoiceState {
    @Path("|value")
    private String bodyExposureValue;

    public void setBodyExposureValue(String bodyExposureValue) {
        this.bodyExposureValue = bodyExposureValue;
    }

    public String getBodyExposureValue() {
        return this.bodyExposureValue;
    }
}
