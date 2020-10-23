package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SepsisScreeningA999FlagElement {
    @Path("/value|defining_code")
    private Definingcode4 definingcode;

    public void setDefiningcode(Definingcode4 definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode4 getDefiningcode() {
        return this.definingcode;
    }
}
