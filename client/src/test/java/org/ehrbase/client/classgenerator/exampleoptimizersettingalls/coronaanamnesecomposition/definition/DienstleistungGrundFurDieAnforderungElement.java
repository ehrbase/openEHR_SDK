package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.125029600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class DienstleistungGrundFurDieAnforderungElement implements LocatableEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/Grund für die Anforderung
   * Description: Ein kurzer Satz, der den Grund für die Anforderung beschreibt.
   */
  @Path("/value|value")
  private String value;

  /** Path: Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
