package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.MathFunctionDefiningcode;

@Entity
@OptionFor("INTERVAL_EVENT")
public class ReisefallBeliebigesIntervallereignisIntervalEvent
    implements ReisefallBeliebigesIntervallereignisChoice {
  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0003]/items[at0004]/value|defining_code")
  private LetzteReiseDefiningcode letzteReiseDefiningcode;

  @Path("/math_function|defining_code")
  private MathFunctionDefiningcode mathFunctionDefiningcode;

  @Path("/data[at0003]/items[at0008]")
  private List<ReisefallBestimmteReiseCluster> bestimmteReise;

  @Path("/width|value")
  private TemporalAmount widthValue;

  @Path("/data[at0003]/items[at0026]/value|defining_code")
  private InlandAuslandDefiningcode inlandAuslandDefiningcode;

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setLetzteReiseDefiningcode(LetzteReiseDefiningcode letzteReiseDefiningcode) {
    this.letzteReiseDefiningcode = letzteReiseDefiningcode;
  }

  public LetzteReiseDefiningcode getLetzteReiseDefiningcode() {
    return this.letzteReiseDefiningcode;
  }

  public void setMathFunctionDefiningcode(MathFunctionDefiningcode mathFunctionDefiningcode) {
    this.mathFunctionDefiningcode = mathFunctionDefiningcode;
  }

  public MathFunctionDefiningcode getMathFunctionDefiningcode() {
    return this.mathFunctionDefiningcode;
  }

  public void setBestimmteReise(List<ReisefallBestimmteReiseCluster> bestimmteReise) {
    this.bestimmteReise = bestimmteReise;
  }

  public List<ReisefallBestimmteReiseCluster> getBestimmteReise() {
    return this.bestimmteReise;
  }

  public void setWidthValue(TemporalAmount widthValue) {
    this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
    return this.widthValue;
  }

  public void setInlandAuslandDefiningcode(InlandAuslandDefiningcode inlandAuslandDefiningcode) {
    this.inlandAuslandDefiningcode = inlandAuslandDefiningcode;
  }

  public InlandAuslandDefiningcode getInlandAuslandDefiningcode() {
    return this.inlandAuslandDefiningcode;
  }
}
