package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("POINT_EVENT")
public class BerichtBeliebigesEreignisPointEvent implements BerichtBeliebigesEreignisChoice {
  /**
   * Bericht/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen
   */
  @Path("/data[at0003]/items[at0022]")
  private List<BerichtSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen;

  /**
   * Bericht/Beliebiges Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Bericht/Beliebiges Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  public void setSpezifischesSymptomAnzeichen(
      List<BerichtSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen) {
     this.spezifischesSymptomAnzeichen = spezifischesSymptomAnzeichen;
  }

  public List<BerichtSpezifischesSymptomAnzeichenCluster> getSpezifischesSymptomAnzeichen() {
     return this.spezifischesSymptomAnzeichen ;
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
