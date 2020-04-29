package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition;

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
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.AufenthaltsdatenAdminEntry;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.PatientenaufenthaltOrgEhrbaseEhrEncodeWrappersSnakecase6ab72419FallidentifikationChoice;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;

public class PatientenaufenthaltCompositionContainment extends Containment {
  public SelectAqlField<PatientenaufenthaltComposition> PATIENTENAUFENTHALT_COMPOSITION = new AqlFieldImp<PatientenaufenthaltComposition>(PatientenaufenthaltComposition.class, "", "PatientenaufenthaltComposition", PatientenaufenthaltComposition.class, this);

  public SelectAqlField<PatientenaufenthaltOrgEhrbaseEhrEncodeWrappersSnakecase6ab72419FallidentifikationChoice> FALLIDENTIFIKATION = new AqlFieldImp<PatientenaufenthaltOrgEhrbaseEhrEncodeWrappersSnakecase6ab72419FallidentifikationChoice>(PatientenaufenthaltComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]", "fallidentifikation", PatientenaufenthaltOrgEhrbaseEhrEncodeWrappersSnakecase6ab72419FallidentifikationChoice.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(PatientenaufenthaltComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(PatientenaufenthaltComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(PatientenaufenthaltComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(PatientenaufenthaltComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(PatientenaufenthaltComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<AufenthaltsdatenAdminEntry> AUFENTHALTSDATEN = new AqlFieldImp<AufenthaltsdatenAdminEntry>(PatientenaufenthaltComposition.class, "/content[openEHR-EHR-ADMIN_ENTRY.hospitalization.v0]", "aufenthaltsdaten", AufenthaltsdatenAdminEntry.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(PatientenaufenthaltComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(PatientenaufenthaltComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(PatientenaufenthaltComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(PatientenaufenthaltComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(PatientenaufenthaltComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  private PatientenaufenthaltCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.event_summary.v0");
  }

  public static PatientenaufenthaltCompositionContainment getInstance() {
    return new PatientenaufenthaltCompositionContainment();
  }
}
