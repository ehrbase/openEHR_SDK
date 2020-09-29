package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisPatientIndicatorElement {
    @Path("/value")
    @Choice
    private DenwisChoice value;

    public void setValue(DenwisChoice value) {
        this.value = value;
    }

    public DenwisChoice getValue() {
        return this.value;
    }
}
