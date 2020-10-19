package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisDvcodedtext4 implements DenwisChoice4 {
    @Path("|defining_code")
    private ValueDefiningcode5 valueDefiningcode;

    public void setValueDefiningcode(ValueDefiningcode5 valueDefiningcode) {
        this.valueDefiningcode = valueDefiningcode;
    }

    public ValueDefiningcode5 getValueDefiningcode() {
        return this.valueDefiningcode;
    }
}
