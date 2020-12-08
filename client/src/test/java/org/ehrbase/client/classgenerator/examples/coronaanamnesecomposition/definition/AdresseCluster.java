package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address_cc.v0")
public class AdresseCluster implements LocatableEntity {
  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Adresse/Stadt
   */
  @Path("/items[at0012]/value|value")
  private String stadtValue;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Adresse/Land
   */
  @Path("/items[at0015]/value|value")
  private String landValue;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Adresse/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStadtValue(String stadtValue) {
     this.stadtValue = stadtValue;
  }

  public String getStadtValue() {
     return this.stadtValue ;
  }

  public void setLandValue(String landValue) {
     this.landValue = landValue;
  }

  public String getLandValue() {
     return this.landValue ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
