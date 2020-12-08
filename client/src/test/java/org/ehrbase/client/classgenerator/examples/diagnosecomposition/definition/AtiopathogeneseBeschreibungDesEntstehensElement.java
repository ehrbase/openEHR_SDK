package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
public class AtiopathogeneseBeschreibungDesEntstehensElement implements LocatableEntity {
  /**
   * COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/Beschreibung des Entstehens
   */
  @Path("/value|value")
  private String value;

  /**
   * COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/feeder_audit
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
