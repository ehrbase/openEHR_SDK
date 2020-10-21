package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisDvcodedtext3 implements DenwisChoice3 {
    @Path("|defining_code")
    private ValueDefiningcode4 valueDefiningcode;

    public void setValueDefiningcode(ValueDefiningcode4 valueDefiningcode) {
        this.valueDefiningcode = valueDefiningcode;
    }

    public ValueDefiningcode4 getValueDefiningcode() {
        return this.valueDefiningcode;
    }
}
