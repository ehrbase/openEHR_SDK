package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("POINT_EVENT")
public class ReisefallBeliebigesIntervallereignisPointEvent
    implements ReisefallBeliebigesIntervallereignisChoice {
  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0003]/items[at0004]/value|defining_code")
  private LetzteReiseDefiningcode letzteReiseDefiningcode;

  @Path("/data[at0003]/items[at0008]")
  private List<ReisefallBestimmteReiseCluster> bestimmteReise;

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

  public void setBestimmteReise(List<ReisefallBestimmteReiseCluster> bestimmteReise) {
    this.bestimmteReise = bestimmteReise;
  }

  public List<ReisefallBestimmteReiseCluster> getBestimmteReise() {
    return this.bestimmteReise;
  }

  public void setInlandAuslandDefiningcode(InlandAuslandDefiningcode inlandAuslandDefiningcode) {
    this.inlandAuslandDefiningcode = inlandAuslandDefiningcode;
  }

  public InlandAuslandDefiningcode getInlandAuslandDefiningcode() {
    return this.inlandAuslandDefiningcode;
  }
}
