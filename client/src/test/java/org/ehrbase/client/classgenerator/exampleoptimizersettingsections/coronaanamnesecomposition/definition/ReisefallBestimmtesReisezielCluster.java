package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.445036300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ReisefallBestimmtesReisezielCluster implements LocatableEntity {
  /**
   * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes
   * Reiseziel/Land Description: Das besuchte Land.
   */
  @Path("/items[at0011]/value|value")
  private String landValue;

  /**
   * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes
   * Reiseziel/Bundesland / Region Description: Die besuchte Region.
   */
  @Path("/items[at0012]/value|value")
  private String bundeslandRegionValue;

  /**
   * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes
   * Reiseziel/Stadt Description: Die besuchte Stadt.
   */
  @Path("/items[at0013]/value|value")
  private String stadtValue;

  /**
   * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes
   * Reiseziel/Bestimmte Gegend Description: Eine bestimmte Gegend, die besucht wurde.
   */
  @Path("/items[at0031]/value|value")
  private String bestimmteGegendValue;

  /**
   * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes
   * Reiseziel/Zusätzliche Angaben zum Zielort Description: Zusätzliche strukturierte Details zur
   * Reise nach, von und am Zielort.
   */
  @Path("/items[at0024]")
  private List<Cluster> zusaetzlicheAngabenZumZielort;

  /**
   * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes
   * Reiseziel/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setLandValue(String landValue) {
    this.landValue = landValue;
  }

  public String getLandValue() {
    return this.landValue;
  }

  public void setBundeslandRegionValue(String bundeslandRegionValue) {
    this.bundeslandRegionValue = bundeslandRegionValue;
  }

  public String getBundeslandRegionValue() {
    return this.bundeslandRegionValue;
  }

  public void setStadtValue(String stadtValue) {
    this.stadtValue = stadtValue;
  }

  public String getStadtValue() {
    return this.stadtValue;
  }

  public void setBestimmteGegendValue(String bestimmteGegendValue) {
    this.bestimmteGegendValue = bestimmteGegendValue;
  }

  public String getBestimmteGegendValue() {
    return this.bestimmteGegendValue;
  }

  public void setZusaetzlicheAngabenZumZielort(List<Cluster> zusaetzlicheAngabenZumZielort) {
    this.zusaetzlicheAngabenZumZielort = zusaetzlicheAngabenZumZielort;
  }

  public List<Cluster> getZusaetzlicheAngabenZumZielort() {
    return this.zusaetzlicheAngabenZumZielort;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
