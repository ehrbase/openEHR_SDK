package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.test_all_types.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.796496400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class TestAllTypesObservation implements EntryEntity {
  /** Path: Test all types/Test all types/Cualquier evento/text Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|value")
  private String textValue;

  /** Path: Test all types/Test all types/Cualquier evento/coded text Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value")
  private DvCodedText codedText;

  /** Path: Test all types/Test all types/Cualquier evento/coded text terminology Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value")
  private DvCodedText codedTextTerminology;

  /** Path: Test all types/Test all types/Cualquier evento/quantity Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|magnitude")
  private Double quantityMagnitude;

  /** Path: Test all types/Test all types/Cualquier evento/quantity Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|units")
  private String quantityUnits;

  /** Path: Test all types/Test all types/Cualquier evento/count Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|magnitude")
  private Long countMagnitude;

  /** Path: Test all types/Test all types/Cualquier evento/date Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0009]/value|value")
  private Temporal dateValue;

  /** Path: Test all types/Test all types/Cualquier evento/datetime Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0010]/value|value")
  private TemporalAccessor datetimeValue;

  /** Path: Test all types/Test all types/Cualquier evento/datetime any Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value|value")
  private TemporalAccessor datetimeAnyValue;

  /** Path: Test all types/Test all types/Cualquier evento/time Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/value|value")
  private TemporalAccessor arbolTimeValue;

  /** Path: Test all types/Test all types/Cualquier evento/ordinal Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0013]/value")
  private DvOrdinal ordinal;

  /** Path: Test all types/Test all types/Cualquier evento/boolean Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0017]/value|value")
  private Boolean booleanValue;

  /** Path: Test all types/Test all types/Cualquier evento/duration any Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0018]/value|value")
  private TemporalAmount durationAnyValue;

  /** Path: Test all types/Test all types/Cualquier evento/multimedia any Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0019]/value")
  private DvMultimedia multimediaAny;

  /** Path: Test all types/Test all types/Cualquier evento/parsable any Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0020]/value")
  private DvParsable parsableAny;

  /** Path: Test all types/Test all types/Cualquier evento/identifier Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0021]/value")
  private DvIdentifier identifier;

  /** Path: Test all types/Test all types/Cualquier evento/proportion any Description: * */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/value")
  private DvProportion proportionAny;

  /** Path: Test all types/Test all types/Cualquier evento/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor cualquierEventoTimeValue;

  /** Path: Test all types/Test all types/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /** Path: Test all types/Test all types/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Test all types/Test all types/language */
  @Path("/language")
  private Language language;

  /** Path: Test all types/Test all types/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setTextValue(String textValue) {
    this.textValue = textValue;
  }

  public String getTextValue() {
    return this.textValue;
  }

  public void setCodedText(DvCodedText codedText) {
    this.codedText = codedText;
  }

  public DvCodedText getCodedText() {
    return this.codedText;
  }

  public void setCodedTextTerminology(DvCodedText codedTextTerminology) {
    this.codedTextTerminology = codedTextTerminology;
  }

  public DvCodedText getCodedTextTerminology() {
    return this.codedTextTerminology;
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

  public void setArbolTimeValue(TemporalAccessor arbolTimeValue) {
    this.arbolTimeValue = arbolTimeValue;
  }

  public TemporalAccessor getArbolTimeValue() {
    return this.arbolTimeValue;
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

  public void setDurationAnyValue(TemporalAmount durationAnyValue) {
    this.durationAnyValue = durationAnyValue;
  }

  public TemporalAmount getDurationAnyValue() {
    return this.durationAnyValue;
  }

  public void setMultimediaAny(DvMultimedia multimediaAny) {
    this.multimediaAny = multimediaAny;
  }

  public DvMultimedia getMultimediaAny() {
    return this.multimediaAny;
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

  public void setProportionAny(DvProportion proportionAny) {
    this.proportionAny = proportionAny;
  }

  public DvProportion getProportionAny() {
    return this.proportionAny;
  }

  public void setCualquierEventoTimeValue(TemporalAccessor cualquierEventoTimeValue) {
    this.cualquierEventoTimeValue = cualquierEventoTimeValue;
  }

  public TemporalAccessor getCualquierEventoTimeValue() {
    return this.cualquierEventoTimeValue;
  }

  public void setOriginValue(TemporalAccessor originValue) {
    this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
    return this.originValue;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
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
}
