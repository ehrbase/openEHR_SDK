package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class SepsisScreeningDvcodedtext implements SepsisScreeningChoice {
    @Path("|defining_code")
    private ValueDefiningcode valueDefiningcode;

    public void setValueDefiningcode(ValueDefiningcode valueDefiningcode) {
        this.valueDefiningcode = valueDefiningcode;
    }

    public ValueDefiningcode getValueDefiningcode() {
        return this.valueDefiningcode;
    }
}
