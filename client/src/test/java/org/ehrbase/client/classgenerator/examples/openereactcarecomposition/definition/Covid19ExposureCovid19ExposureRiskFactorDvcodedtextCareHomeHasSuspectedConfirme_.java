package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class Covid19ExposureCovid19ExposureRiskFactorDvcodedtextCareHomeHasSuspectedConfirme_ implements Covid19ExposureRiskFactorChoice {
    @Path("|defining_code")
    private RiskFactorDefiningcodeContactWithConfirmedCase riskFactorDefiningcode;

    public void setRiskFactorDefiningcode(
            RiskFactorDefiningcodeContactWithConfirmedCase riskFactorDefiningcode) {
        this.riskFactorDefiningcode = riskFactorDefiningcode;
    }

    public RiskFactorDefiningcodeContactWithConfirmedCase getRiskFactorDefiningcode() {
        return this.riskFactorDefiningcode;
    }
}
