package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class AtiopathogeneseSchweregradDvcodedtext implements AtiopathogeneseSchweregradChoice {
    @Path("|defining_code")
    private SchweregradDefiningcode schweregradDefiningcode;

    public void setSchweregradDefiningcode(SchweregradDefiningcode schweregradDefiningcode) {
        this.schweregradDefiningcode = schweregradDefiningcode;
    }

    public SchweregradDefiningcode getSchweregradDefiningcode() {
        return this.schweregradDefiningcode;
    }
}
