package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.etiology.v1")
public class AtiopathogeneseCluster implements LocatableEntity {
  /**
   * COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/Ätiologie der Krankheit
   */
  @Path("/items[at0001]")
  private List<AtiopathogeneseAtiologieDerKrankheitElement> atiologieDerKrankheit;

  /**
   * COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/Beschreibung des Entstehens
   */
  @Path("/items[at0017]")
  private List<AtiopathogeneseBeschreibungDesEntstehensElement> beschreibungDesEntstehens;

  /**
   * COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAtiologieDerKrankheit(
      List<AtiopathogeneseAtiologieDerKrankheitElement> atiologieDerKrankheit) {
     this.atiologieDerKrankheit = atiologieDerKrankheit;
  }

  public List<AtiopathogeneseAtiologieDerKrankheitElement> getAtiologieDerKrankheit() {
     return this.atiologieDerKrankheit ;
  }

  public void setBeschreibungDesEntstehens(
      List<AtiopathogeneseBeschreibungDesEntstehensElement> beschreibungDesEntstehens) {
     this.beschreibungDesEntstehens = beschreibungDesEntstehens;
  }

  public List<AtiopathogeneseBeschreibungDesEntstehensElement> getBeschreibungDesEntstehens() {
     return this.beschreibungDesEntstehens ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
