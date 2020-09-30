package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisAgitationIndicatorElement {
    @Path("/value")
    @Choice
    private DenwisChoice3 value;

    public void setValue(DenwisChoice3 value) {
        this.value = value;
    }

    public DenwisChoice3 getValue() {
        return this.value;
    }
}
