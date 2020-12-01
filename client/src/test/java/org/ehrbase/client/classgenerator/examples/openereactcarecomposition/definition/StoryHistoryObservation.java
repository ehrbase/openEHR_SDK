package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.story.v1")
public class StoryHistoryObservation {
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004 and name/value='Soft signs']")
  private List<StoryHistorySoftSignsElement> softSigns;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004 and name/value='Notes']/value|value")
  private String notesValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]")
  private List<Cluster> structuredDetail;

  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  @Path("/protocol[at0007]/items[at0008]")
  private List<Cluster> extension;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setSoftSigns(List<StoryHistorySoftSignsElement> softSigns) {
     this.softSigns = softSigns;
  }

  public List<StoryHistorySoftSignsElement> getSoftSigns() {
     return this.softSigns ;
  }

  public void setNotesValue(String notesValue) {
     this.notesValue = notesValue;
  }

  public String getNotesValue() {
     return this.notesValue ;
  }

  public void setStructuredDetail(List<Cluster> structuredDetail) {
     this.structuredDetail = structuredDetail;
  }

  public List<Cluster> getStructuredDetail() {
     return this.structuredDetail ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }
}
