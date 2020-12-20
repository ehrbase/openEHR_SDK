package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address_cc.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.105064100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AdresseCluster implements LocatableEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener
   * Beruf/Beschäftigung/Organisation/Adresse/Stadt Description: Der Name der Stadt, des Ortes, des
   * Dorfes oder einer anderen Gemeinde oder eines Lieferzentrums.
   */
  @Path("/items[at0012]/value|value")
  private String stadtValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener
   * Beruf/Beschäftigung/Organisation/Adresse/Land Description: Land - eine Nation, wie allgemein
   * verstanden oder allgemein akzeptiert.
   */
  @Path("/items[at0015]/value|value")
  private String landValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener
   * Beruf/Beschäftigung/Organisation/Adresse/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStadtValue(String stadtValue) {
    this.stadtValue = stadtValue;
  }

  public String getStadtValue() {
    return this.stadtValue;
  }

  public void setLandValue(String landValue) {
    this.landValue = landValue;
  }

  public String getLandValue() {
    return this.landValue;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
