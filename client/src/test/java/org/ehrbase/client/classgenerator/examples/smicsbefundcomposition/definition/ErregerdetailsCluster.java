package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.erregerdetails.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.141022800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ErregerdetailsCluster implements LocatableEntity {
  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Keim Subtyp Description: Subtyp, welcher zusätzlich zur
   * Speziesidentifizierung zur weiteren Kennzeichnung des Erregers genutzt werden kann, z.B.
   * spa-Typ im Falle von S. aureus oder MLST-Typ. Comment: Bestimmte Keimsubtypen beeinflussen die
   * Wirtsreaktion bzw. Immunantwort. Beispielsweise ein Resultat einer spa-Typsierung bei einem S.
   * aureus oder anderen Typsierungen wie MLST.
   */
  @Path("/items[at0047]")
  private List<ErregerdetailsKeimSubtypElement> keimSubtyp;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Keimzahl Description: Quantitative Angabe zur Keimzahl, z.B. bei Urinen
   */
  @Path("/items[at0035]/value|magnitude")
  private Double keimzahlMagnitude;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Keimzahl Description: Quantitative Angabe zur Keimzahl, z.B. bei Urinen
   */
  @Path("/items[at0035]/value|units")
  private String keimzahlUnits;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Häufigkeit Description: Semiquantitative Angabe zur Keimzahl.
   */
  @Path("/items[at0003]/value")
  private DvOrdinal haufigkeit;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Virulenzfaktor Description: Angabe zu untersuchten
   * Virulenzeigenschaften oder -genen, z.B. PVL bei S. aureus oder EHEC bei E. coli.
   */
  @Path("/items[at0016]/value|value")
  private String virulenzfaktorValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Antibiogramm Description: Laborergebnis als Panel/Profil von
   * Einzelresultaten. Verbreitet im medizinischen Labor.
   */
  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0 and name/value='Antibiogramm']")
  private AntibiogrammCluster antibiogramm;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Resistenzmechanismus Description: Angabe der bei dem Erreger
   * vorliegenden Resistenzmechanismen, z.B. ESBL oder Carbapenemase.
   */
  @Path("/items[at0057]")
  private List<ErregerdetailsResistenzmechanismusCluster> resistenzmechanismus;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/MRE Klasse Description: Angabe zur MRE-Klassifikation des Erregers,
   * z.B. 3MRGN oder 4MRGN. Bei VRE und MRSA kann es zu einer Redundanz zum Resistenzmechanismus
   * "Methicillin-resistenz" kommen, dies ist aber problemlos.
   */
  @Path("/items[at0018]/value|defining_code")
  private MreKlasseDefiningCode mreKlasseDefiningCode;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Kommentar Description: Zusätzliche Infomationen zum Erreger.
   */
  @Path("/items[at0062]/value|value")
  private String kommentarValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Weitere Ergänzungen Description: Der Cluster dient dazu, weitere
   * Ergänzungen zum Archetyp Erregerdetails aufzunehmen.
   */
  @Path("/items[at0059]")
  private List<Cluster> weitereErganzungen;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setKeimSubtyp(List<ErregerdetailsKeimSubtypElement> keimSubtyp) {
    this.keimSubtyp = keimSubtyp;
  }

  public List<ErregerdetailsKeimSubtypElement> getKeimSubtyp() {
    return this.keimSubtyp;
  }

  public void setKeimzahlMagnitude(Double keimzahlMagnitude) {
    this.keimzahlMagnitude = keimzahlMagnitude;
  }

  public Double getKeimzahlMagnitude() {
    return this.keimzahlMagnitude;
  }

  public void setKeimzahlUnits(String keimzahlUnits) {
    this.keimzahlUnits = keimzahlUnits;
  }

  public String getKeimzahlUnits() {
    return this.keimzahlUnits;
  }

  public void setHaufigkeit(DvOrdinal haufigkeit) {
    this.haufigkeit = haufigkeit;
  }

  public DvOrdinal getHaufigkeit() {
    return this.haufigkeit;
  }

  public void setVirulenzfaktorValue(String virulenzfaktorValue) {
    this.virulenzfaktorValue = virulenzfaktorValue;
  }

  public String getVirulenzfaktorValue() {
    return this.virulenzfaktorValue;
  }

  public void setAntibiogramm(AntibiogrammCluster antibiogramm) {
    this.antibiogramm = antibiogramm;
  }

  public AntibiogrammCluster getAntibiogramm() {
    return this.antibiogramm;
  }

  public void setResistenzmechanismus(
      List<ErregerdetailsResistenzmechanismusCluster> resistenzmechanismus) {
    this.resistenzmechanismus = resistenzmechanismus;
  }

  public List<ErregerdetailsResistenzmechanismusCluster> getResistenzmechanismus() {
    return this.resistenzmechanismus;
  }

  public void setMreKlasseDefiningCode(MreKlasseDefiningCode mreKlasseDefiningCode) {
    this.mreKlasseDefiningCode = mreKlasseDefiningCode;
  }

  public MreKlasseDefiningCode getMreKlasseDefiningCode() {
    return this.mreKlasseDefiningCode;
  }

  public void setKommentarValue(String kommentarValue) {
    this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
    return this.kommentarValue;
  }

  public void setWeitereErganzungen(List<Cluster> weitereErganzungen) {
    this.weitereErganzungen = weitereErganzungen;
  }

  public List<Cluster> getWeitereErganzungen() {
    return this.weitereErganzungen;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
