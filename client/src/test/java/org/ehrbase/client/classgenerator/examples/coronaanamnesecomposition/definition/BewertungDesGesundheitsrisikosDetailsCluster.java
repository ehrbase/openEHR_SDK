package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@OptionFor("CLUSTER")
public class BewertungDesGesundheitsrisikosDetailsCluster implements BewertungDesGesundheitsrisikosDetailsChoice {
    @Path("")
    private List<Cluster> details;

    public void setDetails(List<Cluster> details) {
        this.details = details;
    }

    public List<Cluster> getDetails() {
        return this.details;
    }
}
