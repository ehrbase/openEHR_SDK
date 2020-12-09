package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:53.402287900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class ReisefallBestimmteReiseCluster implements LocatableEntity {
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

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

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

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
