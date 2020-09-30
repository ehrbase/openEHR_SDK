package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisPainIndicatorElement {
    @Path("/value")
    @Choice
    private DenwisChoice6 value;

    public void setValue(DenwisChoice6 value) {
        this.value = value;
    }

    public DenwisChoice6 getValue() {
        return this.value;
    }
}
