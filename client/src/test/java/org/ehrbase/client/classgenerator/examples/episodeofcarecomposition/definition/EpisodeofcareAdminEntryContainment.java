package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.net.URI;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class EpisodeofcareAdminEntryContainment extends Containment {
  public SelectAqlField<EpisodeofcareAdminEntry> EPISODEOFCARE_ADMIN_ENTRY =
      new AqlFieldImp<EpisodeofcareAdminEntry>(
          EpisodeofcareAdminEntry.class,
          "",
          "EpisodeofcareAdminEntry",
          EpisodeofcareAdminEntry.class,
          this);

  public ListSelectAqlField<EpisodeofcareIdentifierElement> IDENTIFIER =
      new ListAqlFieldImp<EpisodeofcareIdentifierElement>(
          EpisodeofcareAdminEntry.class,
          "/data[at0001]/items[at0002]",
          "identifier",
          EpisodeofcareIdentifierElement.class,
          this);

  public SelectAqlField<StatusDefiningCode> STATUS_DEFINING_CODE =
      new AqlFieldImp<StatusDefiningCode>(
          EpisodeofcareAdminEntry.class,
          "/data[at0001]/items[at0003]/value|defining_code",
          "statusDefiningCode",
          StatusDefiningCode.class,
          this);

  public SelectAqlField<String> TYPE_VALUE =
      new AqlFieldImp<String>(
          EpisodeofcareAdminEntry.class,
          "/data[at0001]/items[at0011]/value|value",
          "typeValue",
          String.class,
          this);

  public SelectAqlField<TemporalAccessor> UPPER_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          EpisodeofcareAdminEntry.class,
          "/data[at0001]/items[at0014]/value/upper|value",
          "upperValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> LOWER_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          EpisodeofcareAdminEntry.class,
          "/data[at0001]/items[at0014]/value/lower|value",
          "lowerValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<Boolean> LOWER_INCLUDED =
      new AqlFieldImp<Boolean>(
          EpisodeofcareAdminEntry.class,
          "/data[at0001]/items[at0014]/value/lower_included",
          "lowerIncluded",
          Boolean.class,
          this);

  public SelectAqlField<Boolean> UPPER_INCLUDED =
      new AqlFieldImp<Boolean>(
          EpisodeofcareAdminEntry.class,
          "/data[at0001]/items[at0014]/value/upper_included",
          "upperIncluded",
          Boolean.class,
          this);

  public ListSelectAqlField<EpisodeofcareDiagnosisCluster> DIAGNOSIS =
      new ListAqlFieldImp<EpisodeofcareDiagnosisCluster>(
          EpisodeofcareAdminEntry.class,
          "/data[at0001]/items[at0018]",
          "diagnosis",
          EpisodeofcareDiagnosisCluster.class,
          this);

  public SelectAqlField<URI> CARE_MANAGER_VALUE =
      new AqlFieldImp<URI>(
          EpisodeofcareAdminEntry.class,
          "/data[at0001]/items[at0012]/value|value",
          "careManagerValue",
          URI.class,
          this);

  public SelectAqlField<URI> MANAGING_ORGANIZATION_VALUE =
      new AqlFieldImp<URI>(
          EpisodeofcareAdminEntry.class,
          "/data[at0001]/items[at0017]/value|value",
          "managingOrganizationValue",
          URI.class,
          this);

  public ListSelectAqlField<EpisodeofcareTeamElement> TEAM =
      new ListAqlFieldImp<EpisodeofcareTeamElement>(
          EpisodeofcareAdminEntry.class,
          "/data[at0001]/items[at0013]",
          "team",
          EpisodeofcareTeamElement.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          EpisodeofcareAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          EpisodeofcareAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          EpisodeofcareAdminEntry.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private EpisodeofcareAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.episodeofcare.v0");
  }

  public static EpisodeofcareAdminEntryContainment getInstance() {
    return new EpisodeofcareAdminEntryContainment();
  }
}
