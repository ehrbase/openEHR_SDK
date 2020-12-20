package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition.AllgemeineAngabenSection;
import org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition.GeschichteHistorieObservation;
import org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition.KontaktSection;
import org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition.RisikogebietSection;
import org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition.SymptomeSection;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Territory;

public class CoronaAnamneseCompositionContainment extends Containment {
  public SelectAqlField<CoronaAnamneseComposition> CORONA_ANAMNESE_COMPOSITION =
      new AqlFieldImp<CoronaAnamneseComposition>(
          CoronaAnamneseComposition.class,
          "",
          "CoronaAnamneseComposition",
          CoronaAnamneseComposition.class,
          this);

  public SelectAqlField<RisikogebietSection> RISIKOGEBIET =
      new AqlFieldImp<RisikogebietSection>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']",
          "risikogebiet",
          RisikogebietSection.class,
          this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/context/end_time|value",
          "endTimeValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Participation> PARTICIPATIONS =
      new ListAqlFieldImp<Participation>(
          CoronaAnamneseComposition.class,
          "/context/participations",
          "participations",
          Participation.class,
          this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY =
      new AqlFieldImp<PartyIdentified>(
          CoronaAnamneseComposition.class,
          "/context/health_care_facility",
          "healthCareFacility",
          PartyIdentified.class,
          this);

  public SelectAqlField<String> STATUS_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/context/other_context[at0001]/items[at0005]/value|value",
          "statusValue",
          String.class,
          this);

  public ListSelectAqlField<GeschichteHistorieObservation> GESCHICHTE_HISTORIE =
      new ListAqlFieldImp<GeschichteHistorieObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-OBSERVATION.story.v1]",
          "geschichteHistorie",
          GeschichteHistorieObservation.class,
          this);

  public SelectAqlField<AllgemeineAngabenSection> ALLGEMEINE_ANGABEN =
      new AqlFieldImp<AllgemeineAngabenSection>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']",
          "allgemeineAngaben",
          AllgemeineAngabenSection.class,
          this);

  public SelectAqlField<String> BERICHT_ID_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/context/other_context[at0001]/items[at0002]/value|value",
          "berichtIdValue",
          String.class,
          this);

  public SelectAqlField<Territory> TERRITORY =
      new AqlFieldImp<Territory>(
          CoronaAnamneseComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/context/start_time|value",
          "startTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<SymptomeSection> SYMPTOME =
      new AqlFieldImp<SymptomeSection>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']",
          "symptome",
          SymptomeSection.class,
          this);

  public SelectAqlField<KontaktSection> KONTAKT =
      new AqlFieldImp<KontaktSection>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']",
          "kontakt",
          KontaktSection.class,
          this);

  public SelectAqlField<PartyProxy> COMPOSER =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE =
      new AqlFieldImp<SettingDefiningcode>(
          CoronaAnamneseComposition.class,
          "/context/setting|defining_code",
          "settingDefiningcode",
          SettingDefiningcode.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          CoronaAnamneseComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/context/other_context[at0001]/items[at0006]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<String> LOCATION =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE =
      new AqlFieldImp<CategoryDefiningcode>(
          CoronaAnamneseComposition.class,
          "/category|defining_code",
          "categoryDefiningcode",
          CategoryDefiningcode.class,
          this);

  private CoronaAnamneseCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static CoronaAnamneseCompositionContainment getInstance() {
    return new CoronaAnamneseCompositionContainment();
  }
}
