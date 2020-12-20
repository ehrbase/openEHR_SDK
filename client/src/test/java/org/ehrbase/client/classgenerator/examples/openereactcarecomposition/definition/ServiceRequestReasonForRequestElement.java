package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.590502+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ServiceRequestReasonForRequestElement implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Response/Service request/Current Activity/Reason for request
   * Description: A short phrase describing the reason for the request.
   */
  @Path("/value")
  private DvCodedText value;

  /** Path: open_eREACT-Care/Response/Service request/Current Activity/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(DvCodedText value) {
    this.value = value;
  }

  public DvCodedText getValue() {
    return this.value;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
