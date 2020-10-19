package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.eventsummary.v0")
public class EventsummaryCluster {
    @Path("/items[at0006]/value|value")
    private String kommentarValue;

    @Path("/items[at0004]/value|value")
    private String fallKategorieValue;

    @Path("/items[at0001]/value|value")
    private String fallidentifikationValue;

    @Path("/items[at0002]/value|value")
    private String fallArtValue;

    @Path("/items[at0007]")
    private List<ContextEventsummaryBeteiligtePersonenCluster> beteiligtePersonen;

    public void setKommentarValue(String kommentarValue) {
        this.kommentarValue = kommentarValue;
    }

    public String getKommentarValue() {
        return this.kommentarValue;
    }

    public void setFallKategorieValue(String fallKategorieValue) {
        this.fallKategorieValue = fallKategorieValue;
    }

    public String getFallKategorieValue() {
        return this.fallKategorieValue;
    }

    public void setFallidentifikationValue(String fallidentifikationValue) {
        this.fallidentifikationValue = fallidentifikationValue;
    }

    public String getFallidentifikationValue() {
        return this.fallidentifikationValue;
    }

    public void setFallArtValue(String fallArtValue) {
        this.fallArtValue = fallArtValue;
    }

    public String getFallArtValue() {
        return this.fallArtValue;
    }

    public void setBeteiligtePersonen(
            List<ContextEventsummaryBeteiligtePersonenCluster> beteiligtePersonen) {
        this.beteiligtePersonen = beteiligtePersonen;
    }

    public List<ContextEventsummaryBeteiligtePersonenCluster> getBeteiligtePersonen() {
        return this.beteiligtePersonen;
    }
}
