package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.584502400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class RecommendationRecommendationElement implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Response/Recommendation/Recommendation Description: Narrative
   * description of the recommendation.
   */
  @Path("/value|value")
  private String value;

  /** Path: open_eREACT-Care/Response/Recommendation/feeder_audit */
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
