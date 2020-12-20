package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.109023800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class EventsummaryIdDerPersonElement implements LocatableEntity {
  /** Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/value */
  @Path("/value")
  @Choice
  private EventsummaryValueChoice value;

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }

  public void setValue(EventsummaryValueChoice value) {
    this.value = value;
  }

  public EventsummaryValueChoice getValue() {
    return this.value;
  }
}
