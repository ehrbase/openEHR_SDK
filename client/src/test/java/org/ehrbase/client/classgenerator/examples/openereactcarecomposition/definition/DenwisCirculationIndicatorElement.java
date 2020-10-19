package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisCirculationIndicatorElement {
    @Path("/value|defining_code")
    private Definingcode6 definingcode;

    public void setDefiningcode(Definingcode6 definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode6 getDefiningcode() {
        return this.definingcode;
    }
}
