package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class EventsummaryBeteiligtePersonenCluster {
  /**
   * SmICS Befund/context/Eventsummary/Beteiligte Personen/Art der Person
   */
  @Path("/items[at0011]/value|value")
  private String artDerPersonValue;

  /**
   * SmICS Befund/context/Eventsummary/Beteiligte Personen/ID der Person
   */
  @Path("/items[at0010]")
  private List<EventsummaryIdDerPersonElement> idDerPerson;

  /**
   * SmICS Befund/context/Eventsummary/Beteiligte Personen/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setArtDerPersonValue(String artDerPersonValue) {
     this.artDerPersonValue = artDerPersonValue;
  }

  public String getArtDerPersonValue() {
     return this.artDerPersonValue ;
  }

  public void setIdDerPerson(List<EventsummaryIdDerPersonElement> idDerPerson) {
     this.idDerPerson = idDerPerson;
  }

  public List<EventsummaryIdDerPersonElement> getIdDerPerson() {
     return this.idDerPerson ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
