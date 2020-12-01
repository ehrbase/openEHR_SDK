package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class SepsisSection {
  @Path("/items[openEHR-EHR-OBSERVATION.sepsis_screening_tool.v0 and name/value='Sepsis screening']")
  private SepsisScreeningObservation sepsisScreening;

  public void setSepsisScreening(SepsisScreeningObservation sepsisScreening) {
     this.sepsisScreening = sepsisScreening;
  }

  public SepsisScreeningObservation getSepsisScreening() {
     return this.sepsisScreening ;
  }
}
