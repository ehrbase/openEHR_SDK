package org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-ADMIN_ENTRY.admission.v0")
public class PatientenaufnahmeAdminEntry {
  @Path("/data[at0001]/items[at0132]")
  private List<Cluster> vorherigerPatientenstandort;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[at0049]/value|value")
  private String artDerAufnahmeValue;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/items[at0013]/name|value")
  private String aufnahmegrundValue;

  @Path("/data[at0001]/items[at0131]")
  private List<Cluster> zugewiesenerPatientenstandort;

  @Path("/data[at0001]/items[at0013]/value|value")
  private String aufnahmegrundValueTree;

  @Path("/data[at0001]/items[at0071]/value|value")
  private TemporalAccessor uhrzeitDerAufnahmeValue;

    @Path("/name")
    private DvText name;

  public void setVorherigerPatientenstandort(List<Cluster> vorherigerPatientenstandort) {
     this.vorherigerPatientenstandort = vorherigerPatientenstandort;
  }

  public List<Cluster> getVorherigerPatientenstandort() {
     return this.vorherigerPatientenstandort ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setArtDerAufnahmeValue(String artDerAufnahmeValue) {
     this.artDerAufnahmeValue = artDerAufnahmeValue;
  }

  public String getArtDerAufnahmeValue() {
     return this.artDerAufnahmeValue ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setAufnahmegrundValue(String aufnahmegrundValue) {
     this.aufnahmegrundValue = aufnahmegrundValue;
  }

  public String getAufnahmegrundValue() {
     return this.aufnahmegrundValue ;
  }

  public void setZugewiesenerPatientenstandort(List<Cluster> zugewiesenerPatientenstandort) {
     this.zugewiesenerPatientenstandort = zugewiesenerPatientenstandort;
  }

  public List<Cluster> getZugewiesenerPatientenstandort() {
     return this.zugewiesenerPatientenstandort ;
  }

  public void setAufnahmegrundValueTree(String aufnahmegrundValueTree) {
     this.aufnahmegrundValueTree = aufnahmegrundValueTree;
  }

  public String getAufnahmegrundValueTree() {
     return this.aufnahmegrundValueTree ;
  }

  public void setUhrzeitDerAufnahmeValue(TemporalAccessor uhrzeitDerAufnahmeValue) {
     this.uhrzeitDerAufnahmeValue = uhrzeitDerAufnahmeValue;
  }

  public TemporalAccessor getUhrzeitDerAufnahmeValue() {
     return this.uhrzeitDerAufnahmeValue ;
  }

    public DvText getName() {
        return name;
    }

    public void setName(DvText name) {
        this.name = name;
    }
}
