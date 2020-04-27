package laborbefund.laborbefundcomposition;

import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import laborbefund.laborbefundcomposition.definition.FallidentifikationCluster;
import laborbefund.laborbefundcomposition.definition.LaborergebnisObservation;
import laborbefund.shareddefinition.CategoryDefiningcode;
import laborbefund.shareddefinition.Language;
import laborbefund.shareddefinition.SettingDefiningcode;
import laborbefund.shareddefinition.Territory;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class LaborbefundCompositionContainment extends Containment {
  public SelectAqlField<LaborbefundComposition> LABORBEFUND_COMPOSITION = new AqlFieldImp<LaborbefundComposition>(LaborbefundComposition.class, "", "LaborbefundComposition", LaborbefundComposition.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(LaborbefundComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(LaborbefundComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(LaborbefundComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(LaborbefundComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<String> STATUS_VALUE = new AqlFieldImp<String>(LaborbefundComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "statusValue", String.class, this);

  public SelectAqlField<String> BERICHT_ID_VALUE = new AqlFieldImp<String>(LaborbefundComposition.class, "/context/other_context[at0001]/items[at0002]/value|value", "berichtIdValue", String.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(LaborbefundComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(LaborbefundComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<FallidentifikationCluster> FALLIDENTIFIKATION = new AqlFieldImp<FallidentifikationCluster>(LaborbefundComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]", "fallidentifikation", FallidentifikationCluster.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(LaborbefundComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(LaborbefundComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(LaborbefundComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(LaborbefundComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  public SelectAqlField<String> BERICHT_ID_VALUE_BAUM = new AqlFieldImp<String>(LaborbefundComposition.class, "/context/other_context[at0001]/items[at0002]/name|value", "berichtIdValueBaum", String.class, this);

  public ListSelectAqlField<LaborergebnisObservation> LABORERGEBNIS = new ListAqlFieldImp<LaborergebnisObservation>(LaborbefundComposition.class, "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]", "laborergebnis", LaborergebnisObservation.class, this);

  private LaborbefundCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report-result.v1");
  }

  public static LaborbefundCompositionContainment getInstance() {
    return new LaborbefundCompositionContainment();
  }
}
