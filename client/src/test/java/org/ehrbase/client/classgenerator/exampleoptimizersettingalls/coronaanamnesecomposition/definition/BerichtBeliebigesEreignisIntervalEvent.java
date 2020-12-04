package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;

@Entity
@OptionFor("INTERVAL_EVENT")
public class BerichtBeliebigesEreignisIntervalEvent implements BerichtBeliebigesEreignisChoice {
  /**
   * Bericht/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen
   */
  @Path("/data[at0003]/items[at0022]")
  private List<BerichtSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen;

  /**
   * Bericht/Beliebiges Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Bericht/Beliebiges Ereignis/width
   */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /**
   * Bericht/Beliebiges Ereignis/math_function
   */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  public void setSpezifischesSymptomAnzeichen(
      List<BerichtSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen) {
     this.spezifischesSymptomAnzeichen = spezifischesSymptomAnzeichen;
  }

  public List<BerichtSpezifischesSymptomAnzeichenCluster> getSpezifischesSymptomAnzeichen() {
     return this.spezifischesSymptomAnzeichen ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setWidthValue(TemporalAmount widthValue) {
     this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
     return this.widthValue ;
  }

  public void setMathFunctionDefiningCode(MathFunction mathFunctionDefiningCode) {
     this.mathFunctionDefiningCode = mathFunctionDefiningCode;
  }

  public MathFunction getMathFunctionDefiningCode() {
     return this.mathFunctionDefiningCode ;
  }
}
