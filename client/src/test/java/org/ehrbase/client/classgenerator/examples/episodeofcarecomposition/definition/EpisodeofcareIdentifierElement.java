package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.181498500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class EpisodeofcareIdentifierElement implements LocatableEntity {
  /**
   * Path: EpisodeOfCare/Episodeofcare/identifier Description: Business Identifier(s) relevant for
   * this EpisodeOfCare
   */
  @Path("/value")
  private DvIdentifier value;

  /** Path: EpisodeOfCare/Episodeofcare/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(DvIdentifier value) {
    this.value = value;
  }

  public DvIdentifier getValue() {
    return this.value;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
