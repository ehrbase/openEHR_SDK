package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisNurseSubjectiveIndicatorElement {
    @Path("/value")
    @Choice
    private DenwisChoice4 value;

    public void setValue(DenwisChoice4 value) {
        this.value = value;
    }

    public DenwisChoice4 getValue() {
        return this.value;
    }
}
