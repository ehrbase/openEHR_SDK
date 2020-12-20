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

public class SepsisScreeningObservationContainment extends Containment {
  public SelectAqlField<SepsisScreeningObservation> SEPSIS_SCREENING_OBSERVATION =
      new AqlFieldImp<SepsisScreeningObservation>(
          SepsisScreeningObservation.class,
          "",
          "SepsisScreeningObservation",
          SepsisScreeningObservation.class,
          this);

  public ListSelectAqlField<SepsisScreeningRiskFactorsForSepsisElement> RISK_FACTORS_FOR_SEPSIS =
      new ListAqlFieldImp<SepsisScreeningRiskFactorsForSepsisElement>(
          SepsisScreeningObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0006]",
          "riskFactorsForSepsis",
          SepsisScreeningRiskFactorsForSepsisElement.class,
          this);

  public ListSelectAqlField<SepsisScreeningLikelySourceOfInfectionElement>
      LIKELY_SOURCE_OF_INFECTION =
          new ListAqlFieldImp<SepsisScreeningLikelySourceOfInfectionElement>(
              SepsisScreeningObservation.class,
              "/data[at0001]/events[at0002]/data[at0003]/items[at0011]",
              "likelySourceOfInfection",
              SepsisScreeningLikelySourceOfInfectionElement.class,
              this);

  public ListSelectAqlField<SepsisScreeningRedFlagAcuteElement> RED_FLAG_ACUTE =
      new ListAqlFieldImp<SepsisScreeningRedFlagAcuteElement>(
          SepsisScreeningObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0058]",
          "redFlagAcute",
          SepsisScreeningRedFlagAcuteElement.class,
          this);

  public ListSelectAqlField<SepsisScreeningAmberFlagAcuteElement> AMBER_FLAG_ACUTE =
      new ListAqlFieldImp<SepsisScreeningAmberFlagAcuteElement>(
          SepsisScreeningObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0083]",
          "amberFlagAcute",
          SepsisScreeningAmberFlagAcuteElement.class,
          this);

  public ListSelectAqlField<SepsisScreening999FlagElement> N999_FLAG =
      new ListAqlFieldImp<SepsisScreening999FlagElement>(
          SepsisScreeningObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0036]",
          "N999Flag",
          SepsisScreening999FlagElement.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          SepsisScreeningObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          SepsisScreeningObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION =
      new ListAqlFieldImp<Cluster>(
          SepsisScreeningObservation.class,
          "/protocol[at0004]/items[at0005]",
          "extension",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          SepsisScreeningObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          SepsisScreeningObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          SepsisScreeningObservation.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  private SepsisScreeningObservationContainment() {
    super("openEHR-EHR-OBSERVATION.sepsis_screening_tool.v0");
  }

  public static SepsisScreeningObservationContainment getInstance() {
    return new SepsisScreeningObservationContainment();
  }
}
