package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.recommendation.v1")
public class RecommendationEvaluation {
  @Path("/data[at0001]/items[at0002]")
  private List<RecommendationRecommendationElement> recommendation;

  @Path("/protocol[at0004]/items[at0005]")
  private List<Cluster> extension;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setRecommendation(List<RecommendationRecommendationElement> recommendation) {
     this.recommendation = recommendation;
  }

  public List<RecommendationRecommendationElement> getRecommendation() {
     return this.recommendation ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
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
}
