package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.775036+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BefundAnforderungElement implements LocatableEntity {
  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Anforderung Description: Name des
   * ursprünglich angeforderten Tests.
   */
  @Path("/value|value")
  private String value;

  /** Path: Virologischer Befund/Befund/Details der Testanforderung/feeder_audit */
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
