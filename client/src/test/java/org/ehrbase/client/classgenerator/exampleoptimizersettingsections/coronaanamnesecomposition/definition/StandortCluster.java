package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.434032200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class StandortCluster implements LocatableEntity {
  /**
   * Path: Bericht/Risikogebiet/Historie der Reise/Jedes
   * Ereignis/Ortsbeschreibung/Standort/Standortbeschreibung Description: Das Feld enthält die
   * Freitextbeschreibung des Standorts, z.B. Throax-, Herz- und Gefäßchirurgie.
   */
  @Path("/items[at0046]/value|value")
  private String standortbeschreibungValue;

  /**
   * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort/Details
   * Description: Für die Erfassung weiterer Angaben über das Bett oder der Adresse. Verwenden Sie
   * dazu den Archetyp CLUSTER.device oder CLUSTER.address.
   */
  @Path("/items[at0047]")
  private List<Cluster> details;

  /**
   * Path: Bericht/Risikogebiet/Historie der Reise/Jedes
   * Ereignis/Ortsbeschreibung/Standort/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStandortbeschreibungValue(String standortbeschreibungValue) {
    this.standortbeschreibungValue = standortbeschreibungValue;
  }

  public String getStandortbeschreibungValue() {
    return this.standortbeschreibungValue;
  }

  public void setDetails(List<Cluster> details) {
    this.details = details;
  }

  public List<Cluster> getDetails() {
    return this.details;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
