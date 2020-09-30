package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisMentationIndicatorElement {
    @Path("/value")
    @Choice
    private DenwisChoice5 value;

    public void setValue(DenwisChoice5 value) {
        this.value = value;
    }

    public DenwisChoice5 getValue() {
        return this.value;
    }
}
