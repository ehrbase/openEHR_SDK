package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.net.URI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:51.340762+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class EpisodeofcareTeamElement implements LocatableEntity {
  /**
   * EpisodeOfCare/Episodeofcare/team
   */
  @Path("/value|value")
  private URI value;

  /**
   * EpisodeOfCare/Episodeofcare/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(URI value) {
     this.value = value;
  }

  public URI getValue() {
     return this.value ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
