package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-EVALUATION.health_risk-covid.v0")
public class Covid19ExposureEvaluation {
    @Path("/data[at0001]/items[at0016]/items[at0013.1]/value")
    @Choice
    private Covid19ExposureRiskFactorChoice riskFactor;

    @Path("/data[at0001]/items[at0016]/items[at0027.1]")
    @Choice
    private Covid19ExposureDetailChoice detail;

    @Path("/data[at0001]/items[at0002.1]/value|defining_code")
    private HealthRiskDefiningcode healthRiskDefiningcode;

    @Path("/protocol[at0010]/items[at0011]")
    private List<Cluster> extension;

    @Path("/language")
    private Language language;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/items[at0003.1]/value|defining_code")
    private RiskAssessmentDefiningcode riskAssessmentDefiningcode;

    @Path("/data[at0001]/items[at0016]/items[at0017.1]/value")
    @Choice
    private Covid19ExposurePresenceChoice presence;

    public void setRiskFactor(Covid19ExposureRiskFactorChoice riskFactor) {
        this.riskFactor = riskFactor;
    }

    public Covid19ExposureRiskFactorChoice getRiskFactor() {
        return this.riskFactor;
    }

    public void setDetail(Covid19ExposureDetailChoice detail) {
        this.detail = detail;
    }

    public Covid19ExposureDetailChoice getDetail() {
        return this.detail;
    }

    public void setHealthRiskDefiningcode(HealthRiskDefiningcode healthRiskDefiningcode) {
        this.healthRiskDefiningcode = healthRiskDefiningcode;
    }

    public HealthRiskDefiningcode getHealthRiskDefiningcode() {
        return this.healthRiskDefiningcode;
    }

    public void setExtension(List<Cluster> extension) {
        this.extension = extension;
    }

    public List<Cluster> getExtension() {
        return this.extension;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setRiskAssessmentDefiningcode(RiskAssessmentDefiningcode riskAssessmentDefiningcode) {
        this.riskAssessmentDefiningcode = riskAssessmentDefiningcode;
    }

    public RiskAssessmentDefiningcode getRiskAssessmentDefiningcode() {
        return this.riskAssessmentDefiningcode;
    }

    public void setPresence(Covid19ExposurePresenceChoice presence) {
        this.presence = presence;
    }

    public Covid19ExposurePresenceChoice getPresence() {
        return this.presence;
    }
}
