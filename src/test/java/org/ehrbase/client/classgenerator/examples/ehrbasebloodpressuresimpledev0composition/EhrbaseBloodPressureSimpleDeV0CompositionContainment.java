package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition;

import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservation;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.DeviceDetailsTrainingSampleCluster;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;

import java.time.temporal.TemporalAccessor;

public class EhrbaseBloodPressureSimpleDeV0CompositionContainment extends Containment {
  public SelectAqlField<EhrbaseBloodPressureSimpleDeV0Composition> EHRBASE_BLOOD_PRESSURE_SIMPLE_DE_V0_COMPOSITION = new AqlFieldImp<EhrbaseBloodPressureSimpleDeV0Composition>(EhrbaseBloodPressureSimpleDeV0Composition.class, "", "EhrbaseBloodPressureSimpleDeV0Composition", EhrbaseBloodPressureSimpleDeV0Composition.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/territory", "territory", Territory.class, this);

  public ListSelectAqlField<BloodPressureTrainingSampleObservation> BLOOD_PRESSURE_TRAINING_SAMPLE = new ListAqlFieldImp<BloodPressureTrainingSampleObservation>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]", "bloodPressureTrainingSample", BloodPressureTrainingSampleObservation.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  public ListSelectAqlField<DeviceDetailsTrainingSampleCluster> DEVICE_DETAILS_TRAINING_SAMPLE = new ListAqlFieldImp<DeviceDetailsTrainingSampleCluster>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/context/other_context[at0001]/items[at0006]/items[openEHR-EHR-CLUSTER.sample_device.v1]", "deviceDetailsTrainingSample", DeviceDetailsTrainingSampleCluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(EhrbaseBloodPressureSimpleDeV0Composition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  private EhrbaseBloodPressureSimpleDeV0CompositionContainment() {
    super("openEHR-EHR-COMPOSITION.sample_encounter.v1");
  }

  public static EhrbaseBloodPressureSimpleDeV0CompositionContainment getInstance() {
    return new EhrbaseBloodPressureSimpleDeV0CompositionContainment();
  }
}
