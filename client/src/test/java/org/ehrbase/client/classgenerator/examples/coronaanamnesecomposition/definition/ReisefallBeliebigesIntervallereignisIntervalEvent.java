package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;

@Entity
public class ReisefallBeliebigesIntervallereignisIntervalEvent {
  @Path("/data[at0003]/items[at0004 and name/value='Letzte Reise?']/value|defining_code")
  private LetzteReiseDefiningCode letzteReiseDefiningCode;

  @Path("/data[at0003]/items[at0026]/value|defining_code")
  private InlandAuslandDefiningCode inlandAuslandDefiningCode;

  @Path("/data[at0003]/items[at0008]")
  private List<ReisefallBestimmteReiseCluster> bestimmteReise;

  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  @Path("/width|value")
  private TemporalAmount widthValue;

  @Path("/time|value")
  private TemporalAccessor timeValue;

  public void setLetzteReiseDefiningCode(LetzteReiseDefiningCode letzteReiseDefiningCode) {
     this.letzteReiseDefiningCode = letzteReiseDefiningCode;
  }

  public LetzteReiseDefiningCode getLetzteReiseDefiningCode() {
     return this.letzteReiseDefiningCode ;
  }

  public void setInlandAuslandDefiningCode(InlandAuslandDefiningCode inlandAuslandDefiningCode) {
     this.inlandAuslandDefiningCode = inlandAuslandDefiningCode;
  }

  public InlandAuslandDefiningCode getInlandAuslandDefiningCode() {
     return this.inlandAuslandDefiningCode ;
  }

  public void setBestimmteReise(List<ReisefallBestimmteReiseCluster> bestimmteReise) {
     this.bestimmteReise = bestimmteReise;
  }

  public List<ReisefallBestimmteReiseCluster> getBestimmteReise() {
     return this.bestimmteReise ;
  }

  public void setMathFunctionDefiningCode(MathFunction mathFunctionDefiningCode) {
     this.mathFunctionDefiningCode = mathFunctionDefiningCode;
  }

  public MathFunction getMathFunctionDefiningCode() {
     return this.mathFunctionDefiningCode ;
  }

  public void setWidthValue(TemporalAmount widthValue) {
     this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
     return this.widthValue ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }
}
