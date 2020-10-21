package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SepsisScreeningAmberFlagAcuteElement {
    @Path("/value|defining_code")
    private Definingcode5 definingcode;

    public void setDefiningcode(Definingcode5 definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode5 getDefiningcode() {
        return this.definingcode;
    }
}
