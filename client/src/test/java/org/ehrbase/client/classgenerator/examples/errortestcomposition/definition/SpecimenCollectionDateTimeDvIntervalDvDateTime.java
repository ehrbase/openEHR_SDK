package org.ehrbase.client.classgenerator.examples.errortestcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-03-02T14:11:00.845608300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_INTERVAL<DV_DATE_TIME>")
public class SpecimenCollectionDateTimeDvIntervalDvDateTime
    implements RMEntity, SpecimenCollectionDateTimeChoice {
  /** Path: ErrorTest/Laboratory test result/Any event/Specimen/Collection date/time/lower */
  @Path("/lower|value")
  private TemporalAccessor lowerValue;

  /** Path: ErrorTest/Laboratory test result/Any event/Specimen/Collection date/time/upper */
  @Path("/upper|value")
  private TemporalAccessor upperValue;

  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Collection date/time/lower_included
   */
  @Path("/lower_included")
  private Boolean lowerIncluded;

  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Collection date/time/upper_included
   */
  @Path("/upper_included")
  private Boolean upperIncluded;

  public void setLowerValue(TemporalAccessor lowerValue) {
    this.lowerValue = lowerValue;
  }

  public TemporalAccessor getLowerValue() {
    return this.lowerValue;
  }

  public void setUpperValue(TemporalAccessor upperValue) {
    this.upperValue = upperValue;
  }

  public TemporalAccessor getUpperValue() {
    return this.upperValue;
  }

  public void setLowerIncluded(Boolean lowerIncluded) {
    this.lowerIncluded = lowerIncluded;
  }

  public Boolean isLowerIncluded() {
    return this.lowerIncluded;
  }

  public void setUpperIncluded(Boolean upperIncluded) {
    this.upperIncluded = upperIncluded;
  }

  public Boolean isUpperIncluded() {
    return this.upperIncluded;
  }
}
