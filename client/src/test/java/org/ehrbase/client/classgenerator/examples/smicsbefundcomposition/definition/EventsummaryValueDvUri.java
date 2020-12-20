package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import java.net.URI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.109023800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_URI")
public class EventsummaryValueDvUri implements RMEntity, EventsummaryValueChoice {
  /** Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/value/value */
  @Path("|value")
  private URI valueValue;

  public void setValueValue(URI valueValue) {
    this.valueValue = valueValue;
  }

  public URI getValueValue() {
    return this.valueValue;
  }
}
