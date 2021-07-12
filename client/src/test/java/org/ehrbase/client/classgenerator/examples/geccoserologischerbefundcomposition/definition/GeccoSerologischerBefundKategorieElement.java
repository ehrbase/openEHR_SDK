package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-05-19T16:20:30.011759800+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class GeccoSerologischerBefundKategorieElement implements LocatableEntity {
  /**
   * Path: GECCO_Serologischer Befund/context/Kategorie Description: Die Klassifikation des
   * Registereintrags (z.B. Typ der Observation des FHIR-Profils).
   */
  @Path("/value|defining_code")
  private KategorieDefiningCode value;

  /** Path: GECCO_Serologischer Befund/context/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(KategorieDefiningCode value) {
    this.value = value;
  }

  public KategorieDefiningCode getValue() {
    return this.value;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
