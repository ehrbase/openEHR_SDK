package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@OptionFor("CLUSTER")
public class Covid19ExposureDetailClusterCareHomeHasSuspectedConfirmedCovid192 implements Covid19ExposureDetailChoice {
    @Path("")
    private List<Cluster> detail;

    public void setDetail(List<Cluster> detail) {
        this.detail = detail;
    }

    public List<Cluster> getDetail() {
        return this.detail;
    }
}
