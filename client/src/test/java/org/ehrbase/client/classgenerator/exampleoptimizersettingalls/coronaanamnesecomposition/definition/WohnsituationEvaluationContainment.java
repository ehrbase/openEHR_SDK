package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class WohnsituationEvaluationContainment extends Containment {
  public SelectAqlField<WohnsituationEvaluation> WOHNSITUATION_EVALUATION =
      new AqlFieldImp<WohnsituationEvaluation>(
          WohnsituationEvaluation.class,
          "",
          "WohnsituationEvaluation",
          WohnsituationEvaluation.class,
          this);

  public SelectAqlField<String> BESCHREIBUNG_VALUE =
      new AqlFieldImp<String>(
          WohnsituationEvaluation.class,
          "/data[at0001]/items[at0003]/value|value",
          "beschreibungValue",
          String.class,
          this);

  public SelectAqlField<Long> ANZAHL_DER_HAUSHALTSMITGLIEDER_MAGNITUDE =
      new AqlFieldImp<Long>(
          WohnsituationEvaluation.class,
          "/data[at0001]/items[at0007]/value|magnitude",
          "anzahlDerHaushaltsmitgliederMagnitude",
          Long.class,
          this);

  public ListSelectAqlField<WohnstatteCluster> WOHNSTATTE =
      new ListAqlFieldImp<WohnstatteCluster>(
          WohnsituationEvaluation.class,
          "/data[at0001]/items[openEHR-EHR-CLUSTER.dwelling.v0]",
          "wohnstatte",
          WohnstatteCluster.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          WohnsituationEvaluation.class,
          "/protocol[at0002]/items[at0011]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          WohnsituationEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          WohnsituationEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          WohnsituationEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private WohnsituationEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.living_arrangement.v0");
  }

  public static WohnsituationEvaluationContainment getInstance() {
    return new WohnsituationEvaluationContainment();
  }
}
