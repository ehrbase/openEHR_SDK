package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
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
    date = "2020-12-10T13:06:13.770035700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ProVirusCluster implements LocatableEntity {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus/Analyseergebnis-Reihenfolge
   * Description: Die beabsichtigte Position dieses Analyseergebnisses in der Reihenfolge aller
   * Analyseergebnisse
   */
  @Path("/items[at0027]/value|magnitude")
  private Long analyseergebnisReihenfolgeMagnitude;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus/Virus Description: Die
   * Bezeichnung des Analyt-Resultats
   */
  @Path("/items[at0024 and name/value='Virus']/value|value")
  private String virusValue;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus/Nachweis Description:
   * (Mess-)Wert des Analyt-Resultats.
   */
  @Path("/items[at0001 and name/value='Nachweis']/value|value")
  private String nachweisValue;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus/Viruslast (ct-Wert)
   * Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("/items[at0001 and name/value='Viruslast (ct-Wert)']/value|magnitude")
  private Double viruslastCtWertMagnitude;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus/Viruslast (ct-Wert)
   * Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("/items[at0001 and name/value='Viruslast (ct-Wert)']/value|units")
  private String viruslastCtWertUnits;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus/Analyseergebnis-Details
   * Description: Weitere Details zu einem einzelnen Ergebnis.
   */
  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus/Zugehörige Laborprobe
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   */
  @Path("/items[at0026 and name/value='Zugehörige Laborprobe']/value")
  private DvIdentifier zugehorigeLaborprobe;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus/Kommentar Description:
   * Kommentar zum Analyt-Resultat, soweit noch nicht in anderen Feldern erfasst.
   */
  @Path("/items[at0003]/value|value")
  private String kommentarValue;

  /** Path: Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAnalyseergebnisReihenfolgeMagnitude(Long analyseergebnisReihenfolgeMagnitude) {
    this.analyseergebnisReihenfolgeMagnitude = analyseergebnisReihenfolgeMagnitude;
  }

  public Long getAnalyseergebnisReihenfolgeMagnitude() {
    return this.analyseergebnisReihenfolgeMagnitude;
  }

  public void setVirusValue(String virusValue) {
    this.virusValue = virusValue;
  }

  public String getVirusValue() {
    return this.virusValue;
  }

  public void setNachweisValue(String nachweisValue) {
    this.nachweisValue = nachweisValue;
  }

  public String getNachweisValue() {
    return this.nachweisValue;
  }

  public void setViruslastCtWertMagnitude(Double viruslastCtWertMagnitude) {
    this.viruslastCtWertMagnitude = viruslastCtWertMagnitude;
  }

  public Double getViruslastCtWertMagnitude() {
    return this.viruslastCtWertMagnitude;
  }

  public void setViruslastCtWertUnits(String viruslastCtWertUnits) {
    this.viruslastCtWertUnits = viruslastCtWertUnits;
  }

  public String getViruslastCtWertUnits() {
    return this.viruslastCtWertUnits;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
    this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
    return this.analyseergebnisDetails;
  }

  public void setZugehorigeLaborprobe(DvIdentifier zugehorigeLaborprobe) {
    this.zugehorigeLaborprobe = zugehorigeLaborprobe;
  }

  public DvIdentifier getZugehorigeLaborprobe() {
    return this.zugehorigeLaborprobe;
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
