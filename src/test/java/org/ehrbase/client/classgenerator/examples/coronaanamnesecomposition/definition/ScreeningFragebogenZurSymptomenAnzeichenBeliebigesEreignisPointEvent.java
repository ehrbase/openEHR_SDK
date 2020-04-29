package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("POINT_EVENT")
public class ScreeningFragebogenZurSymptomenAnzeichenBeliebigesEreignisPointEvent implements ScreeningFragebogenZurSymptomenAnzeichenBeliebigesEreignisChoice {
  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0003]/items[at0022]")
  private List<ScreeningFragebogenZurSymptomenAnzeichenAnzeichenCluster> anzeichen;

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setAnzeichen(
      List<ScreeningFragebogenZurSymptomenAnzeichenAnzeichenCluster> anzeichen) {
     this.anzeichen = anzeichen;
  }

  public List<ScreeningFragebogenZurSymptomenAnzeichenAnzeichenCluster> getAnzeichen() {
     return this.anzeichen ;
  }
}
