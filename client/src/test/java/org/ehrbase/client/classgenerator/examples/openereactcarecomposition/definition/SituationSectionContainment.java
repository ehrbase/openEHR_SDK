package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class SituationSectionContainment extends Containment {
  public SelectAqlField<SituationSection> SITUATION_SECTION =
      new AqlFieldImp<SituationSection>(
          SituationSection.class, "", "SituationSection", SituationSection.class, this);

  public SelectAqlField<StoryHistoryObservation> STORY_HISTORY =
      new AqlFieldImp<StoryHistoryObservation>(
          SituationSection.class,
          "/items[openEHR-EHR-OBSERVATION.story.v1]",
          "storyHistory",
          StoryHistoryObservation.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          SituationSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private SituationSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static SituationSectionContainment getInstance() {
    return new SituationSectionContainment();
  }
}
