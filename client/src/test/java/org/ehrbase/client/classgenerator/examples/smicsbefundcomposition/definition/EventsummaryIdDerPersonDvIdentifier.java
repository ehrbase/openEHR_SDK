package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:57:29.126799900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_IDENTIFIER")
public class EventsummaryIdDerPersonDvIdentifier implements RMEntity, EventsummaryIdDerPersonChoice {
  /**
   * Path: SmICS Befund/context/Eventsummary/Beteiligte Personen/ID der Person/ID der Person
   * Description: *
   */
  @Path("")
  private List<DvIdentifier> idDerPerson;

  public void setIdDerPerson(List<DvIdentifier> idDerPerson) {
     this.idDerPerson = idDerPerson;
  }

  public List<DvIdentifier> getIdDerPerson() {
     return this.idDerPerson ;
  }
}
