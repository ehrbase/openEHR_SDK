package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisDvcodedtext2 implements DenwisChoice2 {
    @Path("|defining_code")
    private Definingcode8 definingcode;

    public void setDefiningcode(Definingcode8 definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode8 getDefiningcode() {
        return this.definingcode;
    }
}
