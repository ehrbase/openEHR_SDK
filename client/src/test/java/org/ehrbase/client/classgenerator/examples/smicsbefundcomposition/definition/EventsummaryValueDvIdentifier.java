package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
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
@OptionFor("DV_IDENTIFIER")
public class EventsummaryValueDvIdentifier implements RMEntity, EventsummaryValueChoice {
  /** Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/value/value */
  @Path("")
  private DvIdentifier value;

  public void setValue(DvIdentifier value) {
    this.value = value;
  }

  public DvIdentifier getValue() {
    return this.value;
  }
}
