package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.MathFunctionDefiningcode;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;

@Entity
@OptionFor("INTERVAL_EVENT")
public class ReisefallBeliebigesIntervallereignisIntervalEvent implements ReisefallBeliebigesIntervallereignisChoice {
    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/data[at0003]/items[at0004]/value|defining_code")
    private ReiseDefiningcode reiseDefiningcode;

    @Path("/data[at0003]/items[at0008]")
    private List<ReisefallBestimmteReiseClusterTree> bestimmteReise;

    @Path("/width|value")
    private TemporalAmount widthValue;

    @Path("/data[at0003]/items[at0004]/name|value")
    private String reiseValue;

    @Path("/math_function|defining_code")
    private MathFunctionDefiningcode mathFunctionDefiningcode;

    @Path("/data[at0003]/items[at0026]/value|defining_code")
    private AuslandDefiningcode auslandDefiningcode;

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setReiseDefiningcode(ReiseDefiningcode reiseDefiningcode) {
        this.reiseDefiningcode = reiseDefiningcode;
    }

    public ReiseDefiningcode getReiseDefiningcode() {
        return this.reiseDefiningcode;
    }

    public void setBestimmteReise(List<ReisefallBestimmteReiseClusterTree> bestimmteReise) {
        this.bestimmteReise = bestimmteReise;
    }

    public List<ReisefallBestimmteReiseClusterTree> getBestimmteReise() {
        return this.bestimmteReise;
    }

    public void setWidthValue(TemporalAmount widthValue) {
        this.widthValue = widthValue;
    }

    public TemporalAmount getWidthValue() {
        return this.widthValue;
    }

    public void setReiseValue(String reiseValue) {
        this.reiseValue = reiseValue;
    }

    public String getReiseValue() {
        return this.reiseValue;
    }

    public void setMathFunctionDefiningcode(MathFunctionDefiningcode mathFunctionDefiningcode) {
        this.mathFunctionDefiningcode = mathFunctionDefiningcode;
    }

    public MathFunctionDefiningcode getMathFunctionDefiningcode() {
        return this.mathFunctionDefiningcode;
    }

    public void setAuslandDefiningcode(AuslandDefiningcode auslandDefiningcode) {
        this.auslandDefiningcode = auslandDefiningcode;
    }

    public AuslandDefiningcode getAuslandDefiningcode() {
        return this.auslandDefiningcode;
    }
}
