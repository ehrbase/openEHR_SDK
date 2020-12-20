package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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
    date = "2020-12-10T13:06:13.103064700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BeschaftigungCluster implements LocatableEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener
   * Beruf/Beschäftigung/Berufsbezeichnung/Rolle Description: Die Hauptberufsbezeichnung oder die
   * Rolle der Person.
   */
  @Path("/items[at0005]/value|value")
  private String berufsbezeichnungRolleValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation
   * Description: Mit FHIR-Ressource abgestimmt Details zur Organisation.
   */
  @Path("/items[openEHR-EHR-CLUSTER.organisation_cc.v0]")
  private List<OrganisationCluster> organisation;

  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Zusätzliche Angaben
   * Description: Weitere Angaben zu einer Beschäftigung. Comment: Zum Beispiel: Standort und
   * Bedingungen am Arbeitsplatz oder Kampfzonenerfahrung.
   */
  @Path("/items[at0018]")
  private List<Cluster> zusatzlicheAngaben;

  /** Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBerufsbezeichnungRolleValue(String berufsbezeichnungRolleValue) {
    this.berufsbezeichnungRolleValue = berufsbezeichnungRolleValue;
  }

  public String getBerufsbezeichnungRolleValue() {
    return this.berufsbezeichnungRolleValue;
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

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
