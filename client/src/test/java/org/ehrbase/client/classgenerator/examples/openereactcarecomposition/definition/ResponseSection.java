package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class ResponseSection {
  /**
   * open_eREACT-Care/Response/Recommendation
   */
  @Path("/items[openEHR-EHR-EVALUATION.recommendation.v1]")
  private RecommendationEvaluation recommendation;

  /**
   * open_eREACT-Care/Response/Service request
   */
  @Path("/items[openEHR-EHR-INSTRUCTION.service_request.v1]")
  private List<ServiceRequestInstruction> serviceRequest;

  /**
   * open_eREACT-Care/Response/Service
   */
  @Path("/items[openEHR-EHR-ACTION.service.v0]")
  private List<ServiceAction> service;

  /**
   * open_eREACT-Care/Response/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setRecommendation(RecommendationEvaluation recommendation) {
     this.recommendation = recommendation;
  }

  public RecommendationEvaluation getRecommendation() {
     return this.recommendation ;
  }

  public void setServiceRequest(List<ServiceRequestInstruction> serviceRequest) {
     this.serviceRequest = serviceRequest;
  }

  public List<ServiceRequestInstruction> getServiceRequest() {
     return this.serviceRequest ;
  }

  public void setService(List<ServiceAction> service) {
     this.service = service;
  }

  public List<ServiceAction> getService() {
     return this.service ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
