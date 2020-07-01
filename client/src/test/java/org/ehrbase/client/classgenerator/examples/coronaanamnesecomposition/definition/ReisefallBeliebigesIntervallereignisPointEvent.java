package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@OptionFor("POINT_EVENT")
public class ReisefallBeliebigesIntervallereignisPointEvent implements ReisefallBeliebigesIntervallereignisChoice {
    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/data[at0003]/items[at0004]/name|value")
    private String reiseValue;

    @Path("/data[at0003]/items[at0004]/value|defining_code")
    private ReiseDefiningcode reiseDefiningcode;

    @Path("/data[at0003]/items[at0008]")
    private List<ReisefallBestimmteReiseCluster> bestimmteReise;

    @Path("/data[at0003]/items[at0026]/value|defining_code")
    private AuslandDefiningcode auslandDefiningcode;

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setReiseValue(String reiseValue) {
        this.reiseValue = reiseValue;
    }

    public String getReiseValue() {
        return this.reiseValue;
    }

    public void setReiseDefiningcode(ReiseDefiningcode reiseDefiningcode) {
        this.reiseDefiningcode = reiseDefiningcode;
    }

    public ReiseDefiningcode getReiseDefiningcode() {
        return this.reiseDefiningcode;
    }

    public void setBestimmteReise(List<ReisefallBestimmteReiseCluster> bestimmteReise) {
        this.bestimmteReise = bestimmteReise;
    }

    public List<ReisefallBestimmteReiseCluster> getBestimmteReise() {
        return this.bestimmteReise;
    }

    public void setAuslandDefiningcode(AuslandDefiningcode auslandDefiningcode) {
        this.auslandDefiningcode = auslandDefiningcode;
    }

    public AuslandDefiningcode getAuslandDefiningcode() {
        return this.auslandDefiningcode;
    }
}
