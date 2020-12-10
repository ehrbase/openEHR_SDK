package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.occupation_record.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T09:57:06.125355100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class BeschaeftigungCluster implements LocatableEntity {
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
  private List<Cluster> zusaetzlicheAngaben;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

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

  public void setZusaetzlicheAngaben(List<Cluster> zusaetzlicheAngaben) {
     this.zusaetzlicheAngaben = zusaetzlicheAngaben;
  }

  public List<Cluster> getZusaetzlicheAngaben() {
     return this.zusaetzlicheAngaben ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
