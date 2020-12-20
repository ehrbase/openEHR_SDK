package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class ResponseSectionContainment extends Containment {
  public SelectAqlField<ResponseSection> RESPONSE_SECTION =
      new AqlFieldImp<ResponseSection>(
          ResponseSection.class, "", "ResponseSection", ResponseSection.class, this);

  public SelectAqlField<RecommendationEvaluation> RECOMMENDATION =
      new AqlFieldImp<RecommendationEvaluation>(
          ResponseSection.class,
          "/items[openEHR-EHR-EVALUATION.recommendation.v1]",
          "recommendation",
          RecommendationEvaluation.class,
          this);

  public ListSelectAqlField<ServiceRequestInstruction> SERVICE_REQUEST =
      new ListAqlFieldImp<ServiceRequestInstruction>(
          ResponseSection.class,
          "/items[openEHR-EHR-INSTRUCTION.service_request.v1]",
          "serviceRequest",
          ServiceRequestInstruction.class,
          this);

  public ListSelectAqlField<ServiceAction> SERVICE =
      new ListAqlFieldImp<ServiceAction>(
          ResponseSection.class,
          "/items[openEHR-EHR-ACTION.service.v0]",
          "service",
          ServiceAction.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          ResponseSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ResponseSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static ResponseSectionContainment getInstance() {
    return new ResponseSectionContainment();
  }
}
