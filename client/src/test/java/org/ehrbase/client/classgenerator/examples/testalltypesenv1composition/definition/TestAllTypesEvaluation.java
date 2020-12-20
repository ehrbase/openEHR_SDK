package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.net.URI;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.test_all_types.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.804495600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class TestAllTypesEvaluation implements EntryEntity {
  /** Path: Test all types/Test all types/uri Description: * */
  @Path("/data[at0001]/items[at0002]/value|value")
  private URI uriValue;

  /** Path: Test all types/Test all types/interval count/upper */
  @Path("/data[at0001]/items[at0003]/value/upper|magnitude")
  private Long upperMagnitude;

  /** Path: Test all types/Test all types/interval count/lower */
  @Path("/data[at0001]/items[at0003]/value/lower|magnitude")
  private Long lowerMagnitude;

  /** Path: Test all types/Test all types/interval count/lower_included */
  @Path("/data[at0001]/items[at0003]/value/lower_included")
  private Boolean intervalCountLowerIncluded;

  /** Path: Test all types/Test all types/interval count/upper_included */
  @Path("/data[at0001]/items[at0003]/value/upper_included")
  private Boolean intervalCountUpperIncluded;

  /** Path: Test all types/Test all types/interval quantity/upper */
  @Path("/data[at0001]/items[at0004]/value/upper|magnitude")
  private Double upperMagnitude2;

  /** Path: Test all types/Test all types/interval quantity/upper */
  @Path("/data[at0001]/items[at0004]/value/upper|units")
  private String upperUnits;

  /** Path: Test all types/Test all types/interval quantity/lower */
  @Path("/data[at0001]/items[at0004]/value/lower|magnitude")
  private Double lowerMagnitude2;

  /** Path: Test all types/Test all types/interval quantity/lower */
  @Path("/data[at0001]/items[at0004]/value/lower|units")
  private String lowerUnits;

  /** Path: Test all types/Test all types/interval quantity/lower_included */
  @Path("/data[at0001]/items[at0004]/value/lower_included")
  private Boolean intervalQuantityLowerIncluded;

  /** Path: Test all types/Test all types/interval quantity/upper_included */
  @Path("/data[at0001]/items[at0004]/value/upper_included")
  private Boolean intervalQuantityUpperIncluded;

  /** Path: Test all types/Test all types/interval datetime/upper */
  @Path("/data[at0001]/items[at0005]/value/upper|value")
  private TemporalAccessor intervalDatetimeUpperValue;

  /** Path: Test all types/Test all types/interval datetime/lower */
  @Path("/data[at0001]/items[at0005]/value/lower|value")
  private TemporalAccessor intervalDatetimeLowerValue;

  /** Path: Test all types/Test all types/interval datetime/lower_included */
  @Path("/data[at0001]/items[at0005]/value/lower_included")
  private Boolean intervalDatetimeLowerIncluded;

  /** Path: Test all types/Test all types/interval datetime/upper_included */
  @Path("/data[at0001]/items[at0005]/value/upper_included")
  private Boolean intervalDatetimeUpperIncluded;

  /** Path: Test all types/Test all types/cluster 1/cluster 2/cluster 3/text 2 Description: * */
  @Path("/data[at0001]/items[at0006]/items[at0007]/items[at0008]/items[at0010]/value|value")
  private String text2Value;

  /** Path: Test all types/Test all types/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Test all types/Test all types/language */
  @Path("/language")
  private Language language;

  /** Path: Test all types/Test all types/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: Test all types/Test all types/value */
  @Path("/data[at0001]/items[at0009]/value")
  @Choice
  private TestAllTypesChoiceChoice choice;

  public void setUriValue(URI uriValue) {
    this.uriValue = uriValue;
  }

  public URI getUriValue() {
    return this.uriValue;
  }

  public void setUpperMagnitude(Long upperMagnitude) {
    this.upperMagnitude = upperMagnitude;
  }

  public Long getUpperMagnitude() {
    return this.upperMagnitude;
  }

  public void setLowerMagnitude(Long lowerMagnitude) {
    this.lowerMagnitude = lowerMagnitude;
  }

  public Long getLowerMagnitude() {
    return this.lowerMagnitude;
  }

  public void setIntervalCountLowerIncluded(Boolean intervalCountLowerIncluded) {
    this.intervalCountLowerIncluded = intervalCountLowerIncluded;
  }

  public Boolean isIntervalCountLowerIncluded() {
    return this.intervalCountLowerIncluded;
  }

  public void setIntervalCountUpperIncluded(Boolean intervalCountUpperIncluded) {
    this.intervalCountUpperIncluded = intervalCountUpperIncluded;
  }

  public Boolean isIntervalCountUpperIncluded() {
    return this.intervalCountUpperIncluded;
  }

  public void setUpperMagnitude2(Double upperMagnitude2) {
    this.upperMagnitude2 = upperMagnitude2;
  }

  public Double getUpperMagnitude2() {
    return this.upperMagnitude2;
  }

  public void setUpperUnits(String upperUnits) {
    this.upperUnits = upperUnits;
  }

  public String getUpperUnits() {
    return this.upperUnits;
  }

  public void setLowerMagnitude2(Double lowerMagnitude2) {
    this.lowerMagnitude2 = lowerMagnitude2;
  }

  public Double getLowerMagnitude2() {
    return this.lowerMagnitude2;
  }

  public void setLowerUnits(String lowerUnits) {
    this.lowerUnits = lowerUnits;
  }

  public String getLowerUnits() {
    return this.lowerUnits;
  }

  public void setIntervalQuantityLowerIncluded(Boolean intervalQuantityLowerIncluded) {
    this.intervalQuantityLowerIncluded = intervalQuantityLowerIncluded;
  }

  public Boolean isIntervalQuantityLowerIncluded() {
    return this.intervalQuantityLowerIncluded;
  }

  public void setIntervalQuantityUpperIncluded(Boolean intervalQuantityUpperIncluded) {
    this.intervalQuantityUpperIncluded = intervalQuantityUpperIncluded;
  }

  public Boolean isIntervalQuantityUpperIncluded() {
    return this.intervalQuantityUpperIncluded;
  }

  public void setIntervalDatetimeUpperValue(TemporalAccessor intervalDatetimeUpperValue) {
    this.intervalDatetimeUpperValue = intervalDatetimeUpperValue;
  }

  public TemporalAccessor getIntervalDatetimeUpperValue() {
    return this.intervalDatetimeUpperValue;
  }

  public void setIntervalDatetimeLowerValue(TemporalAccessor intervalDatetimeLowerValue) {
    this.intervalDatetimeLowerValue = intervalDatetimeLowerValue;
  }

  public TemporalAccessor getIntervalDatetimeLowerValue() {
    return this.intervalDatetimeLowerValue;
  }

  public void setIntervalDatetimeLowerIncluded(Boolean intervalDatetimeLowerIncluded) {
    this.intervalDatetimeLowerIncluded = intervalDatetimeLowerIncluded;
  }

  public Boolean isIntervalDatetimeLowerIncluded() {
    return this.intervalDatetimeLowerIncluded;
  }

  public void setIntervalDatetimeUpperIncluded(Boolean intervalDatetimeUpperIncluded) {
    this.intervalDatetimeUpperIncluded = intervalDatetimeUpperIncluded;
  }

  public Boolean isIntervalDatetimeUpperIncluded() {
    return this.intervalDatetimeUpperIncluded;
  }

  public void setText2Value(String text2Value) {
    this.text2Value = text2Value;
  }

  public String getText2Value() {
    return this.text2Value;
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

  public void setChoice(TestAllTypesChoiceChoice choice) {
    this.choice = choice;
  }

  public TestAllTypesChoiceChoice getChoice() {
    return this.choice;
  }
}
