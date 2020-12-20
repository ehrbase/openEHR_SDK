package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class CovidSymptomsObservationContainment extends Containment {
  public SelectAqlField<CovidSymptomsObservation> COVID_SYMPTOMS_OBSERVATION =
      new AqlFieldImp<CovidSymptomsObservation>(
          CovidSymptomsObservation.class,
          "",
          "CovidSymptomsObservation",
          CovidSymptomsObservation.class,
          this);

  public SelectAqlField<String> STORY_VALUE =
      new AqlFieldImp<String>(
          CovidSymptomsObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|value",
          "storyValue",
          String.class,
          this);

  public SelectAqlField<FirstSymptomsCluster> FIRST_SYMPTOMS =
      new AqlFieldImp<FirstSymptomsCluster>(
          CovidSymptomsObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0]",
          "firstSymptoms",
          FirstSymptomsCluster.class,
          this);

  public ListSelectAqlField<KeyCovidSymptomCluster> KEY_COVID_SYMPTOM =
      new ListAqlFieldImp<KeyCovidSymptomCluster>(
          CovidSymptomsObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0]",
          "keyCovidSymptom",
          KeyCovidSymptomCluster.class,
          this);

  public ListSelectAqlField<ChestSymptomCluster> CHEST_SYMPTOM =
      new ListAqlFieldImp<ChestSymptomCluster>(
          CovidSymptomsObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0]",
          "chestSymptom",
          ChestSymptomCluster.class,
          this);

  public ListSelectAqlField<AbdomenSymptomCluster> ABDOMEN_SYMPTOM =
      new ListAqlFieldImp<AbdomenSymptomCluster>(
          CovidSymptomsObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0]",
          "abdomenSymptom",
          AbdomenSymptomCluster.class,
          this);

  public ListSelectAqlField<NeuroSymptomCluster> NEURO_SYMPTOM =
      new ListAqlFieldImp<NeuroSymptomCluster>(
          CovidSymptomsObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0]",
          "neuroSymptom",
          NeuroSymptomCluster.class,
          this);

  public ListSelectAqlField<OtherSymptomCluster> OTHER_SYMPTOM =
      new ListAqlFieldImp<OtherSymptomCluster>(
          CovidSymptomsObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0]",
          "otherSymptom",
          OtherSymptomCluster.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CovidSymptomsObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CovidSymptomsObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION =
      new ListAqlFieldImp<Cluster>(
          CovidSymptomsObservation.class,
          "/protocol[at0007]/items[at0008]",
          "extension",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CovidSymptomsObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          CovidSymptomsObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          CovidSymptomsObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private CovidSymptomsObservationContainment() {
    super("openEHR-EHR-OBSERVATION.story.v1");
  }

  public static CovidSymptomsObservationContainment getInstance() {
    return new CovidSymptomsObservationContainment();
  }
}
