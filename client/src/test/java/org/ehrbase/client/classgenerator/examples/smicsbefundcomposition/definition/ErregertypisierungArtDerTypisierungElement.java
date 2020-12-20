package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.160024500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ErregertypisierungArtDerTypisierungElement implements LocatableEntity {
  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregertypisierung/Art der Typisierung Description: Angaben zu den durchgeführten
   * Typisierungsverfahren in der Infektionskontrolle.
   */
  @Path("/value|defining_code")
  private ArtDerTypisierungDefiningCode value;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregertypisierung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(ArtDerTypisierungDefiningCode value) {
    this.value = value;
  }

  public ArtDerTypisierungDefiningCode getValue() {
    return this.value;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
