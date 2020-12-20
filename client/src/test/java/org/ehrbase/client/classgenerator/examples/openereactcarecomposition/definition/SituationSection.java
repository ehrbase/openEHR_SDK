package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.313502100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class SituationSection implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Situation/Story/History Description: The subjective clinical history of
   * the subject of care as recorded directly by the subject, or reported to a clinician by the
   * subject or a carer.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.story.v1]")
  private StoryHistoryObservation storyHistory;

  /** Path: open_eREACT-Care/Situation/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStoryHistory(StoryHistoryObservation storyHistory) {
    this.storyHistory = storyHistory;
  }

  public StoryHistoryObservation getStoryHistory() {
    return this.storyHistory;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
