package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.lang.String;
import java.net.URI;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-ADMIN_ENTRY.episodeofcare.v0")
public class EpisodeofcareAdminEntry implements EntryEntity {
  /**
   * EpisodeOfCare/Episodeofcare/identifier
   */
  @Path("/data[at0001]/items[at0002]")
  private List<EpisodeofcareIdentifierElement> identifier;

  /**
   * EpisodeOfCare/Episodeofcare/status
   */
  @Path("/data[at0001]/items[at0003]/value|defining_code")
  private StatusDefiningCode statusDefiningCode;

  /**
   * EpisodeOfCare/Episodeofcare/type
   */
  @Path("/data[at0001]/items[at0011]/value|value")
  private String typeValue;

  /**
   * EpisodeOfCare/Episodeofcare/period/upper
   */
  @Path("/data[at0001]/items[at0014]/value/upper|value")
  private TemporalAccessor upperValue;

  /**
   * EpisodeOfCare/Episodeofcare/period/lower
   */
  @Path("/data[at0001]/items[at0014]/value/lower|value")
  private TemporalAccessor lowerValue;

  /**
   * EpisodeOfCare/Episodeofcare/period/lower_included
   */
  @Path("/data[at0001]/items[at0014]/value/lower_included")
  private Boolean lowerIncluded;

  /**
   * EpisodeOfCare/Episodeofcare/period/upper_included
   */
  @Path("/data[at0001]/items[at0014]/value/upper_included")
  private Boolean upperIncluded;

  /**
   * EpisodeOfCare/Episodeofcare/diagnosis
   */
  @Path("/data[at0001]/items[at0018]")
  private List<EpisodeofcareDiagnosisCluster> diagnosis;

  /**
   * EpisodeOfCare/Episodeofcare/care manager
   */
  @Path("/data[at0001]/items[at0012]/value|value")
  private URI careManagerValue;

  /**
   * EpisodeOfCare/Episodeofcare/managing organization
   */
  @Path("/data[at0001]/items[at0017]/value|value")
  private URI managingOrganizationValue;

  /**
   * EpisodeOfCare/Episodeofcare/team
   */
  @Path("/data[at0001]/items[at0013]")
  private List<EpisodeofcareTeamElement> team;

  /**
   * EpisodeOfCare/Episodeofcare/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * EpisodeOfCare/Episodeofcare/language
   */
  @Path("/language")
  private Language language;

  /**
   * EpisodeOfCare/Episodeofcare/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setIdentifier(List<EpisodeofcareIdentifierElement> identifier) {
     this.identifier = identifier;
  }

  public List<EpisodeofcareIdentifierElement> getIdentifier() {
     return this.identifier ;
  }

  public void setStatusDefiningCode(StatusDefiningCode statusDefiningCode) {
     this.statusDefiningCode = statusDefiningCode;
  }

  public StatusDefiningCode getStatusDefiningCode() {
     return this.statusDefiningCode ;
  }

  public void setTypeValue(String typeValue) {
     this.typeValue = typeValue;
  }

  public String getTypeValue() {
     return this.typeValue ;
  }

  public void setUpperValue(TemporalAccessor upperValue) {
     this.upperValue = upperValue;
  }

  public TemporalAccessor getUpperValue() {
     return this.upperValue ;
  }

  public void setLowerValue(TemporalAccessor lowerValue) {
     this.lowerValue = lowerValue;
  }

  public TemporalAccessor getLowerValue() {
     return this.lowerValue ;
  }

  public void setLowerIncluded(Boolean lowerIncluded) {
     this.lowerIncluded = lowerIncluded;
  }

  public Boolean isLowerIncluded() {
     return this.lowerIncluded ;
  }

  public void setUpperIncluded(Boolean upperIncluded) {
     this.upperIncluded = upperIncluded;
  }

  public Boolean isUpperIncluded() {
     return this.upperIncluded ;
  }

  public void setDiagnosis(List<EpisodeofcareDiagnosisCluster> diagnosis) {
     this.diagnosis = diagnosis;
  }

  public List<EpisodeofcareDiagnosisCluster> getDiagnosis() {
     return this.diagnosis ;
  }

  public void setCareManagerValue(URI careManagerValue) {
     this.careManagerValue = careManagerValue;
  }

  public URI getCareManagerValue() {
     return this.careManagerValue ;
  }

  public void setManagingOrganizationValue(URI managingOrganizationValue) {
     this.managingOrganizationValue = managingOrganizationValue;
  }

  public URI getManagingOrganizationValue() {
     return this.managingOrganizationValue ;
  }

  public void setTeam(List<EpisodeofcareTeamElement> team) {
     this.team = team;
  }

  public List<EpisodeofcareTeamElement> getTeam() {
     return this.team ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
