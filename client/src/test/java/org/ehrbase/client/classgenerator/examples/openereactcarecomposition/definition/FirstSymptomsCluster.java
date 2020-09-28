package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SymptomSignNameDefiningcode;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.symptom_sign-cvid.v0")
public class FirstSymptomsCluster {
    @Path("/items[at0028]/value|value")
    private TemporalAmount durationValue;

    @Path("/items[at0063]")
    private List<Cluster> sign;

    @Path("/items[at0153]")
    private List<Cluster> specificDetails;

    @Path("/items[at0001.1]/value|defining_code")
    private SymptomSignNameDefiningcode symptomSignNameDefiningcode;

    @Path("/items[at0180]/value|defining_code")
    private TrendDefiningcode trendDefiningcode;

    @Path("/items[at0146]")
    private List<Cluster> previousEpisodes;

    @Path("/items[at0.1]/value|defining_code")
    private PresenceDefiningcode presenceDefiningcode;

    @Path("/items[at0147]")
    private List<Cluster> structuredBodySite;

    @Path("/items[at0152]/value|value")
    private TemporalAccessor dateOfOnsetOfFirstSymptomsValue;

    @Path("/items[at0155]/value|value")
    private String impactValue;

    public void setDurationValue(TemporalAmount durationValue) {
        this.durationValue = durationValue;
    }

    public TemporalAmount getDurationValue() {
        return this.durationValue;
    }

    public void setSign(List<Cluster> sign) {
        this.sign = sign;
    }

    public List<Cluster> getSign() {
        return this.sign;
    }

    public void setSpecificDetails(List<Cluster> specificDetails) {
        this.specificDetails = specificDetails;
    }

    public List<Cluster> getSpecificDetails() {
        return this.specificDetails;
    }

    public void setSymptomSignNameDefiningcode(
            SymptomSignNameDefiningcode symptomSignNameDefiningcode) {
        this.symptomSignNameDefiningcode = symptomSignNameDefiningcode;
    }

    public SymptomSignNameDefiningcode getSymptomSignNameDefiningcode() {
        return this.symptomSignNameDefiningcode;
    }

    public void setTrendDefiningcode(TrendDefiningcode trendDefiningcode) {
        this.trendDefiningcode = trendDefiningcode;
    }

    public TrendDefiningcode getTrendDefiningcode() {
        return this.trendDefiningcode;
    }

    public void setPreviousEpisodes(List<Cluster> previousEpisodes) {
        this.previousEpisodes = previousEpisodes;
    }

    public List<Cluster> getPreviousEpisodes() {
        return this.previousEpisodes;
    }

    public void setPresenceDefiningcode(PresenceDefiningcode presenceDefiningcode) {
        this.presenceDefiningcode = presenceDefiningcode;
    }

    public PresenceDefiningcode getPresenceDefiningcode() {
        return this.presenceDefiningcode;
    }

    public void setStructuredBodySite(List<Cluster> structuredBodySite) {
        this.structuredBodySite = structuredBodySite;
    }

    public List<Cluster> getStructuredBodySite() {
        return this.structuredBodySite;
    }

    public void setDateOfOnsetOfFirstSymptomsValue(TemporalAccessor dateOfOnsetOfFirstSymptomsValue) {
        this.dateOfOnsetOfFirstSymptomsValue = dateOfOnsetOfFirstSymptomsValue;
    }

    public TemporalAccessor getDateOfOnsetOfFirstSymptomsValue() {
        return this.dateOfOnsetOfFirstSymptomsValue;
    }

    public void setImpactValue(String impactValue) {
        this.impactValue = impactValue;
    }

    public String getImpactValue() {
        return this.impactValue;
    }
}
