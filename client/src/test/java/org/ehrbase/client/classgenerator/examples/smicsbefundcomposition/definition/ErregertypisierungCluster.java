package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.molekulare_typisierung.v0")
public class ErregertypisierungCluster {
  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregertypisierung/Art der Typisierung
   */
  @Path("/items[at0001]")
  private List<ErregertypisierungArtDerTypisierungElement> artDerTypisierung;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregertypisierung/Ergebnis
   */
  @Path("/items[at0008]")
  private List<ErregertypisierungErgebnisElement> ergebnis;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregertypisierung/Bewertung
   */
  @Path("/items[at0009]/value|value")
  private String bewertungValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregertypisierung/Kommentar
   */
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
