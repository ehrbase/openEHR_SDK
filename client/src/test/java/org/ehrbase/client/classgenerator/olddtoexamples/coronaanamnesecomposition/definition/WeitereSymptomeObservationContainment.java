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

public class WeitereSymptomeObservationContainment extends Containment {
  public SelectAqlField<WeitereSymptomeObservation> WEITERE_SYMPTOME_OBSERVATION =
      new AqlFieldImp<WeitereSymptomeObservation>(
          WeitereSymptomeObservation.class,
          "",
          "WeitereSymptomeObservation",
          WeitereSymptomeObservation.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          WeitereSymptomeObservation.class,
          "/protocol[at0007]/items[at0021]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          WeitereSymptomeObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          WeitereSymptomeObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<WeitereSymptomeBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS =
      new ListAqlFieldImp<WeitereSymptomeBeliebigesEreignisChoice>(
          WeitereSymptomeObservation.class,
          "/data[at0001]/events[at0002]",
          "beliebigesEreignis",
          WeitereSymptomeBeliebigesEreignisChoice.class,
          this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          WeitereSymptomeObservation.class, "/language", "language", Language.class, this);

  private WeitereSymptomeObservationContainment() {
    super("openEHR-EHR-OBSERVATION.symptom_sign_screening.v0");
  }

  public static WeitereSymptomeObservationContainment getInstance() {
    return new WeitereSymptomeObservationContainment();
  }
}
