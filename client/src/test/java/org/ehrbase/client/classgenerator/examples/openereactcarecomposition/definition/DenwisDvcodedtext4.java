package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisDvcodedtext4 implements DenwisChoice4 {
    @Path("|defining_code")
    private Definingcode11 definingcode;

    public void setDefiningcode(Definingcode11 definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode11 getDefiningcode() {
        return this.definingcode;
    }
}
