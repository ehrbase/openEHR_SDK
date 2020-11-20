package org.ehrbase.client.classgenerator.examples2.alternativeeventscomposition.definition;

import com.nedap.archie.rm.datatypes.CodePhrase;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;

@Entity
@OptionFor("INTERVAL_EVENT")
public class AnyEventEnIntervalEvent implements KorpergewichtAnyEventEnChoice {
  @Path("/data[at0001]/items[at0004]/value|magnitude")
  private Double gewichtMagnitude;

  @Path("/data[at0001]/items[at0004]/value|units")
  private String gewichtUnits;

  @Path("/data[at0001]/items[at0024]/value|value")
  private String commentEnValue;

  @Path("/state[at0008]/items[at0009]/value|defining_code")
  private StateOfDressEnDefiningcode stateOfDressEnDefiningcode;

  @Path("/state[at0008]/items[at0025]")
  private List<ConfoundingFactorsEnElement> confoundingFactorsEn;

  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/width|value")
  private TemporalAmount nullValue;

  @Path("/math_function|defining_code")
  private CodePhrase nullDefiningcode;

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

  public void setStateOfDressEnDefiningcode(StateOfDressEnDefiningcode stateOfDressEnDefiningcode) {
     this.stateOfDressEnDefiningcode = stateOfDressEnDefiningcode;
  }

  public StateOfDressEnDefiningcode getStateOfDressEnDefiningcode() {
     return this.stateOfDressEnDefiningcode ;
  }

  public void setConfoundingFactorsEn(List<ConfoundingFactorsEnElement> confoundingFactorsEn) {
     this.confoundingFactorsEn = confoundingFactorsEn;
  }

  public List<ConfoundingFactorsEnElement> getConfoundingFactorsEn() {
     return this.confoundingFactorsEn ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setNullValue(TemporalAmount nullValue) {
     this.nullValue = nullValue;
  }

  public TemporalAmount getNullValue() {
     return this.nullValue ;
  }

  public void setNullDefiningcode(CodePhrase nullDefiningcode) {
     this.nullDefiningcode = nullDefiningcode;
  }

  public CodePhrase getNullDefiningcode() {
     return this.nullDefiningcode ;
  }
}
