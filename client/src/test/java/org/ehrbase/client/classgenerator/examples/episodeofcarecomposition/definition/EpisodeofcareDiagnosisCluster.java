package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvEHRURI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.183500400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class EpisodeofcareDiagnosisCluster implements LocatableEntity {
  /**
   * Path: EpisodeOfCare/Episodeofcare/diagnosis/diagnosis entry Description:
   * Conditions/problems/diagnoses this episode of care is for
   */
  @Path("/items[at0019]/value")
  private DvEHRURI diagnosisEntry;

  /**
   * Path: EpisodeOfCare/Episodeofcare/diagnosis/role Description: Role that this diagnosis has
   * within the episode of care (e.g. admission, billing, discharge...)
   */
  @Path("/items[at0020]/value|value")
  private String roleValue;

  /**
   * Path: EpisodeOfCare/Episodeofcare/diagnosis/rank Description: Ranking of the diagnosis (for
   * each role type)
   */
  @Path("/items[at0021]/value|magnitude")
  private Long rankMagnitude;

  /** Path: EpisodeOfCare/Episodeofcare/diagnosis/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setDiagnosisEntry(DvEHRURI diagnosisEntry) {
    this.diagnosisEntry = diagnosisEntry;
  }

  public DvEHRURI getDiagnosisEntry() {
    return this.diagnosisEntry;
  }

  public void setRoleValue(String roleValue) {
    this.roleValue = roleValue;
  }

  public String getRoleValue() {
    return this.roleValue;
  }

  public void setRankMagnitude(Long rankMagnitude) {
    this.rankMagnitude = rankMagnitude;
  }

  public Long getRankMagnitude() {
    return this.rankMagnitude;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
