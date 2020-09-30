package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class Covid19ExposurePresenceDvcodedtextCareHomeHasSuspectedConfirmedCovid19 implements Covid19ExposurePresenceChoice {
    @Path("|defining_code")
    private PresenceDefiningcode presenceDefiningcode;

    public void setPresenceDefiningcode(PresenceDefiningcode presenceDefiningcode) {
        this.presenceDefiningcode = presenceDefiningcode;
    }

    public PresenceDefiningcode getPresenceDefiningcode() {
        return this.presenceDefiningcode;
    }
}
