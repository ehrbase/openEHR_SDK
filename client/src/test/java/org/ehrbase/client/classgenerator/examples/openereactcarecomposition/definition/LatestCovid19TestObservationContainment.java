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

public class LatestCovid19TestObservationContainment extends Containment {
  public SelectAqlField<LatestCovid19TestObservation> LATEST_COVID19_TEST_OBSERVATION =
      new AqlFieldImp<LatestCovid19TestObservation>(
          LatestCovid19TestObservation.class,
          "",
          "LatestCovid19TestObservation",
          LatestCovid19TestObservation.class,
          this);

  public SelectAqlField<String> TEST_NAME_VALUE =
      new AqlFieldImp<String>(
          LatestCovid19TestObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value",
          "testNameValue",
          String.class,
          this);

  public ListSelectAqlField<Cluster> SPECIMEN_DETAIL =
      new ListAqlFieldImp<Cluster>(
          LatestCovid19TestObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0065]",
          "specimenDetail",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> TEST_RESULT =
      new ListAqlFieldImp<Cluster>(
          LatestCovid19TestObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0097]",
          "testResult",
          Cluster.class,
          this);

  public SelectAqlField<String> TEST_DIAGNOSIS_VALUE =
      new AqlFieldImp<String>(
          LatestCovid19TestObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0098]/value|value",
          "testDiagnosisValue",
          String.class,
          this);

  public ListSelectAqlField<Cluster> STRUCTURED_TEST_DIAGNOSIS =
      new ListAqlFieldImp<Cluster>(
          LatestCovid19TestObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0122]",
          "structuredTestDiagnosis",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> MULTIMEDIA_REPRESENTATION =
      new ListAqlFieldImp<Cluster>(
          LatestCovid19TestObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0118]",
          "multimediaRepresentation",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> STRUCTURED_CONFOUNDING_FACTORS =
      new ListAqlFieldImp<Cluster>(
          LatestCovid19TestObservation.class,
          "/data[at0001]/events[at0002]/state[at0112]/items[at0114]",
          "structuredConfoundingFactors",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          LatestCovid19TestObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          LatestCovid19TestObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<Cluster> RECEIVING_LABORATORY =
      new AqlFieldImp<Cluster>(
          LatestCovid19TestObservation.class,
          "/protocol[at0004]/items[at0017]",
          "receivingLaboratory",
          Cluster.class,
          this);

  public ListSelectAqlField<LatestCovid19TestTestRequestDetailsCluster> TEST_REQUEST_DETAILS =
      new ListAqlFieldImp<LatestCovid19TestTestRequestDetailsCluster>(
          LatestCovid19TestObservation.class,
          "/protocol[at0004]/items[at0094]",
          "testRequestDetails",
          LatestCovid19TestTestRequestDetailsCluster.class,
          this);

  public ListSelectAqlField<Cluster> TESTING_DETAILS =
      new ListAqlFieldImp<Cluster>(
          LatestCovid19TestObservation.class,
          "/protocol[at0004]/items[at0110]",
          "testingDetails",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION =
      new ListAqlFieldImp<Cluster>(
          LatestCovid19TestObservation.class,
          "/protocol[at0004]/items[at0117]",
          "extension",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          LatestCovid19TestObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          LatestCovid19TestObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          LatestCovid19TestObservation.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  public SelectAqlField<LatestCovid19TestOverallTestStatusChoice> OVERALL_TEST_STATUS =
      new AqlFieldImp<LatestCovid19TestOverallTestStatusChoice>(
          LatestCovid19TestObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0073]/value",
          "overallTestStatus",
          LatestCovid19TestOverallTestStatusChoice.class,
          this);

  private LatestCovid19TestObservationContainment() {
    super("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
  }

  public static LatestCovid19TestObservationContainment getInstance() {
    return new LatestCovid19TestObservationContainment();
  }
}
