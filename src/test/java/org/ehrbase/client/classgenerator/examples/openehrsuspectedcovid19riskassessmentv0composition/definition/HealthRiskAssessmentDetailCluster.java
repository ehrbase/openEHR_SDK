package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("CLUSTER")
public class HealthRiskAssessmentDetailCluster implements HealthRiskAssessmentDetailChoice {
  @Path("")
  private List<Cluster> detail;

  public void setDetail(List<Cluster> detail) {
     this.detail = detail;
  }

  public List<Cluster> getDetail() {
     return this.detail ;
  }
}
