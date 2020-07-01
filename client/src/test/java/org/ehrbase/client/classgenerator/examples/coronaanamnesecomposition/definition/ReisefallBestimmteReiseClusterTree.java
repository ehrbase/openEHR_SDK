package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
public class ReisefallBestimmteReiseClusterTree {
    @Path("/items[at0010]")
    private List<ReisefallBestimmtesReisezielCluster2> bestimmtesReiseziel;

    @Path("/items[at0025]")
    private List<Cluster> zusatzlicheReisedetails;

    @Path("/items[at0019]/value|value")
    private TemporalAccessor ruckreisedatumValue;

    public void setBestimmtesReiseziel(
            List<ReisefallBestimmtesReisezielCluster2> bestimmtesReiseziel) {
        this.bestimmtesReiseziel = bestimmtesReiseziel;
    }

    public List<ReisefallBestimmtesReisezielCluster2> getBestimmtesReiseziel() {
        return this.bestimmtesReiseziel;
    }

    public void setZusatzlicheReisedetails(List<Cluster> zusatzlicheReisedetails) {
        this.zusatzlicheReisedetails = zusatzlicheReisedetails;
    }

    public List<Cluster> getZusatzlicheReisedetails() {
        return this.zusatzlicheReisedetails;
    }

    public void setRuckreisedatumValue(TemporalAccessor ruckreisedatumValue) {
        this.ruckreisedatumValue = ruckreisedatumValue;
    }

    public TemporalAccessor getRuckreisedatumValue() {
        return this.ruckreisedatumValue;
    }
}
