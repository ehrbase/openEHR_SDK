package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class RisikogebietSection {
  @Path("/items[openEHR-EHR-OBSERVATION.travel_history.v0]")
  private List<HistorieDerReiseObservation> historieDerReise;

  @Path("/items[openEHR-EHR-OBSERVATION.travel_event.v0]")
  private List<ReisefallObservation> reisefall;

  public void setHistorieDerReise(List<HistorieDerReiseObservation> historieDerReise) {
    this.historieDerReise = historieDerReise;
  }

  public List<HistorieDerReiseObservation> getHistorieDerReise() {
    return this.historieDerReise;
  }

  public void setReisefall(List<ReisefallObservation> reisefall) {
    this.reisefall = reisefall;
  }

  public List<ReisefallObservation> getReisefall() {
    return this.reisefall;
  }
}
