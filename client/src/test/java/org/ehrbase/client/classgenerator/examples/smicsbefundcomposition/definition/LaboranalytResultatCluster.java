package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.147024700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class LaboranalytResultatCluster implements LocatableEntity {
  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Antibiotikum Description: Die
   * Bezeichnung des Analyt-Resultats
   */
  @Path("/items[at0024 and name/value='Antibiotikum']/value|defining_code")
  private AntibiotikumDefiningCode antibiotikumDefiningCode;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Minimale Hemmkonzentration
   * Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("/items[at0001 and name/value='Minimale Hemmkonzentration']/value|magnitude")
  private Double minimaleHemmkonzentrationMagnitude;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Minimale Hemmkonzentration
   * Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("/items[at0001 and name/value='Minimale Hemmkonzentration']/value|units")
  private String minimaleHemmkonzentrationUnits;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Analyseergebnis-Details Description:
   * Weitere Details zu einem einzelnen Ergebnis.
   */
  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Resistenz Description: Zusätzliche
   * Hinweise zur Anwendbarkeit des Referenzbereichs für dieses Resultat oder (codierter) Text, ob
   * das Resultat im Referenzbereich ist oder nicht.
   */
  @Path("/items[at0004 and name/value='Resistenz']/value|value")
  private String resistenzValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Kommentar Description: Kommentar zum
   * Analyt-Resultat, soweit noch nicht in anderen Feldern erfasst.
   */
  @Path("/items[at0003]/value|value")
  private String kommentarValue;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAntibiotikumDefiningCode(AntibiotikumDefiningCode antibiotikumDefiningCode) {
    this.antibiotikumDefiningCode = antibiotikumDefiningCode;
  }

  public AntibiotikumDefiningCode getAntibiotikumDefiningCode() {
    return this.antibiotikumDefiningCode;
  }

  public void setMinimaleHemmkonzentrationMagnitude(Double minimaleHemmkonzentrationMagnitude) {
    this.minimaleHemmkonzentrationMagnitude = minimaleHemmkonzentrationMagnitude;
  }

  public Double getMinimaleHemmkonzentrationMagnitude() {
    return this.minimaleHemmkonzentrationMagnitude;
  }

  public void setMinimaleHemmkonzentrationUnits(String minimaleHemmkonzentrationUnits) {
    this.minimaleHemmkonzentrationUnits = minimaleHemmkonzentrationUnits;
  }

  public String getMinimaleHemmkonzentrationUnits() {
    return this.minimaleHemmkonzentrationUnits;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
    this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
    return this.analyseergebnisDetails;
  }

  public void setResistenzValue(String resistenzValue) {
    this.resistenzValue = resistenzValue;
  }

  public String getResistenzValue() {
    return this.resistenzValue;
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
