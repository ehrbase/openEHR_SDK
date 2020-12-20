package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.net.URI;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-ADMIN_ENTRY.episodeofcare.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.181498500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class EpisodeofcareAdminEntry implements EntryEntity {
  /**
   * Path: EpisodeOfCare/Episodeofcare/identifier Description: Business Identifier(s) relevant for
   * this EpisodeOfCare
   */
  @Path("/data[at0001]/items[at0002]")
  private List<EpisodeofcareIdentifierElement> identifier;

  /** Path: EpisodeOfCare/Episodeofcare/status Description: * */
  @Path("/data[at0001]/items[at0003]/value|defining_code")
  private StatusDefiningCode statusDefiningCode;

  /**
   * Path: EpisodeOfCare/Episodeofcare/type Description: Type/class - e.g. specialist referral,
   * disease management
   */
  @Path("/data[at0001]/items[at0011]/value|value")
  private String typeValue;

  /** Path: EpisodeOfCare/Episodeofcare/period/upper */
  @Path("/data[at0001]/items[at0014]/value/upper|value")
  private TemporalAccessor upperValue;

  /** Path: EpisodeOfCare/Episodeofcare/period/lower */
  @Path("/data[at0001]/items[at0014]/value/lower|value")
  private TemporalAccessor lowerValue;

  /** Path: EpisodeOfCare/Episodeofcare/period/lower_included */
  @Path("/data[at0001]/items[at0014]/value/lower_included")
  private Boolean lowerIncluded;

  /** Path: EpisodeOfCare/Episodeofcare/period/upper_included */
  @Path("/data[at0001]/items[at0014]/value/upper_included")
  private Boolean upperIncluded;

  /**
   * Path: EpisodeOfCare/Episodeofcare/diagnosis Description: The list of diagnosis relevant to this
   * episode of care
   */
  @Path("/data[at0001]/items[at0018]")
  private List<EpisodeofcareDiagnosisCluster> diagnosis;

  /**
   * Path: EpisodeOfCare/Episodeofcare/care manager Description: Care manager/care coordinator for
   * the patient
   */
  @Path("/data[at0001]/items[at0012]/value|value")
  private URI careManagerValue;

  /** Path: EpisodeOfCare/Episodeofcare/managing organization Description: * */
  @Path("/data[at0001]/items[at0017]/value|value")
  private URI managingOrganizationValue;

  /**
   * Path: EpisodeOfCare/Episodeofcare/team Description: Other practitioners facilitating this
   * episode of care
   */
  @Path("/data[at0001]/items[at0013]")
  private List<EpisodeofcareTeamElement> team;

  /** Path: EpisodeOfCare/Episodeofcare/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: EpisodeOfCare/Episodeofcare/language */
  @Path("/language")
  private Language language;

  /** Path: EpisodeOfCare/Episodeofcare/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setIdentifier(List<EpisodeofcareIdentifierElement> identifier) {
    this.identifier = identifier;
  }

  public List<EpisodeofcareIdentifierElement> getIdentifier() {
    return this.identifier;
  }

  public void setStatusDefiningCode(StatusDefiningCode statusDefiningCode) {
    this.statusDefiningCode = statusDefiningCode;
  }

  public StatusDefiningCode getStatusDefiningCode() {
    return this.statusDefiningCode;
  }

  public void setTypeValue(String typeValue) {
    this.typeValue = typeValue;
  }

  public String getTypeValue() {
    return this.typeValue;
  }

  public void setUpperValue(TemporalAccessor upperValue) {
    this.upperValue = upperValue;
  }

  public TemporalAccessor getUpperValue() {
    return this.upperValue;
  }

  public void setLowerValue(TemporalAccessor lowerValue) {
    this.lowerValue = lowerValue;
  }

  public TemporalAccessor getLowerValue() {
    return this.lowerValue;
  }

  public void setLowerIncluded(Boolean lowerIncluded) {
    this.lowerIncluded = lowerIncluded;
  }

  public Boolean isLowerIncluded() {
    return this.lowerIncluded;
  }

  public void setUpperIncluded(Boolean upperIncluded) {
    this.upperIncluded = upperIncluded;
  }

  public Boolean isUpperIncluded() {
    return this.upperIncluded;
  }

  public void setDiagnosis(List<EpisodeofcareDiagnosisCluster> diagnosis) {
    this.diagnosis = diagnosis;
  }

  public List<EpisodeofcareDiagnosisCluster> getDiagnosis() {
    return this.diagnosis;
  }

  public void setCareManagerValue(URI careManagerValue) {
    this.careManagerValue = careManagerValue;
  }

  public URI getCareManagerValue() {
    return this.careManagerValue;
  }

  public void setManagingOrganizationValue(URI managingOrganizationValue) {
    this.managingOrganizationValue = managingOrganizationValue;
  }

  public URI getManagingOrganizationValue() {
    return this.managingOrganizationValue;
  }

  public void setTeam(List<EpisodeofcareTeamElement> team) {
    this.team = team;
  }

  public List<EpisodeofcareTeamElement> getTeam() {
    return this.team;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
