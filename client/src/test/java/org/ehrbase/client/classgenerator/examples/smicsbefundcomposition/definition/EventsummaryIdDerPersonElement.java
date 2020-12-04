package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class EventsummaryIdDerPersonElement {
  /**
   * SmICS Befund/context/Eventsummary/Beteiligte Personen/value
   */
  @Path("/value")
  @Choice
  private EventsummaryValueChoice value;

  public void setValue(EventsummaryValueChoice value) {
     this.value = value;
  }

  public EventsummaryValueChoice getValue() {
     return this.value ;
  }
}
