package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.occupation_record.v1")
public class OccupationRecordCluster {
  @Path("/items[at0005]/value|value")
  private String roleValue;

  @Path("/items[openEHR-EHR-CLUSTER.organisation_cc.v0]")
  private List<OrganisationCluster> organisation;

  @Path("/items[at0018]")
  private List<Cluster> additionalDetails;

  public void setRoleValue(String roleValue) {
     this.roleValue = roleValue;
  }

  public String getRoleValue() {
     return this.roleValue ;
  }

  public void setOrganisation(List<OrganisationCluster> organisation) {
     this.organisation = organisation;
  }

  public List<OrganisationCluster> getOrganisation() {
     return this.organisation ;
  }

  public void setAdditionalDetails(List<Cluster> additionalDetails) {
     this.additionalDetails = additionalDetails;
  }

  public List<Cluster> getAdditionalDetails() {
     return this.additionalDetails ;
  }
}
