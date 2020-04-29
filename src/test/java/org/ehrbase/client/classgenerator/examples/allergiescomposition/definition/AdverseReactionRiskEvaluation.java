package org.ehrbase.client.classgenerator.examples.allergiescomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.adverse_reaction_risk.v1")
public class AdverseReactionRiskEvaluation {
  @Path("/data[at0001]/items[at0063]/value|defining_code")
  private CodePhrase statusDefiningcode;

  @Path("/data[at0001]/items[at0002]/value|value")
  private String substanceValue;

  @Path("/data[at0001]/items[at0006]/value|value")
  private String commentValue;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[at0058]/value|defining_code")
  private CodePhrase reactionMechanismDefiningcode;

  @Path("/protocol[at0042]/items[at0128]")
  private List<Cluster> extension;

  @Path("/data[at0001]/items[at0120]/value|defining_code")
  private CodePhrase categoryDefiningcode;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/items[at0101]/value|defining_code")
  private CriticalityDefiningcode criticalityDefiningcode;

  public void setStatusDefiningcode(CodePhrase statusDefiningcode) {
     this.statusDefiningcode = statusDefiningcode;
  }

  public CodePhrase getStatusDefiningcode() {
     return this.statusDefiningcode ;
  }

  public void setSubstanceValue(String substanceValue) {
     this.substanceValue = substanceValue;
  }

  public String getSubstanceValue() {
     return this.substanceValue ;
  }

  public void setCommentValue(String commentValue) {
     this.commentValue = commentValue;
  }

  public String getCommentValue() {
     return this.commentValue ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setReactionMechanismDefiningcode(CodePhrase reactionMechanismDefiningcode) {
     this.reactionMechanismDefiningcode = reactionMechanismDefiningcode;
  }

  public CodePhrase getReactionMechanismDefiningcode() {
     return this.reactionMechanismDefiningcode ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setCategoryDefiningcode(CodePhrase categoryDefiningcode) {
     this.categoryDefiningcode = categoryDefiningcode;
  }

  public CodePhrase getCategoryDefiningcode() {
     return this.categoryDefiningcode ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setCriticalityDefiningcode(CriticalityDefiningcode criticalityDefiningcode) {
     this.criticalityDefiningcode = criticalityDefiningcode;
  }

  public CriticalityDefiningcode getCriticalityDefiningcode() {
     return this.criticalityDefiningcode ;
  }
}
