package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.generic.PartyProxy;
import java.net.URI;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.test_all_types.v1")
public class TestAllTypesEvaluation {
  @Path("/data[at0001]/items[at0004]/value")
  private DvInterval intervalQuantity;

  @Path("/data[at0001]/items[at0006]/items[at0007]/items[at0008]/items[at0010]/value|value")
  private String text2Value;

  @Path("/data[at0001]/items[at0003]/value")
  private DvInterval intervalCount;

  @Path("/data[at0001]/items[at0005]/value")
  private DvInterval intervalDatetime;

  @Path("/data[at0001]/items[at0002]/value|value")
  private URI uriValue;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[at0009]/value")
  @Choice
  private TestAllTypesChoiceChoice choice;

  @Path("/subject")
  private PartyProxy subject;

  public void setIntervalQuantity(DvInterval intervalQuantity) {
    this.intervalQuantity = intervalQuantity;
  }

  public DvInterval getIntervalQuantity() {
    return this.intervalQuantity;
  }

  public void setText2Value(String text2Value) {
    this.text2Value = text2Value;
  }

  public String getText2Value() {
    return this.text2Value;
  }

  public void setIntervalCount(DvInterval intervalCount) {
    this.intervalCount = intervalCount;
  }

  public DvInterval getIntervalCount() {
    return this.intervalCount;
  }

  public void setIntervalDatetime(DvInterval intervalDatetime) {
    this.intervalDatetime = intervalDatetime;
  }

  public DvInterval getIntervalDatetime() {
    return this.intervalDatetime;
  }

  public void setUriValue(URI uriValue) {
    this.uriValue = uriValue;
  }

  public URI getUriValue() {
    return this.uriValue;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setChoice(TestAllTypesChoiceChoice choice) {
    this.choice = choice;
  }

  public TestAllTypesChoiceChoice getChoice() {
    return this.choice;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
  }
}
