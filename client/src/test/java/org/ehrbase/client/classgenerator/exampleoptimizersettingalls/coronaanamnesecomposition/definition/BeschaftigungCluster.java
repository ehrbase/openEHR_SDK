package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.occupation_record.v1")
public class BeschaftigungCluster {
  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Berufsbezeichnung/Rolle
   */
  @Path("/items[at0005]/value|value")
  private String berufsbezeichnungRolleValue;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation
   */
  @Path("/items[openEHR-EHR-CLUSTER.organisation_cc.v0]")
  private List<OrganisationCluster> organisation;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Zusätzliche Angaben
   */
  @Path("/items[at0018]")
  private List<Cluster> zusatzlicheAngaben;

  public void setBerufsbezeichnungRolleValue(String berufsbezeichnungRolleValue) {
     this.berufsbezeichnungRolleValue = berufsbezeichnungRolleValue;
  }

  public String getBerufsbezeichnungRolleValue() {
     return this.berufsbezeichnungRolleValue ;
  }

  public void setOrganisation(List<OrganisationCluster> organisation) {
     this.organisation = organisation;
  }

  public List<OrganisationCluster> getOrganisation() {
     return this.organisation ;
  }

  public void setZusatzlicheAngaben(List<Cluster> zusatzlicheAngaben) {
     this.zusatzlicheAngaben = zusatzlicheAngaben;
  }

  public List<Cluster> getZusatzlicheAngaben() {
     return this.zusatzlicheAngaben ;
  }
}
