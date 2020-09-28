package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisTemperatureIndicatorElement {
    @Path("/value")
    @Choice
    private DenwisChoice7 value;

    public void setValue(DenwisChoice7 value) {
        this.value = value;
    }

    public DenwisChoice7 getValue() {
        return this.value;
    }
}
