package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ReisefallBestimmtesReisezielCluster {
  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel/Land
   */
  @Path("/items[at0011]/value|value")
  private String landValue;

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel/Bundesland / Region
   */
  @Path("/items[at0012]/value|value")
  private String bundeslandRegionValue;

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel/Stadt
   */
  @Path("/items[at0013]/value|value")
  private String stadtValue;

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel/Bestimmte Gegend
   */
  @Path("/items[at0031]/value|value")
  private String bestimmteGegendValue;

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel/Zusätzliche Angaben zum Zielort
   */
  @Path("/items[at0024]")
  private List<Cluster> zusatzlicheAngabenZumZielort;

  public void setLandValue(String landValue) {
     this.landValue = landValue;
  }

  public String getLandValue() {
     return this.landValue ;
  }

  public void setBundeslandRegionValue(String bundeslandRegionValue) {
     this.bundeslandRegionValue = bundeslandRegionValue;
  }

  public String getBundeslandRegionValue() {
     return this.bundeslandRegionValue ;
  }

  public void setStadtValue(String stadtValue) {
     this.stadtValue = stadtValue;
  }

  public String getStadtValue() {
     return this.stadtValue ;
  }

  public void setBestimmteGegendValue(String bestimmteGegendValue) {
     this.bestimmteGegendValue = bestimmteGegendValue;
  }

  public String getBestimmteGegendValue() {
     return this.bestimmteGegendValue ;
  }

  public void setZusatzlicheAngabenZumZielort(List<Cluster> zusatzlicheAngabenZumZielort) {
     this.zusatzlicheAngabenZumZielort = zusatzlicheAngabenZumZielort;
  }

  public List<Cluster> getZusatzlicheAngabenZumZielort() {
     return this.zusatzlicheAngabenZumZielort ;
  }
}
