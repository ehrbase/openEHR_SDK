package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class LaboranalytResultatAnzahlDerErregernachweiseElement {
    @Path("/value|magnitude")
    private Long magnitude;

    public void setMagnitude(Long magnitude) {
        this.magnitude = magnitude;
    }

    public Long getMagnitude() {
        return this.magnitude;
    }
}
