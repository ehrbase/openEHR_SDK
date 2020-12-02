package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class EventsummaryBeteiligtePersonenCluster {
  @Path("/items[at0011]/value|value")
  private String artDerPersonValue;

  @Path("/items[at0010]")
  private List<EventsummaryIdDerPersonElement> idDerPerson;

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
}
