package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;

@Entity
public class ReisefallBeliebigesIntervallereignisIntervalEvent {
  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Letzte Reise?
   */
  @Path("/data[at0003]/items[at0004 and name/value='Letzte Reise?']/value|defining_code")
  private LetzteReiseDefiningCode letzteReiseDefiningCode;

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Inland/Ausland
   */
  @Path("/data[at0003]/items[at0026]/value|defining_code")
  private InlandAuslandDefiningCode inlandAuslandDefiningCode;

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise
   */
  @Path("/data[at0003]/items[at0008]")
  private List<ReisefallBestimmteReiseCluster> bestimmteReise;

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/math_function
   */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/width
   */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/time
   */
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
