package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisDvcodedtext5 implements DenwisChoice5 {
    @Path("|defining_code")
    private Definingcode12 definingcode;

    public void setDefiningcode(Definingcode12 definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode12 getDefiningcode() {
        return this.definingcode;
    }
}
