package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class ExposureScreeningQuestionnaireEnObservationContainment extends Containment {
  public SelectAqlField<ExposureScreeningQuestionnaireEnObservation> EXPOSURE_SCREENING_QUESTIONNAIRE_EN_OBSERVATION = new AqlFieldImp<ExposureScreeningQuestionnaireEnObservation>(ExposureScreeningQuestionnaireEnObservation.class, "", "ExposureScreeningQuestionnaireEnObservation", ExposureScreeningQuestionnaireEnObservation.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(ExposureScreeningQuestionnaireEnObservation.class, "/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value|value", "kommentarValue", String.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ExposureScreeningQuestionnaireEnObservation.class, "/protocol[at0004]/items[at0056]", "erweiterung", Cluster.class, this);

  public SelectAqlField<String> EXPOSURE_EN_VALUE = new AqlFieldImp<String>(ExposureScreeningQuestionnaireEnObservation.class, "/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0045]/value|value", "exposureEnValue", String.class, this);

  public SelectAqlField<VorhandenseinDefiningcode> VORHANDENSEIN_DEFININGCODE = new AqlFieldImp<VorhandenseinDefiningcode>(ExposureScreeningQuestionnaireEnObservation.class, "/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0046]/value|defining_code", "vorhandenseinDefiningcode", VorhandenseinDefiningcode.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ExposureScreeningQuestionnaireEnObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<String> AGENT_EN_VALUE = new AqlFieldImp<String>(ExposureScreeningQuestionnaireEnObservation.class, "/data[at0001]/events[at0002]/data[at0042]/items[at0043]/value|value", "agentEnValue", String.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(ExposureScreeningQuestionnaireEnObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ExposureScreeningQuestionnaireEnObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(ExposureScreeningQuestionnaireEnObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<PresenceOfAnyExposureEnDefiningcode> PRESENCE_OF_ANY_EXPOSURE_EN_DEFININGCODE = new AqlFieldImp<PresenceOfAnyExposureEnDefiningcode>(ExposureScreeningQuestionnaireEnObservation.class, "/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value|defining_code", "presenceOfAnyExposureEnDefiningcode", PresenceOfAnyExposureEnDefiningcode.class, this);

  private ExposureScreeningQuestionnaireEnObservationContainment() {
    super("openEHR-EHR-OBSERVATION.exposure_assessment.v0");
  }

  public static ExposureScreeningQuestionnaireEnObservationContainment getInstance() {
    return new ExposureScreeningQuestionnaireEnObservationContainment();
  }
}
