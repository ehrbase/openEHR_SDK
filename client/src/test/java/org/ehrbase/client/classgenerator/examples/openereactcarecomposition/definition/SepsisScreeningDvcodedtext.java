package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class SepsisScreeningDvcodedtext implements SepsisScreeningChoice {
    @Path("|defining_code")
    private Definingcode3 definingcode;

    public void setDefiningcode(Definingcode3 definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode3 getDefiningcode() {
        return this.definingcode;
    }
}
