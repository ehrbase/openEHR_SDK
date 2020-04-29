package org.ehrbase.client.classgenerator.examples.allergiescomposition;

import com.nedap.archie.rm.datastructures.Cluster;
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
import org.ehrbase.client.classgenerator.examples.allergiescomposition.definition.AdverseReactionRiskEvaluation;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;

public class AllergiesCompositionContainment extends Containment {
  public SelectAqlField<AllergiesComposition> ALLERGIES_COMPOSITION = new AqlFieldImp<AllergiesComposition>(AllergiesComposition.class, "", "AllergiesComposition", AllergiesComposition.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(AllergiesComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(AllergiesComposition.class, "/context/participations", "participations", Participation.class, this);

  public ListSelectAqlField<AdverseReactionRiskEvaluation> ADVERSE_REACTION_RISK = new ListAqlFieldImp<AdverseReactionRiskEvaluation>(AllergiesComposition.class, "/content[openEHR-EHR-EVALUATION.adverse_reaction_risk.v1]", "adverseReactionRisk", AdverseReactionRiskEvaluation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AllergiesComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(AllergiesComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(AllergiesComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(AllergiesComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(AllergiesComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(AllergiesComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(AllergiesComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(AllergiesComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(AllergiesComposition.class, "/context/other_context[at0001]/items[at0002]", "extension", Cluster.class, this);

  private AllergiesCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.adverse_reaction_list.v1");
  }

  public static AllergiesCompositionContainment getInstance() {
    return new AllergiesCompositionContainment();
  }
}
