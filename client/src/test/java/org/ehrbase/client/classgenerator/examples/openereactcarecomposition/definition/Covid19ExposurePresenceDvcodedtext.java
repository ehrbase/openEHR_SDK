package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class Covid19ExposurePresenceDvcodedtext implements Covid19ExposurePresenceChoice {
    @Path("|defining_code")
    private PresenceDefiningcodeCareHomeHasSuspectedConfirmedCovid19 presenceDefiningcode;

    public void setPresenceDefiningcode(
            PresenceDefiningcodeCareHomeHasSuspectedConfirmedCovid19 presenceDefiningcode) {
        this.presenceDefiningcode = presenceDefiningcode;
    }

    public PresenceDefiningcodeCareHomeHasSuspectedConfirmedCovid19 getPresenceDefiningcode() {
        return this.presenceDefiningcode;
    }
}
