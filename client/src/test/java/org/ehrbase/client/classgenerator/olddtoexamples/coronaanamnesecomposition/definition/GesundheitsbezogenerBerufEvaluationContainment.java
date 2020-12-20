package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

public class GesundheitsbezogenerBerufEvaluationContainment extends Containment {
  public SelectAqlField<GesundheitsbezogenerBerufEvaluation> GESUNDHEITSBEZOGENER_BERUF_EVALUATION =
      new AqlFieldImp<GesundheitsbezogenerBerufEvaluation>(
          GesundheitsbezogenerBerufEvaluation.class,
          "",
          "GesundheitsbezogenerBerufEvaluation",
          GesundheitsbezogenerBerufEvaluation.class,
          this);

  public SelectAqlField<String> BESCHAFTIGUNGSSTATUS_VALUE =
      new AqlFieldImp<String>(
          GesundheitsbezogenerBerufEvaluation.class,
          "/data[at0001]/items[at0004]/value|value",
          "beschaftigungsstatusValue",
          String.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          GesundheitsbezogenerBerufEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          GesundheitsbezogenerBerufEvaluation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<BeschaftigungCluster> BESCHAFTIGUNG =
      new ListAqlFieldImp<BeschaftigungCluster>(
          GesundheitsbezogenerBerufEvaluation.class,
          "/data[at0001]/items[openEHR-EHR-CLUSTER.occupation_record.v1]",
          "beschaftigung",
          BeschaftigungCluster.class,
          this);

  public ListSelectAqlField<Cluster> ZUSATZLICHE_ANGABEN =
      new ListAqlFieldImp<Cluster>(
          GesundheitsbezogenerBerufEvaluation.class,
          "/data[at0001]/items[at0005]",
          "zusatzlicheAngaben",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          GesundheitsbezogenerBerufEvaluation.class,
          "/protocol[at0007]/items[at0008]",
          "erweiterung",
          Cluster.class,
          this);

  private GesundheitsbezogenerBerufEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.occupation_summary.v1");
  }

  public static GesundheitsbezogenerBerufEvaluationContainment getInstance() {
    return new GesundheitsbezogenerBerufEvaluationContainment();
  }
}
