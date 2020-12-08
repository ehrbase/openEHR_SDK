package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.story.v1")
public class StoryHistoryObservation implements EntryEntity {
  /**
   * open_eREACT-Care/Situation/Story/History/Any event/Soft signs
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004 and name/value='Soft signs']")
  private List<StoryHistorySoftSignsElement> softSigns;

  /**
   * open_eREACT-Care/Situation/Story/History/Any event/Notes
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004 and name/value='Notes']/value|value")
  private String notesValue;

  /**
   * open_eREACT-Care/Situation/Story/History/Any event/Structured detail
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]")
  private List<Cluster> structuredDetail;

  /**
   * open_eREACT-Care/Situation/Story/History/Any event/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * open_eREACT-Care/Situation/Story/History/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * open_eREACT-Care/Situation/Story/History/Extension
   */
  @Path("/protocol[at0007]/items[at0008]")
  private List<Cluster> extension;

  /**
   * open_eREACT-Care/Situation/Story/History/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * open_eREACT-Care/Situation/Story/History/language
   */
  @Path("/language")
  private Language language;

  /**
   * open_eREACT-Care/Situation/Story/History/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

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

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
