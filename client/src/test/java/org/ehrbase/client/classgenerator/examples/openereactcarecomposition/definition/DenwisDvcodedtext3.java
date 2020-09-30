package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisDvcodedtext3 implements DenwisChoice3 {
    @Path("|defining_code")
    private Definingcode9 definingcode;

    public void setDefiningcode(Definingcode9 definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode9 getDefiningcode() {
        return this.definingcode;
    }
}
