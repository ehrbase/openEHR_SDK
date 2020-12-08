package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
public class DienstleistungGrundFurDieAnforderungElement implements LocatableEntity {
  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Grund für die Anforderung
   */
  @Path("/value|value")
  private String value;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(String value) {
     this.value = value;
  }

  public String getValue() {
     return this.value ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
