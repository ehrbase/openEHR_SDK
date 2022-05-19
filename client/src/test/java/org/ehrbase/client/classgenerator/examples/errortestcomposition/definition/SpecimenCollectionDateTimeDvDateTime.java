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
    date = "2022-03-02T14:11:00.844601200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_DATE_TIME")
public class SpecimenCollectionDateTimeDvDateTime
    implements RMEntity, SpecimenCollectionDateTimeChoice {
  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Collection date/time/Collection
   * date/time Description: The date and time that collection has been ordered to take place or has
   * taken place.
   */
  @Path("|value")
  private TemporalAccessor collectionDateTimeValue;

  public void setCollectionDateTimeValue(TemporalAccessor collectionDateTimeValue) {
    this.collectionDateTimeValue = collectionDateTimeValue;
  }

  public TemporalAccessor getCollectionDateTimeValue() {
    return this.collectionDateTimeValue;
  }
}
