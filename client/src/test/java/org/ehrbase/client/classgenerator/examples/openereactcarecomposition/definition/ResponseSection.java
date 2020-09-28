package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class ResponseSection {
    @Path("/items[openEHR-EHR-ACTION.service.v0]")
    private List<ServiceAction> service;

    @Path("/items[openEHR-EHR-INSTRUCTION.service_request.v1]")
    private List<ServiceRequestInstruction> serviceRequest;

    @Path("/items[openEHR-EHR-EVALUATION.recommendation.v1]")
    private RecommendationEvaluation recommendation;

    public void setService(List<ServiceAction> service) {
        this.service = service;
    }

    public List<ServiceAction> getService() {
        return this.service;
    }

    public void setServiceRequest(List<ServiceRequestInstruction> serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public List<ServiceRequestInstruction> getServiceRequest() {
        return this.serviceRequest;
    }

    public void setRecommendation(RecommendationEvaluation recommendation) {
        this.recommendation = recommendation;
    }

    public RecommendationEvaluation getRecommendation() {
        return this.recommendation;
    }
}
