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
    date = "2021-02-16T12:57:29.130799500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_URI")
public class EventsummaryIdDerPersonDvUri implements RMEntity, EventsummaryIdDerPersonChoice {
  /**
   * Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/ID der Person/ID der Person
   * Description: *
   */
  @Path("|value")
  private URI idDerPersonValue;

  public void setIdDerPersonValue(URI idDerPersonValue) {
     this.idDerPersonValue = idDerPersonValue;
  }

  public URI getIdDerPersonValue() {
     return this.idDerPersonValue ;
  }
}
