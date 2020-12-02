package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;

@Entity
@OptionFor("INTERVAL_EVENT")
public class GeschichteHistorieBeliebigesEreignisIntervalEvent implements GeschichteHistorieBeliebigesEreignisChoice {
  @Path("/data[at0003]/items[at0004]")
  private List<GeschichteHistorieGeschichteElement> geschichte;

  @Path("/data[at0003]/items[at0006]")
  private List<Cluster> strukturierteAngabe;

  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/width|value")
  private TemporalAmount widthValue;

  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

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
