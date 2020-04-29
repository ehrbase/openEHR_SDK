package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class AnzeichenObservationScreeningFragebogenZurSymptomen2Containment extends Containment {
  public SelectAqlField<AnzeichenObservationScreeningFragebogenZurSymptomen2> ANZEICHEN_OBSERVATION_SCREENING_FRAGEBOGEN_ZUR_SYMPTOMEN2 = new AqlFieldImp<AnzeichenObservationScreeningFragebogenZurSymptomen2>(AnzeichenObservationScreeningFragebogenZurSymptomen2.class, "", "AnzeichenObservationScreeningFragebogenZurSymptomen2", AnzeichenObservationScreeningFragebogenZurSymptomen2.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AnzeichenObservationScreeningFragebogenZurSymptomen2.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(AnzeichenObservationScreeningFragebogenZurSymptomen2.class, "/protocol[at0007]/items[at0021]", "erweiterung", Cluster.class, this);

  public SelectAqlField<String> BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE = new AqlFieldImp<String>(AnzeichenObservationScreeningFragebogenZurSymptomen2.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value", "bezeichnungDesSymptomsOderAnzeichensValue", String.class, this);

  public SelectAqlField<VorhandenDefiningcode> VORHANDEN_DEFININGCODE = new AqlFieldImp<VorhandenDefiningcode>(AnzeichenObservationScreeningFragebogenZurSymptomen2.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code", "vorhandenDefiningcode", VorhandenDefiningcode.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(AnzeichenObservationScreeningFragebogenZurSymptomen2.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AnzeichenObservationScreeningFragebogenZurSymptomen2.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(AnzeichenObservationScreeningFragebogenZurSymptomen2.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> ANZEICHEN = new ListAqlFieldImp<Cluster>(AnzeichenObservationScreeningFragebogenZurSymptomen2.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]", "anzeichen", Cluster.class, this);

  private AnzeichenObservationScreeningFragebogenZurSymptomen2Containment() {
    super("openEHR-EHR-OBSERVATION.symptom_sign_screening_schnupfen.v0");
  }

  public static AnzeichenObservationScreeningFragebogenZurSymptomen2Containment getInstance() {
    return new AnzeichenObservationScreeningFragebogenZurSymptomen2Containment();
  }
}
