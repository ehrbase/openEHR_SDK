package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class LatestCovid19TestOverallTestStatusDvcodedtext implements LatestCovid19TestOverallTestStatusChoice {
    @Path("|defining_code")
    private OverallTestStatusDefiningcode overallTestStatusDefiningcode;

    public void setOverallTestStatusDefiningcode(
            OverallTestStatusDefiningcode overallTestStatusDefiningcode) {
        this.overallTestStatusDefiningcode = overallTestStatusDefiningcode;
    }

    public OverallTestStatusDefiningcode getOverallTestStatusDefiningcode() {
        return this.overallTestStatusDefiningcode;
    }
}
