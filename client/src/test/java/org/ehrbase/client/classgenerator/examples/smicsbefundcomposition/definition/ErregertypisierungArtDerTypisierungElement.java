package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
public class ErregertypisierungArtDerTypisierungElement implements LocatableEntity {
  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregertypisierung/Art der Typisierung
   */
  @Path("/value|defining_code")
  private ArtDerTypisierungDefiningCode value;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregertypisierung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(ArtDerTypisierungDefiningCode value) {
     this.value = value;
  }

  public ArtDerTypisierungDefiningCode getValue() {
     return this.value ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
