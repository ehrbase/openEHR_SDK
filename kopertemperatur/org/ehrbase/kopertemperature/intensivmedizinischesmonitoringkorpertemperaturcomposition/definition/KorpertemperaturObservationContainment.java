package org.ehrbase.kopertemperature.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.kopertemperature.shareddefinition.Language;

public class KorpertemperaturObservationContainment extends Containment {
  public SelectAqlField<KorpertemperaturObservation> KORPERTEMPERATUR_OBSERVATION = new AqlFieldImp<KorpertemperaturObservation>(KorpertemperaturObservation.class, "", "KorpertemperaturObservation", KorpertemperaturObservation.class, this);

  public ListSelectAqlField<KorpertemperaturBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS = new ListAqlFieldImp<KorpertemperaturBeliebigesEreignisChoice>(KorpertemperaturObservation.class, "/data[at0002]/events[at0003]", "beliebigesEreignis", KorpertemperaturBeliebigesEreignisChoice.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(KorpertemperaturObservation.class, "/protocol[at0020]/items[at0062]", "erweiterung", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KorpertemperaturObservation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_LOKALISATION_DER_MESSUNG = new ListAqlFieldImp<Cluster>(KorpertemperaturObservation.class, "/protocol[at0020]/items[at0064]", "strukturierteLokalisationDerMessung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(KorpertemperaturObservation.class, "/data[at0002]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Cluster> GERAT = new AqlFieldImp<Cluster>(KorpertemperaturObservation.class, "/protocol[at0020]/items[at0059]", "gerat", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(KorpertemperaturObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<KorpertemperaturLokalisationDerMessungChoice> LOKALISATION_DER_MESSUNG = new AqlFieldImp<KorpertemperaturLokalisationDerMessungChoice>(KorpertemperaturObservation.class, "/protocol[at0020]/items[at0021]/value", "lokalisationDerMessung", KorpertemperaturLokalisationDerMessungChoice.class, this);

  private KorpertemperaturObservationContainment() {
    super("openEHR-EHR-OBSERVATION.body_temperature.v2");
  }

  public static KorpertemperaturObservationContainment getInstance() {
    return new KorpertemperaturObservationContainment();
  }
}
