package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.symptom_sign-cvid.v0")
public class KeyCovidSymptomCluster {
    @Path("/items[at0063]")
    private List<Cluster> sign;

    @Path("/items[at0153]")
    private List<Cluster> specificDetails;

    @Path("/items[at0001.1]/value|defining_code")
    private SymptomSignNameDefiningcode3 symptomSignNameDefiningcode;

    @Path("/items[at0146]")
    private List<Cluster> previousEpisodes;

    @Path("/items[at0.1]/value|defining_code")
    private PresenceDefiningcode presenceDefiningcode;

    @Path("/items[at0147]")
    private List<Cluster> structuredBodySite;

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
            SymptomSignNameDefiningcode3 symptomSignNameDefiningcode) {
        this.symptomSignNameDefiningcode = symptomSignNameDefiningcode;
    }

    public SymptomSignNameDefiningcode3 getSymptomSignNameDefiningcode() {
        return this.symptomSignNameDefiningcode;
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
}
