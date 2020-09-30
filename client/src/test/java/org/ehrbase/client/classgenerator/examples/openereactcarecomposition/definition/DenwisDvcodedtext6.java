package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisDvcodedtext6 implements DenwisChoice6 {
    @Path("|defining_code")
    private Definingcode14 definingcode;

    public void setDefiningcode(Definingcode14 definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode14 getDefiningcode() {
        return this.definingcode;
    }
}
