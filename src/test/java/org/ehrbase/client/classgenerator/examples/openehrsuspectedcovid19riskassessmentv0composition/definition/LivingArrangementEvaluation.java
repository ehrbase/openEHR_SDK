package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.living_arrangement.v0")
public class LivingArrangementEvaluation {
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.dwelling.v0]")
  private List<DwellingCluster> dwelling;

  @Path("/data[at0001]/items[at0003]/value|value")
  private String descriptionValue;

  @Path("/data[at0001]/items[at0007]/value|magnitude")
  private Long numberOfHouseholdMembersMagnitude;

  @Path("/protocol[at0002]/items[at0011]")
  private List<Cluster> extension;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setDwelling(List<DwellingCluster> dwelling) {
     this.dwelling = dwelling;
  }

  public List<DwellingCluster> getDwelling() {
     return this.dwelling ;
  }

  public void setDescriptionValue(String descriptionValue) {
     this.descriptionValue = descriptionValue;
  }

  public String getDescriptionValue() {
     return this.descriptionValue ;
  }

  public void setNumberOfHouseholdMembersMagnitude(Long numberOfHouseholdMembersMagnitude) {
     this.numberOfHouseholdMembersMagnitude = numberOfHouseholdMembersMagnitude;
  }

  public Long getNumberOfHouseholdMembersMagnitude() {
     return this.numberOfHouseholdMembersMagnitude ;
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
