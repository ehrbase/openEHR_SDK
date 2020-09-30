package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;

public class CovidSymptomsObservationContainment extends Containment {
  public SelectAqlField<CovidSymptomsObservation> COVID_SYMPTOMS_OBSERVATION = new AqlFieldImp<CovidSymptomsObservation>(CovidSymptomsObservation.class, "", "CovidSymptomsObservation", CovidSymptomsObservation.class, this);

  public SelectAqlField<String> STORY_VALUE = new AqlFieldImp<String>(CovidSymptomsObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|value", "storyValue", String.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(CovidSymptomsObservation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<OtherSymptomCluster> OTHER_SYMPTOM = new ListAqlFieldImp<OtherSymptomCluster>(CovidSymptomsObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='Other symptom']", "otherSymptom", OtherSymptomCluster.class, this);

  public ListSelectAqlField<NeuroSymptomCluster> NEURO_SYMPTOM = new ListAqlFieldImp<NeuroSymptomCluster>(CovidSymptomsObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='Neuro symptom']", "neuroSymptom", NeuroSymptomCluster.class, this);

  public ListSelectAqlField<ChestSymptomCluster> CHEST_SYMPTOM = new ListAqlFieldImp<ChestSymptomCluster>(CovidSymptomsObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='Chest symptom']", "chestSymptom", ChestSymptomCluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(CovidSymptomsObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<KeyCovidSymptomCluster> KEY_COVID_SYMPTOM = new ListAqlFieldImp<KeyCovidSymptomCluster>(CovidSymptomsObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='Key Covid symptom']", "keyCovidSymptom", KeyCovidSymptomCluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(CovidSymptomsObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(CovidSymptomsObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<AbdomenSymptomCluster> ABDOMEN_SYMPTOM = new ListAqlFieldImp<AbdomenSymptomCluster>(CovidSymptomsObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='Abdomen symptom']", "abdomenSymptom", AbdomenSymptomCluster.class, this);

  public SelectAqlField<FirstSymptomsCluster> FIRST_SYMPTOMS = new AqlFieldImp<FirstSymptomsCluster>(CovidSymptomsObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='First symptoms']", "firstSymptoms", FirstSymptomsCluster.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(CovidSymptomsObservation.class, "/protocol[at0007]/items[at0008]", "extension", Cluster.class, this);

  private CovidSymptomsObservationContainment() {
    super("openEHR-EHR-OBSERVATION.story.v1");
  }

  public static CovidSymptomsObservationContainment getInstance() {
    return new CovidSymptomsObservationContainment();
  }
}
