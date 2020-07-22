package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
public class FallidentifikationCluster {
    @Path("/items[at0001]/value|value")
    private String fallKennungValue;

    public void setFallKennungValue(String fallKennungValue) {
        this.fallKennungValue = fallKennungValue;
    }

    public String getFallKennungValue() {
        return this.fallKennungValue;
    }
}
