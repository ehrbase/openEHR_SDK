package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("POINT_EVENT")
public class WeitereSymptomeBeliebigesEreignisPointEvent
    implements WeitereSymptomeBeliebigesEreignisChoice {
  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0003]/items[at0022]")
  private List<WeitereSymptomeSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen;

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setSpezifischesSymptomAnzeichen(
      List<WeitereSymptomeSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen) {
    this.spezifischesSymptomAnzeichen = spezifischesSymptomAnzeichen;
  }

  public List<WeitereSymptomeSpezifischesSymptomAnzeichenCluster>
      getSpezifischesSymptomAnzeichen() {
    return this.spezifischesSymptomAnzeichen;
  }
}
