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

public class FieberOderErhohteKorpertemperaturObservationContainment extends Containment {
  public SelectAqlField<FieberOderErhohteKorpertemperaturObservation>
      FIEBER_ODER_ERHOHTE_KORPERTEMPERATUR_OBSERVATION =
          new AqlFieldImp<FieberOderErhohteKorpertemperaturObservation>(
              FieberOderErhohteKorpertemperaturObservation.class,
              "",
              "FieberOderErhohteKorpertemperaturObservation",
              FieberOderErhohteKorpertemperaturObservation.class,
              this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          FieberOderErhohteKorpertemperaturObservation.class,
          "/language",
          "language",
          Language.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          FieberOderErhohteKorpertemperaturObservation.class,
          "/protocol[at0007]/items[at0021]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<String> BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE =
      new AqlFieldImp<String>(
          FieberOderErhohteKorpertemperaturObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
          "bezeichnungDesSymptomsOderAnzeichensValue",
          String.class,
          this);

  public SelectAqlField<VorhandenDefiningcode> VORHANDEN_DEFININGCODE =
      new AqlFieldImp<VorhandenDefiningcode>(
          FieberOderErhohteKorpertemperaturObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
          "vorhandenDefiningcode",
          VorhandenDefiningcode.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          FieberOderErhohteKorpertemperaturObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          FieberOderErhohteKorpertemperaturObservation.class,
          "/subject",
          "subject",
          PartyProxy.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          FieberOderErhohteKorpertemperaturObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> ANZEICHEN =
      new ListAqlFieldImp<Cluster>(
          FieberOderErhohteKorpertemperaturObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
          "anzeichen",
          Cluster.class,
          this);

  private FieberOderErhohteKorpertemperaturObservationContainment() {
    super("openEHR-EHR-OBSERVATION.symptom_sign_screening.v0");
  }

  public static FieberOderErhohteKorpertemperaturObservationContainment getInstance() {
    return new FieberOderErhohteKorpertemperaturObservationContainment();
  }
}
