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
    date = "2020-12-10T13:06:11.405499800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class SepsisSection implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening Description: Sepsis screening tool
   * for ages 12 plus based on the paper forms developed by The UK Sepsis Trust.
   */
  @Path(
      "/items[openEHR-EHR-OBSERVATION.sepsis_screening_tool.v0 and name/value='Sepsis screening']")
  private SepsisScreeningObservation sepsisScreening;

  /** Path: open_eREACT-Care/Assessment/Sepsis/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSepsisScreening(SepsisScreeningObservation sepsisScreening) {
    this.sepsisScreening = sepsisScreening;
  }

  public SepsisScreeningObservation getSepsisScreening() {
    return this.sepsisScreening;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
