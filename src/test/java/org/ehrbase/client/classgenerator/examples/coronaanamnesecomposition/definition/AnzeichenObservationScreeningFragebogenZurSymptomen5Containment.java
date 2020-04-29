package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class AnzeichenObservationScreeningFragebogenZurSymptomen5Containment extends Containment {
  public SelectAqlField<AnzeichenObservationScreeningFragebogenZurSymptomen5> ANZEICHEN_OBSERVATION_SCREENING_FRAGEBOGEN_ZUR_SYMPTOMEN5 = new AqlFieldImp<AnzeichenObservationScreeningFragebogenZurSymptomen5>(AnzeichenObservationScreeningFragebogenZurSymptomen5.class, "", "AnzeichenObservationScreeningFragebogenZurSymptomen5", AnzeichenObservationScreeningFragebogenZurSymptomen5.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(AnzeichenObservationScreeningFragebogenZurSymptomen5.class, "/protocol[at0007]/items[at0021]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AnzeichenObservationScreeningFragebogenZurSymptomen5.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(AnzeichenObservationScreeningFragebogenZurSymptomen5.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<ScreeningFragebogenZurSymptomenAnzeichenBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS = new ListAqlFieldImp<ScreeningFragebogenZurSymptomenAnzeichenBeliebigesEreignisChoice>(AnzeichenObservationScreeningFragebogenZurSymptomen5.class, "/data[at0001]/events[at0002]", "beliebigesEreignis", ScreeningFragebogenZurSymptomenAnzeichenBeliebigesEreignisChoice.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AnzeichenObservationScreeningFragebogenZurSymptomen5.class, "/language", "language", Language.class, this);

  private AnzeichenObservationScreeningFragebogenZurSymptomen5Containment() {
    super("openEHR-EHR-OBSERVATION.symptom_sign_screening_weitere.v0");
  }

  public static AnzeichenObservationScreeningFragebogenZurSymptomen5Containment getInstance() {
    return new AnzeichenObservationScreeningFragebogenZurSymptomen5Containment();
  }
}
