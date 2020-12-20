package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.molekulare_typisierung.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.159025500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ErregertypisierungCluster implements LocatableEntity {
  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregertypisierung/Art der Typisierung Description: Angaben zu den durchgeführten
   * Typisierungsverfahren in der Infektionskontrolle.
   */
  @Path("/items[at0001]")
  private List<ErregertypisierungArtDerTypisierungElement> artDerTypisierung;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregertypisierung/Ergebnis Description: Ergebnisse der Bilddateien der Molekularen
   * Typisierung.
   */
  @Path("/items[at0008]")
  private List<ErregertypisierungErgebnisElement> ergebnis;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregertypisierung/Bewertung Description: Das Ergebnis bzw. die Bewertung der
   * Molekularen Typisierung.
   */
  @Path("/items[at0009]/value|value")
  private String bewertungValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregertypisierung/Kommentar Description: Zusätzliche Angaben zu der Molekularen
   * Typisierung.
   */
  @Path("/items[at0002]/value|value")
  private String kommentarValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregertypisierung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setArtDerTypisierung(
      List<ErregertypisierungArtDerTypisierungElement> artDerTypisierung) {
    this.artDerTypisierung = artDerTypisierung;
  }

  public List<ErregertypisierungArtDerTypisierungElement> getArtDerTypisierung() {
    return this.artDerTypisierung;
  }

  public void setErgebnis(List<ErregertypisierungErgebnisElement> ergebnis) {
    this.ergebnis = ergebnis;
  }

  public List<ErregertypisierungErgebnisElement> getErgebnis() {
    return this.ergebnis;
  }

  public void setBewertungValue(String bewertungValue) {
    this.bewertungValue = bewertungValue;
  }

  public String getBewertungValue() {
    return this.bewertungValue;
  }

  public void setKommentarValue(String kommentarValue) {
    this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
    return this.kommentarValue;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
