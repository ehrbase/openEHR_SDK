package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.Double;
import java.lang.String;

import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class LuftsauerstoffFrequenzElement {
    @Path("/value|magnitude")
    private Double magnitude;

    @Path("/value|units")
    private String units;

    @Path("/name")
    private DvText name;


    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public Double getMagnitude() {
        return this.magnitude;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getUnits() {
        return units;
    }

    public DvText getName() {
        return name;
    }

    public void setName(DvText name) {
        this.name = name;
    }
}