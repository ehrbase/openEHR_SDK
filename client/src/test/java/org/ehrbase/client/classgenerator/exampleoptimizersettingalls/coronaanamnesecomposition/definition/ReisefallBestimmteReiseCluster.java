package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ReisefallBestimmteReiseCluster {
  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Bestimmtes Reiseziel
   */
  @Path("/items[at0010]")
  private List<ReisefallBestimmtesReisezielCluster> bestimmtesReiseziel;

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Zusätzliche Reisedetails
   */
  @Path("/items[at0025]")
  private List<Cluster> zusatzlicheReisedetails;

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/Rückreisedatum
   */
  @Path("/items[at0019]/value|value")
  private TemporalAccessor ruckreisedatumValue;

  public void setBestimmtesReiseziel(
      List<ReisefallBestimmtesReisezielCluster> bestimmtesReiseziel) {
     this.bestimmtesReiseziel = bestimmtesReiseziel;
  }

  public List<ReisefallBestimmtesReisezielCluster> getBestimmtesReiseziel() {
     return this.bestimmtesReiseziel ;
  }

  public void setZusatzlicheReisedetails(List<Cluster> zusatzlicheReisedetails) {
     this.zusatzlicheReisedetails = zusatzlicheReisedetails;
  }

  public List<Cluster> getZusatzlicheReisedetails() {
     return this.zusatzlicheReisedetails ;
  }

  public void setRuckreisedatumValue(TemporalAccessor ruckreisedatumValue) {
     this.ruckreisedatumValue = ruckreisedatumValue;
  }

  public TemporalAccessor getRuckreisedatumValue() {
     return this.ruckreisedatumValue ;
  }
}