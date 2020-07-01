package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
public class ReisefallBestimmtesReisezielCluster2 {
    @Path("/items[at0031]/value|value")
    private String bestimmteGegendValue;

    @Path("/items[at0024]")
    private List<Cluster> zusatzlicheAngabenZumZielort;

    @Path("/items[at0012]/value|value")
    private String regionValue;

    @Path("/items[at0011]/value|value")
    private String landValue;

    @Path("/items[at0013]/value|value")
    private String stadtValue;

    public void setBestimmteGegendValue(String bestimmteGegendValue) {
        this.bestimmteGegendValue = bestimmteGegendValue;
    }

    public String getBestimmteGegendValue() {
        return this.bestimmteGegendValue;
    }

    public void setZusatzlicheAngabenZumZielort(List<Cluster> zusatzlicheAngabenZumZielort) {
        this.zusatzlicheAngabenZumZielort = zusatzlicheAngabenZumZielort;
    }

    public List<Cluster> getZusatzlicheAngabenZumZielort() {
        return this.zusatzlicheAngabenZumZielort;
    }

    public void setRegionValue(String regionValue) {
        this.regionValue = regionValue;
    }

    public String getRegionValue() {
        return this.regionValue;
    }

    public void setLandValue(String landValue) {
        this.landValue = landValue;
    }

    public String getLandValue() {
        return this.landValue;
    }

    public void setStadtValue(String stadtValue) {
        this.stadtValue = stadtValue;
    }

    public String getStadtValue() {
        return this.stadtValue;
    }
}
