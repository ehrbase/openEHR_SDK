package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.molekulare_typisierung.v0")
public class ErregertypisierungCluster {
  @Path("/items[at0001]")
  private List<ErregertypisierungArtDerTypisierungElement> artDerTypisierung;

  @Path("/items[at0008]")
  private List<ErregertypisierungErgebnisElement> ergebnis;

  @Path("/items[at0009]/value|value")
  private String bewertungValue;

  @Path("/items[at0002]/value|value")
  private String kommentarValue;

  public void setArtDerTypisierung(
      List<ErregertypisierungArtDerTypisierungElement> artDerTypisierung) {
     this.artDerTypisierung = artDerTypisierung;
  }

  public List<ErregertypisierungArtDerTypisierungElement> getArtDerTypisierung() {
     return this.artDerTypisierung ;
  }

  public void setErgebnis(List<ErregertypisierungErgebnisElement> ergebnis) {
     this.ergebnis = ergebnis;
  }

  public List<ErregertypisierungErgebnisElement> getErgebnis() {
     return this.ergebnis ;
  }

  public void setBewertungValue(String bewertungValue) {
     this.bewertungValue = bewertungValue;
  }

  public String getBewertungValue() {
     return this.bewertungValue ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }
}
