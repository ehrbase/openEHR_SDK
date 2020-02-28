package org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@OptionFor("POINT_EVENT")
public class KorpergewichtAnyEventEnPointEvent implements KorpergewichtAnyEventEnChoice {
    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/data[at0001]/items[at0004]/value|magnitude")
    private Double gewichtMagnitude;

    @Path("/data[at0001]/items[at0004]/value|units")
    private String gewichtUnits;

    @Path("/state[at0008]/items[at0025]")
    private List<KorpergewichtConfoundingFactorsEnElement> confoundingFactorsEn;

    @Path("/data[at0001]/items[at0024]/value|value")
    private String commentEnValue;

    @Path("/state[at0008]/items[at0009]/value|defining_code")
    private StateOfDressEnDefiningcode stateOfDressEnDefiningcode;

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setGewichtMagnitude(Double gewichtMagnitude) {
        this.gewichtMagnitude = gewichtMagnitude;
    }

    public Double getGewichtMagnitude() {
        return this.gewichtMagnitude;
    }

    public void setGewichtUnits(String gewichtUnits) {
        this.gewichtUnits = gewichtUnits;
    }

    public String getGewichtUnits() {
        return this.gewichtUnits;
    }

    public void setConfoundingFactorsEn(
            List<KorpergewichtConfoundingFactorsEnElement> confoundingFactorsEn) {
        this.confoundingFactorsEn = confoundingFactorsEn;
    }

    public List<KorpergewichtConfoundingFactorsEnElement> getConfoundingFactorsEn() {
        return this.confoundingFactorsEn;
    }

    public void setCommentEnValue(String commentEnValue) {
        this.commentEnValue = commentEnValue;
    }

    public String getCommentEnValue() {
        return this.commentEnValue;
    }

    public void setStateOfDressEnDefiningcode(StateOfDressEnDefiningcode stateOfDressEnDefiningcode) {
        this.stateOfDressEnDefiningcode = stateOfDressEnDefiningcode;
    }

    public StateOfDressEnDefiningcode getStateOfDressEnDefiningcode() {
        return this.stateOfDressEnDefiningcode;
    }
}
