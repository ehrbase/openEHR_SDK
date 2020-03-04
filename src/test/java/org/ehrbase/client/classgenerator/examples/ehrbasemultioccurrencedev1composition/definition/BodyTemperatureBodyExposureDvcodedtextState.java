package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class BodyTemperatureBodyExposureDvcodedtextState implements BodyTemperatureBodyExposureChoiceState {
    @Path("|defining_code")
    private BodyExposureDefiningcode bodyExposureDefiningcode;

    public void setBodyExposureDefiningcode(BodyExposureDefiningcode bodyExposureDefiningcode) {
        this.bodyExposureDefiningcode = bodyExposureDefiningcode;
    }

    public BodyExposureDefiningcode getBodyExposureDefiningcode() {
        return this.bodyExposureDefiningcode;
    }
}
