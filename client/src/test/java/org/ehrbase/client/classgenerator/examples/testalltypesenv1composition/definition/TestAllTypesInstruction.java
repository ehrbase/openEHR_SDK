package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-INSTRUCTION.test_all_types.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.813499700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class TestAllTypesInstruction implements EntryEntity {
  /**
   * Path: Test all types/Test all types/section 2/section 3/Test all types/Current Activity/partial
   * date Description: *
   */
  @Path("/activities[at0001]/description[at0002]/items[at0003]/value|value")
  private Temporal partialDateValue;

  /**
   * Path: Test all types/Test all types/section 2/section 3/Test all types/Current Activity/partial
   * datetime Description: *
   */
  @Path("/activities[at0001]/description[at0002]/items[at0004]/value|value")
  private TemporalAccessor partialDatetimeValue;

  /**
   * Path: Test all types/Test all types/section 2/section 3/Test all types/Current Activity/timing
   */
  @Path("/activities[at0001]/timing")
  private DvParsable timing;

  /** Path: Test all types/Test all types/section 2/section 3/Test all types/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Test all types/Test all types/section 2/section 3/Test all types/narrative */
  @Path("/narrative|value")
  private String narrativeValue;

  /** Path: Test all types/Test all types/section 2/section 3/Test all types/language */
  @Path("/language")
  private Language language;

  /** Path: Test all types/Test all types/section 2/section 3/Test all types/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: Test all types/Test all types/section 2/section 3/Test all types/expiry_time */
  @Path("/expiry_time|value")
  private TemporalAccessor expiryTimeValue;

  public void setPartialDateValue(Temporal partialDateValue) {
    this.partialDateValue = partialDateValue;
  }

  public Temporal getPartialDateValue() {
    return this.partialDateValue;
  }

  public void setPartialDatetimeValue(TemporalAccessor partialDatetimeValue) {
    this.partialDatetimeValue = partialDatetimeValue;
  }

  public TemporalAccessor getPartialDatetimeValue() {
    return this.partialDatetimeValue;
  }

  public void setTiming(DvParsable timing) {
    this.timing = timing;
  }

  public DvParsable getTiming() {
    return this.timing;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
  }

  public void setNarrativeValue(String narrativeValue) {
    this.narrativeValue = narrativeValue;
  }

  public String getNarrativeValue() {
    return this.narrativeValue;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }

  public void setExpiryTimeValue(TemporalAccessor expiryTimeValue) {
    this.expiryTimeValue = expiryTimeValue;
  }

  public TemporalAccessor getExpiryTimeValue() {
    return this.expiryTimeValue;
  }
}
