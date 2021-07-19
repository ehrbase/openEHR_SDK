package org.ehrbase.client.classgenerator.examples.minimalinstructionenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;

@Entity
@Archetype("openEHR-EHR-INSTRUCTION.minimal.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-07-16T10:53:22.321062+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class MinimalInstruction implements EntryEntity {
  /**
   * Path: Minimal/Minimal/Current Activity/duration
   * Description: *
   */
  @Path("/activities[at0001]/description[at0002]/items[at0003]/value|value")
  private TemporalAmount durationValue;

  /**
   * Path: Minimal/Minimal/Current Activity/Arbol/duration/null_flavour
   */
  @Path("/activities[at0001]/description[at0002]/items[at0003]/null_flavour|defining_code")
  private NullFlavour durationNullFlavourDefiningCode;

  /**
   * Path: Minimal/Minimal/Current Activity/timing
   */
  @Path("/activities[at0001]/timing")
  private DvParsable timing;

  /**
   * Path: Minimal/Minimal/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Minimal/Minimal/narrative
   */
  @Path("/narrative|value")
  private String narrativeValue;

  /**
   * Path: Minimal/Minimal/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Minimal/Minimal/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Minimal/Minimal/expiry_time
   */
  @Path("/expiry_time|value")
  private TemporalAccessor expiryTimeValue;

  public void setDurationValue(TemporalAmount durationValue) {
     this.durationValue = durationValue;
  }

  public TemporalAmount getDurationValue() {
     return this.durationValue ;
  }

  public void setDurationNullFlavourDefiningCode(NullFlavour durationNullFlavourDefiningCode) {
     this.durationNullFlavourDefiningCode = durationNullFlavourDefiningCode;
  }

  public NullFlavour getDurationNullFlavourDefiningCode() {
     return this.durationNullFlavourDefiningCode ;
  }

  public void setTiming(DvParsable timing) {
     this.timing = timing;
  }

  public DvParsable getTiming() {
     return this.timing ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setNarrativeValue(String narrativeValue) {
     this.narrativeValue = narrativeValue;
  }

  public String getNarrativeValue() {
     return this.narrativeValue ;
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

  public void setExpiryTimeValue(TemporalAccessor expiryTimeValue) {
     this.expiryTimeValue = expiryTimeValue;
  }

  public TemporalAccessor getExpiryTimeValue() {
     return this.expiryTimeValue ;
  }
}
