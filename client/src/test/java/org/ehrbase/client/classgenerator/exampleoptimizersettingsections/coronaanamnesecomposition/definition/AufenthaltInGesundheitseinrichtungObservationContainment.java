package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

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

public class AufenthaltInGesundheitseinrichtungObservationContainment extends Containment {
  public SelectAqlField<AufenthaltInGesundheitseinrichtungObservation>
      AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG_OBSERVATION =
          new AqlFieldImp<AufenthaltInGesundheitseinrichtungObservation>(
              AufenthaltInGesundheitseinrichtungObservation.class,
              "",
              "AufenthaltInGesundheitseinrichtungObservation",
              AufenthaltInGesundheitseinrichtungObservation.class,
              this);

  public SelectAqlField<String> AGENT_EN_VALUE =
      new AqlFieldImp<String>(
          AufenthaltInGesundheitseinrichtungObservation.class,
          "/data[at0001]/events[at0002]/data[at0042]/items[at0043]/value|value",
          "agentEnValue",
          String.class,
          this);

  public SelectAqlField<PresenceOfAnyExposureEnDefiningCode>
      PRESENCE_OF_ANY_EXPOSURE_EN_DEFINING_CODE =
          new AqlFieldImp<PresenceOfAnyExposureEnDefiningCode>(
              AufenthaltInGesundheitseinrichtungObservation.class,
              "/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value|defining_code",
              "presenceOfAnyExposureEnDefiningCode",
              PresenceOfAnyExposureEnDefiningCode.class,
              this);

  public SelectAqlField<String> EXPOSURE_EN_VALUE =
      new AqlFieldImp<String>(
          AufenthaltInGesundheitseinrichtungObservation.class,
          "/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0045]/value|value",
          "exposureEnValue",
          String.class,
          this);

  public SelectAqlField<VorhandenseinDefiningCode> VORHANDENSEIN_DEFINING_CODE =
      new AqlFieldImp<VorhandenseinDefiningCode>(
          AufenthaltInGesundheitseinrichtungObservation.class,
          "/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0046]/value|defining_code",
          "vorhandenseinDefiningCode",
          VorhandenseinDefiningCode.class,
          this);

  public SelectAqlField<String> KOMMENTAR_VALUE =
      new AqlFieldImp<String>(
          AufenthaltInGesundheitseinrichtungObservation.class,
          "/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value|value",
          "kommentarValue",
          String.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          AufenthaltInGesundheitseinrichtungObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          AufenthaltInGesundheitseinrichtungObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          AufenthaltInGesundheitseinrichtungObservation.class,
          "/protocol[at0004]/items[at0056]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          AufenthaltInGesundheitseinrichtungObservation.class,
          "/subject",
          "subject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          AufenthaltInGesundheitseinrichtungObservation.class,
          "/language",
          "language",
          Language.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          AufenthaltInGesundheitseinrichtungObservation.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  private AufenthaltInGesundheitseinrichtungObservationContainment() {
    super("openEHR-EHR-OBSERVATION.exposure_assessment.v0");
  }

  public static AufenthaltInGesundheitseinrichtungObservationContainment getInstance() {
    return new AufenthaltInGesundheitseinrichtungObservationContainment();
  }
}
