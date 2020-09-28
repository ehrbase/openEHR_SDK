package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisTrajectoryIndicatorElement {
    @Path("/value")
    @Choice
    private DenwisChoice2 value;

    public void setValue(DenwisChoice2 value) {
        this.value = value;
    }

    public DenwisChoice2 getValue() {
        return this.value;
    }
}
