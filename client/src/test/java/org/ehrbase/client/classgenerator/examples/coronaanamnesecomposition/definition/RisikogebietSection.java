package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class RisikogebietSection implements LocatableEntity {
  /**
   * Bericht/Risikogebiet/Historie der Reise
   */
  @Path("/items[openEHR-EHR-OBSERVATION.travel_history.v0]")
  private List<HistorieDerReiseObservation> historieDerReise;

  /**
   * Bericht/Risikogebiet/Reisefall
   */
  @Path("/items[openEHR-EHR-OBSERVATION.travel_event.v0]")
  private List<ReisefallObservation> reisefall;

  /**
   * Bericht/Risikogebiet/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setHistorieDerReise(List<HistorieDerReiseObservation> historieDerReise) {
     this.historieDerReise = historieDerReise;
  }

  public List<HistorieDerReiseObservation> getHistorieDerReise() {
     return this.historieDerReise ;
  }

  public void setReisefall(List<ReisefallObservation> reisefall) {
     this.reisefall = reisefall;
  }

  public List<ReisefallObservation> getReisefall() {
     return this.reisefall ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
