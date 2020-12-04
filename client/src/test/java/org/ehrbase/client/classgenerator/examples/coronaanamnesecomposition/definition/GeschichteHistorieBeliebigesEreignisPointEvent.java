package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("POINT_EVENT")
public class GeschichteHistorieBeliebigesEreignisPointEvent implements GeschichteHistorieBeliebigesEreignisChoice {
  /**
   * Bericht/Geschichte/Historie/Beliebiges Ereignis/Geschichte
   */
  @Path("/data[at0003]/items[at0004]")
  private List<GeschichteHistorieGeschichteElement> geschichte;

  /**
   * Bericht/Geschichte/Historie/Beliebiges Ereignis/Strukturierte Angabe
   */
  @Path("/data[at0003]/items[at0006]")
  private List<Cluster> strukturierteAngabe;

  /**
   * Bericht/Geschichte/Historie/Beliebiges Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Bericht/Geschichte/Historie/Beliebiges Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  public void setGeschichte(List<GeschichteHistorieGeschichteElement> geschichte) {
     this.geschichte = geschichte;
  }

  public List<GeschichteHistorieGeschichteElement> getGeschichte() {
     return this.geschichte ;
  }

  public void setStrukturierteAngabe(List<Cluster> strukturierteAngabe) {
     this.strukturierteAngabe = strukturierteAngabe;
  }

  public List<Cluster> getStrukturierteAngabe() {
     return this.strukturierteAngabe ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }
}
