package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisDvcodedtext6 implements DenwisChoice6 {
    @Path("|defining_code")
    private ValueDefiningcode7 valueDefiningcode;

    public void setValueDefiningcode(ValueDefiningcode7 valueDefiningcode) {
        this.valueDefiningcode = valueDefiningcode;
    }

    public ValueDefiningcode7 getValueDefiningcode() {
        return this.valueDefiningcode;
    }
}
