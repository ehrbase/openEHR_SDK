package org.ehrbase.client.classgenerator.examples.minimalevaluationenv1composition;

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
import org.ehrbase.client.classgenerator.examples.minimalevaluationenv1composition.definition.MinimalEvaluation;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

public class MinimalEvaluationEnV1CompositionContainment extends Containment {
  public SelectAqlField<MinimalEvaluationEnV1Composition> MINIMAL_EVALUATION_EN_V1_COMPOSITION = new AqlFieldImp<MinimalEvaluationEnV1Composition>(MinimalEvaluationEnV1Composition.class, "", "MinimalEvaluationEnV1Composition", MinimalEvaluationEnV1Composition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(MinimalEvaluationEnV1Composition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public ListSelectAqlField<MinimalEvaluation> MINIMAL = new ListAqlFieldImp<MinimalEvaluation>(MinimalEvaluationEnV1Composition.class, "/content[openEHR-EHR-EVALUATION.minimal.v1]", "minimal", MinimalEvaluation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(MinimalEvaluationEnV1Composition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(MinimalEvaluationEnV1Composition.class, "/language", "language", Language.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(MinimalEvaluationEnV1Composition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(MinimalEvaluationEnV1Composition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(MinimalEvaluationEnV1Composition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(MinimalEvaluationEnV1Composition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(MinimalEvaluationEnV1Composition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(MinimalEvaluationEnV1Composition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(MinimalEvaluationEnV1Composition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(MinimalEvaluationEnV1Composition.class, "/territory", "territory", Territory.class, this);

  private MinimalEvaluationEnV1CompositionContainment() {
    super("openEHR-EHR-COMPOSITION.minimal.v1");
  }

  public static MinimalEvaluationEnV1CompositionContainment getInstance() {
    return new MinimalEvaluationEnV1CompositionContainment();
  }
}
