package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisDvcodedtext implements DenwisChoice {
    @Path("|defining_code")
    private ValueDefiningcode2 valueDefiningcode;

    public void setValueDefiningcode(ValueDefiningcode2 valueDefiningcode) {
        this.valueDefiningcode = valueDefiningcode;
    }

    public ValueDefiningcode2 getValueDefiningcode() {
        return this.valueDefiningcode;
    }
}
