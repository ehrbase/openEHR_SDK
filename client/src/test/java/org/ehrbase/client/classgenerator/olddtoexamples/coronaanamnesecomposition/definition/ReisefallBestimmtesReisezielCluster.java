package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ReisefallBestimmtesReisezielCluster {
  @Path("/items[at0031]/value|value")
  private String bestimmteGegendValue;

  @Path("/items[at0024]")
  private List<Cluster> zusatzlicheAngabenZumZielort;

  @Path("/items[at0012]/value|value")
  private String bundeslandRegionValue;

  @Path("/items[at0011]/value|value")
  private String landValue;

  @Path("/items[at0013]/value|value")
  private String stadtValue;

  public void setBestimmteGegendValue(String bestimmteGegendValue) {
    this.bestimmteGegendValue = bestimmteGegendValue;
  }

  public String getBestimmteGegendValue() {
    return this.bestimmteGegendValue;
  }

  public void setZusatzlicheAngabenZumZielort(List<Cluster> zusatzlicheAngabenZumZielort) {
    this.zusatzlicheAngabenZumZielort = zusatzlicheAngabenZumZielort;
  }

  public List<Cluster> getZusatzlicheAngabenZumZielort() {
    return this.zusatzlicheAngabenZumZielort;
  }

  public void setBundeslandRegionValue(String bundeslandRegionValue) {
    this.bundeslandRegionValue = bundeslandRegionValue;
  }

  public String getBundeslandRegionValue() {
    return this.bundeslandRegionValue;
  }

  public void setLandValue(String landValue) {
    this.landValue = landValue;
  }

  public String getLandValue() {
    return this.landValue;
  }

  public void setStadtValue(String stadtValue) {
    this.stadtValue = stadtValue;
  }

  public String getStadtValue() {
    return this.stadtValue;
  }
}
