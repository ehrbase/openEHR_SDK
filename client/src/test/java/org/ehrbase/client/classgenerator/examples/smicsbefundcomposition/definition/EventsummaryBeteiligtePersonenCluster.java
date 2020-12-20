package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.109023800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class EventsummaryBeteiligtePersonenCluster implements LocatableEntity {
  /** Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/Art der Person Description: * */
  @Path("/items[at0011]/value|value")
  private String artDerPersonValue;

  /** Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/ID der Person Description: * */
  @Path("/items[at0010]")
  private List<EventsummaryIdDerPersonElement> idDerPerson;

  /** Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setArtDerPersonValue(String artDerPersonValue) {
    this.artDerPersonValue = artDerPersonValue;
  }

  public String getArtDerPersonValue() {
    return this.artDerPersonValue;
  }

  public void setIdDerPerson(List<EventsummaryIdDerPersonElement> idDerPerson) {
    this.idDerPerson = idDerPerson;
  }

  public List<EventsummaryIdDerPersonElement> getIdDerPerson() {
    return this.idDerPerson;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
