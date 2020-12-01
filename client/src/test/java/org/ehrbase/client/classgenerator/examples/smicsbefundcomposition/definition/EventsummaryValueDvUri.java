package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

import java.net.URI;

@Entity
@OptionFor("DV_URI")
public class EventsummaryValueDvUri implements EventsummaryValueChoice {
  @Path("|value")
  private URI valueValue;

  public void setValueValue(URI valueValue) {
     this.valueValue = valueValue;
  }

  public URI getValueValue() {
     return this.valueValue ;
  }
}
