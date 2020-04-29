package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class SignScreeningQuestionnaireObservationContainment extends Containment {
  public SelectAqlField<SignScreeningQuestionnaireObservation> SIGN_SCREENING_QUESTIONNAIRE_OBSERVATION = new AqlFieldImp<SignScreeningQuestionnaireObservation>(SignScreeningQuestionnaireObservation.class, "", "SignScreeningQuestionnaireObservation", SignScreeningQuestionnaireObservation.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(SignScreeningQuestionnaireObservation.class, "/protocol[at0007]/items[at0021]", "extension", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(SignScreeningQuestionnaireObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(SignScreeningQuestionnaireObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<SymptomSignScreeningQuestionnaireAnyEventChoice> ANY_EVENT = new ListAqlFieldImp<SymptomSignScreeningQuestionnaireAnyEventChoice>(SignScreeningQuestionnaireObservation.class, "/data[at0001]/events[at0002]", "anyEvent", SymptomSignScreeningQuestionnaireAnyEventChoice.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(SignScreeningQuestionnaireObservation.class, "/language", "language", Language.class, this);

  private SignScreeningQuestionnaireObservationContainment() {
    super("openEHR-EHR-OBSERVATION.symptom_sign_screening.v0");
  }

  public static SignScreeningQuestionnaireObservationContainment getInstance() {
    return new SignScreeningQuestionnaireObservationContainment();
  }
}
