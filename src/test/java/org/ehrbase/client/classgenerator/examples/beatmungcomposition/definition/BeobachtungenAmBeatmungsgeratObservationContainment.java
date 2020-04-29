package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class BeobachtungenAmBeatmungsgeratObservationContainment extends Containment {
  public SelectAqlField<BeobachtungenAmBeatmungsgeratObservation> BEOBACHTUNGEN_AM_BEATMUNGSGERAT_OBSERVATION = new AqlFieldImp<BeobachtungenAmBeatmungsgeratObservation>(BeobachtungenAmBeatmungsgeratObservation.class, "", "BeobachtungenAmBeatmungsgeratObservation", BeobachtungenAmBeatmungsgeratObservation.class, this);

  public SelectAqlField<MedizingeratCluster> MEDIZINGERAT = new AqlFieldImp<MedizingeratCluster>(BeobachtungenAmBeatmungsgeratObservation.class, "/protocol[at0012]/items[openEHR-EHR-CLUSTER.device.v1]", "medizingerat", MedizingeratCluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(BeobachtungenAmBeatmungsgeratObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(BeobachtungenAmBeatmungsgeratObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(BeobachtungenAmBeatmungsgeratObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(BeobachtungenAmBeatmungsgeratObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<MesswerteCluster> MESSWERTE = new AqlFieldImp<MesswerteCluster>(BeobachtungenAmBeatmungsgeratObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.ventilator_settings2.v0]", "messwerte", MesswerteCluster.class, this);

  public SelectAqlField<MesswerteClusterBeatmungsgeratEinstellungen> MESSWERTE_BEATMUNGSGERAT_EINSTELLUNGEN = new AqlFieldImp<MesswerteClusterBeatmungsgeratEinstellungen>(BeobachtungenAmBeatmungsgeratObservation.class, "/data[at0001]/events[at0002]/state[at0010]/items[openEHR-EHR-CLUSTER.ventilator_settings2.v0]", "messwerteBeatmungsgeratEinstellungen", MesswerteClusterBeatmungsgeratEinstellungen.class, this);

  private BeobachtungenAmBeatmungsgeratObservationContainment() {
    super("openEHR-EHR-OBSERVATION.ventilator_vital_signs.v0");
  }

  public static BeobachtungenAmBeatmungsgeratObservationContainment getInstance() {
    return new BeobachtungenAmBeatmungsgeratObservationContainment();
  }
}
