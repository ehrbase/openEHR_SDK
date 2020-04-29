package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class ServiceRequestInstructionContainment extends Containment {
  public SelectAqlField<ServiceRequestInstruction> SERVICE_REQUEST_INSTRUCTION = new AqlFieldImp<ServiceRequestInstruction>(ServiceRequestInstruction.class, "", "ServiceRequestInstruction", ServiceRequestInstruction.class, this);

  public SelectAqlField<String> NARRATIVE_VALUE = new AqlFieldImp<String>(ServiceRequestInstruction.class, "/narrative|value", "narrativeValue", String.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ServiceRequestInstruction.class, "/language", "language", Language.class, this);

  public SelectAqlField<Cluster> RECEIVER = new AqlFieldImp<Cluster>(ServiceRequestInstruction.class, "/protocol[at0008]/items[at0142]", "receiver", Cluster.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(ServiceRequestInstruction.class, "/protocol[at0008]/items[at0112]", "extension", Cluster.class, this);

  public SelectAqlField<Cluster> REQUESTER = new AqlFieldImp<Cluster>(ServiceRequestInstruction.class, "/protocol[at0008]/items[at0141]", "requester", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ServiceRequestInstruction.class, "/subject", "subject", PartyProxy.class, this);

  public ListSelectAqlField<ServiceRequestCurrentActivityActivity> CURRENT_ACTIVITY = new ListAqlFieldImp<ServiceRequestCurrentActivityActivity>(ServiceRequestInstruction.class, "/activities[at0001]", "currentActivity", ServiceRequestCurrentActivityActivity.class, this);

  public ListSelectAqlField<Cluster> DISTRIBUTION_LIST = new ListAqlFieldImp<Cluster>(ServiceRequestInstruction.class, "/protocol[at0008]/items[at0128]", "distributionList", Cluster.class, this);

  private ServiceRequestInstructionContainment() {
    super("openEHR-EHR-INSTRUCTION.service_request.v1");
  }

  public static ServiceRequestInstructionContainment getInstance() {
    return new ServiceRequestInstructionContainment();
  }
}
