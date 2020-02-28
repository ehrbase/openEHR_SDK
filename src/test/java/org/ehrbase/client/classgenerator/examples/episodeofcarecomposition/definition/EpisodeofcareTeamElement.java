package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.net.URI;

@Entity
public class EpisodeofcareTeamElement {
    @Path("/value|value")
    private URI value;

    public void setValue(URI value) {
        this.value = value;
    }

    public URI getValue() {
        return this.value;
    }
}
