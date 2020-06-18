package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.occupation_record.v1")
public class BeschaftigungCluster {
    @Path("/items[at0005]/value|value")
    private String rolleValue;

    @Path("/items[openEHR-EHR-CLUSTER.organisation_cc.v0]")
    private List<OrganisationCluster> organisation;

    @Path("/items[at0018]")
    private List<Cluster> zusatzlicheAngaben;

    public void setRolleValue(String rolleValue) {
        this.rolleValue = rolleValue;
    }

    public String getRolleValue() {
        return this.rolleValue;
    }

    public void setOrganisation(List<OrganisationCluster> organisation) {
        this.organisation = organisation;
    }

    public List<OrganisationCluster> getOrganisation() {
        return this.organisation;
    }

    public void setZusatzlicheAngaben(List<Cluster> zusatzlicheAngaben) {
        this.zusatzlicheAngaben = zusatzlicheAngaben;
    }

    public List<Cluster> getZusatzlicheAngaben() {
        return this.zusatzlicheAngaben;
    }
}
