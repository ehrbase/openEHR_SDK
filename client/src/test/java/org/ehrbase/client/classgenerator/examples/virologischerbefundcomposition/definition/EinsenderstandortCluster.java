package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

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
    date = "2020-12-10T13:06:13.776036200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class EinsenderstandortCluster implements LocatableEntity {
  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Einsenderstandort/Standorttyp
   * Description: Kategorisierung des Standorttyps, z.B. Klinik, zu Hause.
   */
  @Path("/items[at0040]/value|value")
  private String standorttypValue;

  /**
   * Path: Virologischer Befund/Befund/Details der
   * Testanforderung/Einsenderstandort/Standortbeschreibung Description: Das Feld enthält die
   * Freitextbeschreibung des Standorts, z.B. Throax-, Herz- und Gefäßchirurgie.
   */
  @Path("/items[at0046]/value|value")
  private String standortbeschreibungValue;

  /**
   * Path: Virologischer Befund/Befund/Details der
   * Testanforderung/Einsenderstandort/Standortschlüssel Description: Kodierung des Standortes, z.B.
   * der Fachabteilungsschlüssel (z. B. 2000 Thoraxchirurgie).
   */
  @Path("/items[at0048]/value|value")
  private String standortschlusselValue;

  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Einsenderstandort/Details
   * Description: Für die Erfassung weiterer Angaben über das Bett oder der Adresse. Verwenden Sie
   * dazu den Archetyp CLUSTER.device oder CLUSTER.address.
   */
  @Path("/items[at0047]")
  private List<Cluster> details;

  /**
   * Path: Virologischer Befund/Befund/Details der Testanforderung/Einsenderstandort/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStandorttypValue(String standorttypValue) {
    this.standorttypValue = standorttypValue;
  }

  public String getStandorttypValue() {
    return this.standorttypValue;
  }

  public void setStandortbeschreibungValue(String standortbeschreibungValue) {
    this.standortbeschreibungValue = standortbeschreibungValue;
  }

  public String getStandortbeschreibungValue() {
    return this.standortbeschreibungValue;
  }

  public void setStandortschlusselValue(String standortschlusselValue) {
    this.standortschlusselValue = standortschlusselValue;
  }

  public String getStandortschlusselValue() {
    return this.standortschlusselValue;
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
