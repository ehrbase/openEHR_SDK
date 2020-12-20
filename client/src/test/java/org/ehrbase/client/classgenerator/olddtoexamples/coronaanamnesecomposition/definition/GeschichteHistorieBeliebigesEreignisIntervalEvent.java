package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.MathFunctionDefiningcode;

@Entity
@OptionFor("INTERVAL_EVENT")
public class GeschichteHistorieBeliebigesEreignisIntervalEvent
    implements GeschichteHistorieBeliebigesEreignisChoice {
  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0003]/items[at0004]")
  private List<GeschichteHistorieGeschichteElement> geschichte;

  @Path("/math_function|defining_code")
  private MathFunctionDefiningcode mathFunctionDefiningcode;

  @Path("/width|value")
  private TemporalAmount widthValue;

  @Path("/data[at0003]/items[at0006]")
  private List<Cluster> strukturierteAngabe;

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setGeschichte(List<GeschichteHistorieGeschichteElement> geschichte) {
    this.geschichte = geschichte;
  }

  public List<GeschichteHistorieGeschichteElement> getGeschichte() {
    return this.geschichte;
  }

  public void setMathFunctionDefiningcode(MathFunctionDefiningcode mathFunctionDefiningcode) {
    this.mathFunctionDefiningcode = mathFunctionDefiningcode;
  }

  public MathFunctionDefiningcode getMathFunctionDefiningcode() {
    return this.mathFunctionDefiningcode;
  }

  public void setWidthValue(TemporalAmount widthValue) {
    this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
    return this.widthValue;
  }

  public void setStrukturierteAngabe(List<Cluster> strukturierteAngabe) {
    this.strukturierteAngabe = strukturierteAngabe;
  }

  public List<Cluster> getStrukturierteAngabe() {
    return this.strukturierteAngabe;
  }
}
