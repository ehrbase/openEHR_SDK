package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SepsisScreeningLikelySourceOfInfectionElement {
    @Path("/value")
    @Choice
    private SepsisScreeningChoice value;

    public void setValue(SepsisScreeningChoice value) {
        this.value = value;
    }

    public SepsisScreeningChoice getValue() {
        return this.value;
    }
}
