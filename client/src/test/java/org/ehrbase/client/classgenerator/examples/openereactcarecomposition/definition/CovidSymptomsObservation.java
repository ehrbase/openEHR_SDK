package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.story.v1")
public class CovidSymptomsObservation {
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|value")
  private String storyValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='First symptoms']")
  private FirstSymptomsCluster firstSymptoms;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='Key Covid symptom']")
  private List<KeyCovidSymptomCluster> keyCovidSymptom;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='Chest symptom']")
  private List<ChestSymptomCluster> chestSymptom;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='Abdomen symptom']")
  private List<AbdomenSymptomCluster> abdomenSymptom;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='Neuro symptom']")
  private List<NeuroSymptomCluster> neuroSymptom;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign-cvid.v0 and name/value='Other symptom']")
  private List<OtherSymptomCluster> otherSymptom;

  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  @Path("/protocol[at0007]/items[at0008]")
  private List<Cluster> extension;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setStoryValue(String storyValue) {
     this.storyValue = storyValue;
  }

  public String getStoryValue() {
     return this.storyValue ;
  }

  public void setFirstSymptoms(FirstSymptomsCluster firstSymptoms) {
     this.firstSymptoms = firstSymptoms;
  }

  public FirstSymptomsCluster getFirstSymptoms() {
     return this.firstSymptoms ;
  }

  public void setKeyCovidSymptom(List<KeyCovidSymptomCluster> keyCovidSymptom) {
     this.keyCovidSymptom = keyCovidSymptom;
  }

  public List<KeyCovidSymptomCluster> getKeyCovidSymptom() {
     return this.keyCovidSymptom ;
  }

  public void setChestSymptom(List<ChestSymptomCluster> chestSymptom) {
     this.chestSymptom = chestSymptom;
  }

  public List<ChestSymptomCluster> getChestSymptom() {
     return this.chestSymptom ;
  }

  public void setAbdomenSymptom(List<AbdomenSymptomCluster> abdomenSymptom) {
     this.abdomenSymptom = abdomenSymptom;
  }

  public List<AbdomenSymptomCluster> getAbdomenSymptom() {
     return this.abdomenSymptom ;
  }

  public void setNeuroSymptom(List<NeuroSymptomCluster> neuroSymptom) {
     this.neuroSymptom = neuroSymptom;
  }

  public List<NeuroSymptomCluster> getNeuroSymptom() {
     return this.neuroSymptom ;
  }

  public void setOtherSymptom(List<OtherSymptomCluster> otherSymptom) {
     this.otherSymptom = otherSymptom;
  }

  public List<OtherSymptomCluster> getOtherSymptom() {
     return this.otherSymptom ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
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
