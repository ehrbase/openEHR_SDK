package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvEHRURI;
import java.lang.Long;
import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class EpisodeofcareDiagnosisCluster {
  /**
   * EpisodeOfCare/Episodeofcare/diagnosis/diagnosis entry
   */
  @Path("/items[at0019]/value")
  private DvEHRURI diagnosisEntry;

  /**
   * EpisodeOfCare/Episodeofcare/diagnosis/role
   */
  @Path("/items[at0020]/value|value")
  private String roleValue;

  /**
   * EpisodeOfCare/Episodeofcare/diagnosis/rank
   */
  @Path("/items[at0021]/value|magnitude")
  private Long rankMagnitude;

  /**
   * EpisodeOfCare/Episodeofcare/diagnosis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setDiagnosisEntry(DvEHRURI diagnosisEntry) {
     this.diagnosisEntry = diagnosisEntry;
  }

  public DvEHRURI getDiagnosisEntry() {
     return this.diagnosisEntry ;
  }

  public void setRoleValue(String roleValue) {
     this.roleValue = roleValue;
  }

  public String getRoleValue() {
     return this.roleValue ;
  }

  public void setRankMagnitude(Long rankMagnitude) {
     this.rankMagnitude = rankMagnitude;
  }

  public Long getRankMagnitude() {
     return this.rankMagnitude ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
