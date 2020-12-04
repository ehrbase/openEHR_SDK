package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import java.net.URI;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class EpisodeofcareTeamElement {
  /**
   * EpisodeOfCare/Episodeofcare/team
   */
  @Path("/value|value")
  private URI value;

  public void setValue(URI value) {
     this.value = value;
  }

  public URI getValue() {
     return this.value ;
  }
}
