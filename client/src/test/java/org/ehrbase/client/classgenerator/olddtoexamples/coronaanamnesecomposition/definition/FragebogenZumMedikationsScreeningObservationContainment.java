package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

public class FragebogenZumMedikationsScreeningObservationContainment extends Containment {
  public SelectAqlField<FragebogenZumMedikationsScreeningObservation>
      FRAGEBOGEN_ZUM_MEDIKATIONS_SCREENING_OBSERVATION =
          new AqlFieldImp<FragebogenZumMedikationsScreeningObservation>(
              FragebogenZumMedikationsScreeningObservation.class,
              "",
              "FragebogenZumMedikationsScreeningObservation",
              FragebogenZumMedikationsScreeningObservation.class,
              this);

  public ListSelectAqlField<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster>
      SPEZIFISCHE_MEDIKAMENTE =
          new ListAqlFieldImp<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster>(
              FragebogenZumMedikationsScreeningObservation.class,
              "/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0008]",
              "spezifischeMedikamente",
              FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster.class,
              this);

  public SelectAqlField<MedikamentenklasseInVerwendungDefiningcode>
      MEDIKAMENTENKLASSE_IN_VERWENDUNG_DEFININGCODE =
          new AqlFieldImp<MedikamentenklasseInVerwendungDefiningcode>(
              FragebogenZumMedikationsScreeningObservation.class,
              "/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0003]/value|defining_code",
              "medikamentenklasseInVerwendungDefiningcode",
              MedikamentenklasseInVerwendungDefiningcode.class,
              this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          FragebogenZumMedikationsScreeningObservation.class,
          "/language",
          "language",
          Language.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          FragebogenZumMedikationsScreeningObservation.class,
          "/data[at0022]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<CodePhrase> MEDIKAMENTE_IN_VERWENDUNG_DEFININGCODE =
      new AqlFieldImp<CodePhrase>(
          FragebogenZumMedikationsScreeningObservation.class,
          "/data[at0022]/events[at0023]/data[at0001]/items[at0027]/value|defining_code",
          "medikamenteInVerwendungDefiningcode",
          CodePhrase.class,
          this);

  public SelectAqlField<String> NAME_DER_MEDIKAMENTENKLASSE_VALUE =
      new AqlFieldImp<String>(
          FragebogenZumMedikationsScreeningObservation.class,
          "/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0002]/value|value",
          "nameDerMedikamentenklasseValue",
          String.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          FragebogenZumMedikationsScreeningObservation.class,
          "/data[at0022]/events[at0023]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          FragebogenZumMedikationsScreeningObservation.class,
          "/subject",
          "subject",
          PartyProxy.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          FragebogenZumMedikationsScreeningObservation.class,
          "/protocol[at0005]/items[at0019]",
          "erweiterung",
          Cluster.class,
          this);

  private FragebogenZumMedikationsScreeningObservationContainment() {
    super("openEHR-EHR-OBSERVATION.medication_use.v0");
  }

  public static FragebogenZumMedikationsScreeningObservationContainment getInstance() {
    return new FragebogenZumMedikationsScreeningObservationContainment();
  }
}
