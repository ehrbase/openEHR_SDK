package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.test_all_types.v1")
public class TestAllTypesObservation {
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0019]/value")
  private DvMultimedia multimediaAny;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/value|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0020]/value")
  private DvParsable parsableAny;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0021]/value")
  private DvIdentifier identifier;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|value")
  private String textValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|defining_code")
  private CodePhrase codedTextDefiningcode;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/value")
  private DvProportion proportionAny;

  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValueTime;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value|defining_code")
  private CodePhrase codedTextTerminologyDefiningcode;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|magnitude")
  private Double quantityMagnitude;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|units")
  private String quantityUnits;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|magnitude")
  private Long countMagnitude;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0009]/value|value")
  private Temporal dateValue;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0018]/value|value")
  private TemporalAmount durationAnyValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0013]/value")
  private DvOrdinal ordinal;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0017]/value|value")
  private Boolean booleanValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0010]/value|value")
  private TemporalAccessor datetimeValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value|value")
  private TemporalAccessor datetimeAnyValue;

  public void setMultimediaAny(DvMultimedia multimediaAny) {
    this.multimediaAny = multimediaAny;
  }

  public DvMultimedia getMultimediaAny() {
    return this.multimediaAny;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setParsableAny(DvParsable parsableAny) {
    this.parsableAny = parsableAny;
  }

  public DvParsable getParsableAny() {
    return this.parsableAny;
  }

  public void setIdentifier(DvIdentifier identifier) {
    this.identifier = identifier;
  }

  public DvIdentifier getIdentifier() {
    return this.identifier;
  }

  public void setTextValue(String textValue) {
    this.textValue = textValue;
  }

  public String getTextValue() {
    return this.textValue;
  }

  public void setCodedTextDefiningcode(CodePhrase codedTextDefiningcode) {
    this.codedTextDefiningcode = codedTextDefiningcode;
  }

  public CodePhrase getCodedTextDefiningcode() {
    return this.codedTextDefiningcode;
  }

  public void setProportionAny(DvProportion proportionAny) {
    this.proportionAny = proportionAny;
  }

  public DvProportion getProportionAny() {
    return this.proportionAny;
  }

  public void setTimeValueTime(TemporalAccessor timeValueTime) {
    this.timeValueTime = timeValueTime;
  }

  public TemporalAccessor getTimeValueTime() {
    return this.timeValueTime;
  }

  public void setCodedTextTerminologyDefiningcode(CodePhrase codedTextTerminologyDefiningcode) {
    this.codedTextTerminologyDefiningcode = codedTextTerminologyDefiningcode;
  }

  public CodePhrase getCodedTextTerminologyDefiningcode() {
    return this.codedTextTerminologyDefiningcode;
  }

  public void setQuantityMagnitude(Double quantityMagnitude) {
    this.quantityMagnitude = quantityMagnitude;
  }

  public Double getQuantityMagnitude() {
    return this.quantityMagnitude;
  }

  public void setQuantityUnits(String quantityUnits) {
    this.quantityUnits = quantityUnits;
  }

  public String getQuantityUnits() {
    return this.quantityUnits;
  }

  public void setCountMagnitude(Long countMagnitude) {
    this.countMagnitude = countMagnitude;
  }

  public Long getCountMagnitude() {
    return this.countMagnitude;
  }

  public void setDateValue(Temporal dateValue) {
    this.dateValue = dateValue;
  }

  public Temporal getDateValue() {
    return this.dateValue;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
  }

  public void setOriginValue(TemporalAccessor originValue) {
    this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
    return this.originValue;
  }

  public void setDurationAnyValue(TemporalAmount durationAnyValue) {
    this.durationAnyValue = durationAnyValue;
  }

  public TemporalAmount getDurationAnyValue() {
    return this.durationAnyValue;
  }

  public void setOrdinal(DvOrdinal ordinal) {
    this.ordinal = ordinal;
  }

  public DvOrdinal getOrdinal() {
    return this.ordinal;
  }

  public void setBooleanValue(Boolean booleanValue) {
    this.booleanValue = booleanValue;
  }

  public Boolean isBooleanValue() {
    return this.booleanValue;
  }

  public void setDatetimeValue(TemporalAccessor datetimeValue) {
    this.datetimeValue = datetimeValue;
  }

  public TemporalAccessor getDatetimeValue() {
    return this.datetimeValue;
  }

  public void setDatetimeAnyValue(TemporalAccessor datetimeAnyValue) {
    this.datetimeAnyValue = datetimeAnyValue;
  }

  public TemporalAccessor getDatetimeAnyValue() {
    return this.datetimeAnyValue;
  }
}
