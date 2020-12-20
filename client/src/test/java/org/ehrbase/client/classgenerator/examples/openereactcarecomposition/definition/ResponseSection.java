package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.583501700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ResponseSection implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Response/Recommendation Description: A suggestion, advice or proposal
   * for clinical management.
   */
  @Path("/items[openEHR-EHR-EVALUATION.recommendation.v1]")
  private RecommendationEvaluation recommendation;

  /**
   * Path: open_eREACT-Care/Response/Service request Description: Request for a health-related
   * service or activity to be delivered by a clinician, organisation or agency.
   */
  @Path("/items[openEHR-EHR-INSTRUCTION.service_request.v1]")
  private List<ServiceRequestInstruction> serviceRequest;

  /**
   * Path: open_eREACT-Care/Response/Service Description: A general clinical activity carried out
   * for the patient to receive a specified service, advice or care from an expert healthcare
   * provider.
   */
  @Path("/items[openEHR-EHR-ACTION.service.v0]")
  private List<ServiceAction> service;

  /** Path: open_eREACT-Care/Response/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setRecommendation(RecommendationEvaluation recommendation) {
    this.recommendation = recommendation;
  }

  public RecommendationEvaluation getRecommendation() {
    return this.recommendation;
  }

  public void setServiceRequest(List<ServiceRequestInstruction> serviceRequest) {
    this.serviceRequest = serviceRequest;
  }

  public List<ServiceRequestInstruction> getServiceRequest() {
    return this.serviceRequest;
  }

  public void setService(List<ServiceAction> service) {
    this.service = service;
  }

  public List<ServiceAction> getService() {
    return this.service;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
