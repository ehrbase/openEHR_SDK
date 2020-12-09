package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import java.lang.Double;
import java.lang.String;
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
    date = "2020-12-09T11:37:52.195763300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class ErregerdetailsCluster implements LocatableEntity {
  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Keim Subtyp
   */
  @Path("/items[at0047]")
  private List<ErregerdetailsKeimSubtypElement> keimSubtyp;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/value
   */
  @Path("/items[at0035]/value|magnitude")
  private Double keimzahlMagnitude;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/value
   */
  @Path("/items[at0035]/value|units")
  private String keimzahlUnits;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Häufigkeit
   */
  @Path("/items[at0003]/value")
  private DvOrdinal haufigkeit;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Virulenzfaktor
   */
  @Path("/items[at0016]/value|value")
  private String virulenzfaktorValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Antibiogramm
   */
  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0 and name/value='Antibiogramm']")
  private AntibiogrammCluster antibiogramm;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Resistenzmechanismus
   */
  @Path("/items[at0057]")
  private List<ErregerdetailsResistenzmechanismusCluster> resistenzmechanismus;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/MRE Klasse
   */
  @Path("/items[at0018]/value|defining_code")
  private MreKlasseDefiningCode mreKlasseDefiningCode;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Kommentar
   */
  @Path("/items[at0062]/value|value")
  private String kommentarValue;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Weitere Ergänzungen
   */
  @Path("/items[at0059]")
  private List<Cluster> weitereErganzungen;

  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

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

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
