package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:57:29.122798800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class EventsummaryIdDerPersonElement implements LocatableEntity {
  /**
   * Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/ID der Person
   * Description: *
   */
  @Path("/value")
  @Choice
  private List<EventsummaryIdDerPersonChoice> value;

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setValue(List<EventsummaryIdDerPersonChoice> value) {
     this.value = value;
  }

  public List<EventsummaryIdDerPersonChoice> getValue() {
     return this.value ;
  }
}
