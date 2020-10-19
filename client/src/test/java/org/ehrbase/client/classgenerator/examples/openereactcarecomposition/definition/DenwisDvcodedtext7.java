package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisDvcodedtext7 implements DenwisChoice7 {
    @Path("|defining_code")
    private ValueDefiningcode8 valueDefiningcode;

    public void setValueDefiningcode(ValueDefiningcode8 valueDefiningcode) {
        this.valueDefiningcode = valueDefiningcode;
    }

    public ValueDefiningcode8 getValueDefiningcode() {
        return this.valueDefiningcode;
    }
}
