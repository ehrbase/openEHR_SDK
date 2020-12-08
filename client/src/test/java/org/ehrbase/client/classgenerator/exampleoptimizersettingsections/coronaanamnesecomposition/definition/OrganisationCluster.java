package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.organisation_cc.v0")
public class OrganisationCluster implements LocatableEntity {
  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Identifier
   */
  @Path("/items[at0018]")
  private List<Cluster> identifier;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Name der Einrichtung
   */
  @Path("/items[at0012 and name/value='Name der Einrichtung']/value|value")
  private String nameDerEinrichtungValue;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Telefon
   */
  @Path("/items[at0014]")
  private List<Cluster> telefon;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Adresse
   */
  @Path("/items[openEHR-EHR-CLUSTER.address_cc.v0]")
  private List<AdresseCluster> adresse;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Teil von
   */
  @Path("/items[at0017]")
  private List<Cluster> teilVon;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Kontakt
   */
  @Path("/items[at0016]")
  private List<Cluster> kontakt;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setIdentifier(List<Cluster> identifier) {
     this.identifier = identifier;
  }

  public List<Cluster> getIdentifier() {
     return this.identifier ;
  }

  public void setNameDerEinrichtungValue(String nameDerEinrichtungValue) {
     this.nameDerEinrichtungValue = nameDerEinrichtungValue;
  }

  public String getNameDerEinrichtungValue() {
     return this.nameDerEinrichtungValue ;
  }

  public void setTelefon(List<Cluster> telefon) {
     this.telefon = telefon;
  }

  public List<Cluster> getTelefon() {
     return this.telefon ;
  }

  public void setAdresse(List<AdresseCluster> adresse) {
     this.adresse = adresse;
  }

  public List<AdresseCluster> getAdresse() {
     return this.adresse ;
  }

  public void setTeilVon(List<Cluster> teilVon) {
     this.teilVon = teilVon;
  }

  public List<Cluster> getTeilVon() {
     return this.teilVon ;
  }

  public void setKontakt(List<Cluster> kontakt) {
     this.kontakt = kontakt;
  }

  public List<Cluster> getKontakt() {
     return this.kontakt ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
