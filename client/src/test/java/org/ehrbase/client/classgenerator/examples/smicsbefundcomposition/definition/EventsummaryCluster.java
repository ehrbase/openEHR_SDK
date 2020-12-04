package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.eventsummary.v0")
public class EventsummaryCluster {
  /**
   * SmICS Befund/context/Eventsummary/Fallidentifikation
   */
  @Path("/items[at0001 and name/value='Fallidentifikation']/value|value")
  private String fallidentifikationValue;

  /**
   * SmICS Befund/context/Eventsummary/Fall-Art
   */
  @Path("/items[at0002 and name/value='Fall-Art']/value|value")
  private String fallArtValue;

  /**
   * SmICS Befund/context/Eventsummary/Beteiligte Personen
   */
  @Path("/items[at0007]")
  private List<EventsummaryBeteiligtePersonenCluster> beteiligtePersonen;

  /**
   * SmICS Befund/context/Eventsummary/Fall-Kategorie
   */
  @Path("/items[at0004 and name/value='Fall-Kategorie']/value|value")
  private String fallKategorieValue;

  /**
   * SmICS Befund/context/Eventsummary/Kommentar
   */
  @Path("/items[at0006]/value|value")
  private String kommentarValue;

  public void setFallidentifikationValue(String fallidentifikationValue) {
     this.fallidentifikationValue = fallidentifikationValue;
  }

  public String getFallidentifikationValue() {
     return this.fallidentifikationValue ;
  }

  public void setFallArtValue(String fallArtValue) {
     this.fallArtValue = fallArtValue;
  }

  public String getFallArtValue() {
     return this.fallArtValue ;
  }

  public void setBeteiligtePersonen(
      List<EventsummaryBeteiligtePersonenCluster> beteiligtePersonen) {
     this.beteiligtePersonen = beteiligtePersonen;
  }

  public List<EventsummaryBeteiligtePersonenCluster> getBeteiligtePersonen() {
     return this.beteiligtePersonen ;
  }

  public void setFallKategorieValue(String fallKategorieValue) {
     this.fallKategorieValue = fallKategorieValue;
  }

  public String getFallKategorieValue() {
     return this.fallKategorieValue ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }
}
