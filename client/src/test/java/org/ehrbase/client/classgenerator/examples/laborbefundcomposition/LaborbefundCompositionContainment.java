package org.ehrbase.client.classgenerator.examples.laborbefundcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition.FallidentifikationCluster;
import org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

public class LaborbefundCompositionContainment extends Containment {
  public SelectAqlField<LaborbefundComposition> LABORBEFUND_COMPOSITION = new AqlFieldImp<LaborbefundComposition>(LaborbefundComposition.class, "", "LaborbefundComposition", LaborbefundComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(LaborbefundComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<String> IDENTIFIKATOR_DES_LABORBEFUNDS_VALUE = new AqlFieldImp<String>(LaborbefundComposition.class, "/context/other_context[at0001]/items[at0002]/value|value", "identifikatorDesLaborbefundsValue", String.class, this);

  public SelectAqlField<NullFlavour> IDENTIFIKATOR_DES_LABORBEFUNDS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LaborbefundComposition.class, "/context/other_context[at0001]/items[at0002]/null_flavour|defining_code", "identifikatorDesLaborbefundsNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> STATUS_VALUE = new AqlFieldImp<String>(LaborbefundComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "statusValue", String.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LaborbefundComposition.class, "/context/other_context[at0001]/items[at0005]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FallidentifikationCluster> FALLIDENTIFIKATION = new AqlFieldImp<FallidentifikationCluster>(LaborbefundComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]", "fallidentifikation", FallidentifikationCluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(LaborbefundComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(LaborbefundComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(LaborbefundComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(LaborbefundComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(LaborbefundComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(LaborbefundComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public ListSelectAqlField<LaborergebnisObservation> LABORERGEBNIS = new ListAqlFieldImp<LaborergebnisObservation>(LaborbefundComposition.class, "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]", "laborergebnis", LaborergebnisObservation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(LaborbefundComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(LaborbefundComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(LaborbefundComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(LaborbefundComposition.class, "/territory", "territory", Territory.class, this);

  private LaborbefundCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report-result.v1");
  }

  public static LaborbefundCompositionContainment getInstance() {
    return new LaborbefundCompositionContainment();
  }
}
