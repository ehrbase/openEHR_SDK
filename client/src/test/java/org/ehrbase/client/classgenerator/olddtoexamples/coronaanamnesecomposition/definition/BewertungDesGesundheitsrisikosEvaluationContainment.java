package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

public class BewertungDesGesundheitsrisikosEvaluationContainment extends Containment {
  public SelectAqlField<BewertungDesGesundheitsrisikosEvaluation>
      BEWERTUNG_DES_GESUNDHEITSRISIKOS_EVALUATION =
          new AqlFieldImp<BewertungDesGesundheitsrisikosEvaluation>(
              BewertungDesGesundheitsrisikosEvaluation.class,
              "",
              "BewertungDesGesundheitsrisikosEvaluation",
              BewertungDesGesundheitsrisikosEvaluation.class,
              this);

  public SelectAqlField<TemporalAccessor> LETZTE_AKTUALISIERUNG_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          BewertungDesGesundheitsrisikosEvaluation.class,
          "/protocol[at0010]/items[at0024]/value|value",
          "letzteAktualisierungValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<BewertungDesGesundheitsrisikosRisikofaktorChoice> RISIKOFAKTOR =
      new AqlFieldImp<BewertungDesGesundheitsrisikosRisikofaktorChoice>(
          BewertungDesGesundheitsrisikosEvaluation.class,
          "/data[at0001]/items[at0016]/items[at0013]/value",
          "risikofaktor",
          BewertungDesGesundheitsrisikosRisikofaktorChoice.class,
          this);

  public SelectAqlField<String> RISIKOBEWERTUNG_VALUE =
      new AqlFieldImp<String>(
          BewertungDesGesundheitsrisikosEvaluation.class,
          "/data[at0001]/items[at0003]/value|value",
          "risikobewertungValue",
          String.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          BewertungDesGesundheitsrisikosEvaluation.class,
          "/protocol[at0010]/items[at0011]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<String> GESUNDHEITSRISIKO_VALUE =
      new AqlFieldImp<String>(
          BewertungDesGesundheitsrisikosEvaluation.class,
          "/data[at0001]/items[at0002]/value|value",
          "gesundheitsrisikoValue",
          String.class,
          this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          BewertungDesGesundheitsrisikosEvaluation.class,
          "/language",
          "language",
          Language.class,
          this);

  public SelectAqlField<BewertungDesGesundheitsrisikosVorhandenseinChoice> VORHANDENSEIN =
      new AqlFieldImp<BewertungDesGesundheitsrisikosVorhandenseinChoice>(
          BewertungDesGesundheitsrisikosEvaluation.class,
          "/data[at0001]/items[at0016]/items[at0017]/value",
          "vorhandensein",
          BewertungDesGesundheitsrisikosVorhandenseinChoice.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          BewertungDesGesundheitsrisikosEvaluation.class,
          "/subject",
          "subject",
          PartyProxy.class,
          this);

  public SelectAqlField<String> BEWERTUNGSMETHODE_VALUE =
      new AqlFieldImp<String>(
          BewertungDesGesundheitsrisikosEvaluation.class,
          "/protocol[at0010]/items[at0025]/value|value",
          "bewertungsmethodeValue",
          String.class,
          this);

  public SelectAqlField<BewertungDesGesundheitsrisikosDetailsChoice> DETAILS =
      new AqlFieldImp<BewertungDesGesundheitsrisikosDetailsChoice>(
          BewertungDesGesundheitsrisikosEvaluation.class,
          "/data[at0001]/items[at0016]/items[at0027]",
          "details",
          BewertungDesGesundheitsrisikosDetailsChoice.class,
          this);

  private BewertungDesGesundheitsrisikosEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.health_risk.v1");
  }

  public static BewertungDesGesundheitsrisikosEvaluationContainment getInstance() {
    return new BewertungDesGesundheitsrisikosEvaluationContainment();
  }
}
