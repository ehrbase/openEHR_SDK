package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import com.nedap.archie.rm.datavalues.DvEHRURI;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class EpisodeofcareDiagnosisCluster {
  @Path("/items[at0019]/value")
  private DvEHRURI diagnosisEntry;

  @Path("/items[at0020]/value|value")
  private String roleValue;

  @Path("/items[at0021]/value|magnitude")
  private Long rankMagnitude;

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
}
