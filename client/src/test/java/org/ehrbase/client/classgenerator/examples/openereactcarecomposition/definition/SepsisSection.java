package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class SepsisSection {
  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening
   */
  @Path("/items[openEHR-EHR-OBSERVATION.sepsis_screening_tool.v0 and name/value='Sepsis screening']")
  private SepsisScreeningObservation sepsisScreening;

  /**
   * open_eREACT-Care/Assessment/Sepsis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSepsisScreening(SepsisScreeningObservation sepsisScreening) {
     this.sepsisScreening = sepsisScreening;
  }

  public SepsisScreeningObservation getSepsisScreening() {
     return this.sepsisScreening ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
