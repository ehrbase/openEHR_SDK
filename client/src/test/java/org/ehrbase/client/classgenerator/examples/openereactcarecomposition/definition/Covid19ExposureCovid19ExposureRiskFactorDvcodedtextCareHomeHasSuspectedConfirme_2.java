package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class Covid19ExposureCovid19ExposureRiskFactorDvcodedtextCareHomeHasSuspectedConfirme_2 implements Covid19ExposureRiskFactorChoice {
    @Path("|defining_code")
    private RiskFactorDefiningcodeOtherResidentsHouseholdMembersUnwell riskFactorDefiningcode;

    public void setRiskFactorDefiningcode(
            RiskFactorDefiningcodeOtherResidentsHouseholdMembersUnwell riskFactorDefiningcode) {
        this.riskFactorDefiningcode = riskFactorDefiningcode;
    }

    public RiskFactorDefiningcodeOtherResidentsHouseholdMembersUnwell getRiskFactorDefiningcode() {
        return this.riskFactorDefiningcode;
    }
}
