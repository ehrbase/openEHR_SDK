package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.erregerdetails.v1")
public class ErregerdetailsCluster {
  @Path("/items[at0047]")
  private List<ErregerdetailsKeimSubtypElement> keimSubtyp;

  @Path("/items[at0035]/value|magnitude")
  private Double keimzahlMagnitude;

  @Path("/items[at0035]/value|units")
  private String keimzahlUnits;

  @Path("/items[at0003]/value")
  private DvOrdinal haufigkeit;

  @Path("/items[at0016]/value|value")
  private String virulenzfaktorValue;

  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0 and name/value='Antibiogramm']")
  private AntibiogrammCluster antibiogramm;

  @Path("/items[at0057]")
  private List<ErregerdetailsResistenzmechanismusCluster> resistenzmechanismus;

  @Path("/items[at0018]/value|defining_code")
  private MreKlasseDefiningCode mreKlasseDefiningCode;

  @Path("/items[at0062]/value|value")
  private String kommentarValue;

  @Path("/items[at0059]")
  private List<Cluster> weitereErganzungen;

  public void setKeimSubtyp(List<ErregerdetailsKeimSubtypElement> keimSubtyp) {
     this.keimSubtyp = keimSubtyp;
  }

  public List<ErregerdetailsKeimSubtypElement> getKeimSubtyp() {
     return this.keimSubtyp ;
  }

  public void setKeimzahlMagnitude(Double keimzahlMagnitude) {
     this.keimzahlMagnitude = keimzahlMagnitude;
  }

  public Double getKeimzahlMagnitude() {
     return this.keimzahlMagnitude ;
  }

  public void setKeimzahlUnits(String keimzahlUnits) {
     this.keimzahlUnits = keimzahlUnits;
  }

  public String getKeimzahlUnits() {
     return this.keimzahlUnits ;
  }

  public void setHaufigkeit(DvOrdinal haufigkeit) {
     this.haufigkeit = haufigkeit;
  }

  public DvOrdinal getHaufigkeit() {
     return this.haufigkeit ;
  }

  public void setVirulenzfaktorValue(String virulenzfaktorValue) {
     this.virulenzfaktorValue = virulenzfaktorValue;
  }

  public String getVirulenzfaktorValue() {
     return this.virulenzfaktorValue ;
  }

  public void setAntibiogramm(AntibiogrammCluster antibiogramm) {
     this.antibiogramm = antibiogramm;
  }

  public AntibiogrammCluster getAntibiogramm() {
     return this.antibiogramm ;
  }

  public void setResistenzmechanismus(
      List<ErregerdetailsResistenzmechanismusCluster> resistenzmechanismus) {
     this.resistenzmechanismus = resistenzmechanismus;
  }

  public List<ErregerdetailsResistenzmechanismusCluster> getResistenzmechanismus() {
     return this.resistenzmechanismus ;
  }

  public void setMreKlasseDefiningCode(MreKlasseDefiningCode mreKlasseDefiningCode) {
     this.mreKlasseDefiningCode = mreKlasseDefiningCode;
  }

  public MreKlasseDefiningCode getMreKlasseDefiningCode() {
     return this.mreKlasseDefiningCode ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setWeitereErganzungen(List<Cluster> weitereErganzungen) {
     this.weitereErganzungen = weitereErganzungen;
  }

  public List<Cluster> getWeitereErganzungen() {
     return this.weitereErganzungen ;
  }
}
