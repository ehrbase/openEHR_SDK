package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisDvcodedtext5 implements DenwisChoice5 {
    @Path("|defining_code")
    private ValueDefiningcode6 valueDefiningcode;

    public void setValueDefiningcode(ValueDefiningcode6 valueDefiningcode) {
        this.valueDefiningcode = valueDefiningcode;
    }

    public ValueDefiningcode6 getValueDefiningcode() {
        return this.valueDefiningcode;
    }
}
