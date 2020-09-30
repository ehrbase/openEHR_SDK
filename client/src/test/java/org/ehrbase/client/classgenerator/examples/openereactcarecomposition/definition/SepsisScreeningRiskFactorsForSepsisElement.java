package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SepsisScreeningRiskFactorsForSepsisElement {
    @Path("/value|defining_code")
    private Definingcode2 definingcode;

    public void setDefiningcode(Definingcode2 definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode2 getDefiningcode() {
        return this.definingcode;
    }
}
