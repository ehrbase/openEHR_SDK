package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.354025300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AtiopathogeneseBeschreibungDesEntstehensElement implements LocatableEntity {
  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/Beschreibung des Entstehens
   * Description: Weitere Beschreibung der Ursachen oder Gründe für das Entstehen (Ätiologie) eines
   * spezifischen Problems/einer Diagnose.
   */
  @Path("/value|value")
  private String value;

  /** Path: COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/feeder_audit */
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
