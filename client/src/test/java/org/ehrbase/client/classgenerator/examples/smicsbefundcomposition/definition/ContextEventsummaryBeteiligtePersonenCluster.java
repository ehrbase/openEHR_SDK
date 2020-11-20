package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
public class ContextEventsummaryBeteiligtePersonenCluster {
    @Path("/items[at0010]")
    private List<ContextEventsummaryIdDerPersonElement> idDerPerson;

    @Path("/items[at0011]/value|value")
    private String artDerPersonValue;

    public void setIdDerPerson(List<ContextEventsummaryIdDerPersonElement> idDerPerson) {
        this.idDerPerson = idDerPerson;
    }

    public List<ContextEventsummaryIdDerPersonElement> getIdDerPerson() {
        return this.idDerPerson;
    }

    public void setArtDerPersonValue(String artDerPersonValue) {
        this.artDerPersonValue = artDerPersonValue;
    }

    public String getArtDerPersonValue() {
        return this.artDerPersonValue;
    }
}
