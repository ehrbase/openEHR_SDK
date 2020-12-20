package org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.094492500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class KorpergewichtConfoundingFactorsEnElement implements LocatableEntity {
  /**
   * Path: Bericht/Körpergewicht/*Birth(en)/*Confounding factors(en) Description: *Record any issues
   * or factors that may impact on the measurement of body weight eg timing in menstrual cycle,
   * timing of recent bowel motion or noting of amputation.(en)
   */
  @Path("/value|value")
  private String value;

  /** Path: Bericht/Körpergewicht/*Birth(en)/feeder_audit */
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
