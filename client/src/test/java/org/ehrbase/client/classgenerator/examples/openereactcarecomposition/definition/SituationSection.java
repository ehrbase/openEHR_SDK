package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class SituationSection {
  /**
   * open_eREACT-Care/Situation/Story/History
   */
  @Path("/items[openEHR-EHR-OBSERVATION.story.v1]")
  private StoryHistoryObservation storyHistory;

  public void setStoryHistory(StoryHistoryObservation storyHistory) {
     this.storyHistory = storyHistory;
  }

  public StoryHistoryObservation getStoryHistory() {
     return this.storyHistory ;
  }
}
