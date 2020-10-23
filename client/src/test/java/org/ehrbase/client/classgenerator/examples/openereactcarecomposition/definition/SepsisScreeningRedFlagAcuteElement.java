package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SepsisScreeningRedFlagAcuteElement {
    @Path("/value|defining_code")
    private Definingcode3 definingcode;

    public void setDefiningcode(Definingcode3 definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode3 getDefiningcode() {
        return this.definingcode;
    }
}
