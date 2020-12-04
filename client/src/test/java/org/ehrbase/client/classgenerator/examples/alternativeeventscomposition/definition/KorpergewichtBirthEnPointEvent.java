package org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition;

import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class KorpergewichtBirthEnPointEvent {
  /**
   * Bericht/Körpergewicht/*Birth(en)/Gewicht
   */
  @Path("/data[at0001]/items[at0004]/value|magnitude")
  private Double gewichtMagnitude;

  /**
   * Bericht/Körpergewicht/*Birth(en)/Gewicht
   */
  @Path("/data[at0001]/items[at0004]/value|units")
  private String gewichtUnits;

  /**
   * Bericht/Körpergewicht/*Birth(en)/*Comment(en)
   */
  @Path("/data[at0001]/items[at0024]/value|value")
  private String commentEnValue;

  /**
   * Bericht/Körpergewicht/*Birth(en)/*State of dress(en)
   */
  @Path("/state[at0008]/items[at0009]/value|defining_code")
  private StateOfDressEnDefiningCode stateOfDressEnDefiningCode;

  /**
   * Bericht/Körpergewicht/*Birth(en)/*Confounding factors(en)
   */
  @Path("/state[at0008]/items[at0025]")
  private List<KorpergewichtConfoundingFactorsEnElement> confoundingFactorsEn;

  /**
   * Bericht/Körpergewicht/*Birth(en)/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  public void setGewichtMagnitude(Double gewichtMagnitude) {
     this.gewichtMagnitude = gewichtMagnitude;
  }

  public Double getGewichtMagnitude() {
     return this.gewichtMagnitude ;
  }

  public void setGewichtUnits(String gewichtUnits) {
     this.gewichtUnits = gewichtUnits;
  }

  public String getGewichtUnits() {
     return this.gewichtUnits ;
  }

  public void setCommentEnValue(String commentEnValue) {
     this.commentEnValue = commentEnValue;
  }

  public String getCommentEnValue() {
     return this.commentEnValue ;
  }

  public void setStateOfDressEnDefiningCode(StateOfDressEnDefiningCode stateOfDressEnDefiningCode) {
     this.stateOfDressEnDefiningCode = stateOfDressEnDefiningCode;
  }

  public StateOfDressEnDefiningCode getStateOfDressEnDefiningCode() {
     return this.stateOfDressEnDefiningCode ;
  }

  public void setConfoundingFactorsEn(
      List<KorpergewichtConfoundingFactorsEnElement> confoundingFactorsEn) {
     this.confoundingFactorsEn = confoundingFactorsEn;
  }

  public List<KorpergewichtConfoundingFactorsEnElement> getConfoundingFactorsEn() {
     return this.confoundingFactorsEn ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }
}