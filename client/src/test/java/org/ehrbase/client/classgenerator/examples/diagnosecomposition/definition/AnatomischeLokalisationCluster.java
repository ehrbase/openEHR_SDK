package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.anatomical_location.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.346027900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AnatomischeLokalisationCluster implements LocatableEntity {
  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Anatomische Lokalisation/Name der Körperstelle
   * Description: Identifikation einer einzelnen physischen Stelle entweder am oder innerhalb des
   * menschlichen Körpers.
   */
  @Path("/items[at0001]/value|value")
  private String nameDerKorperstelleValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Anatomische Lokalisation/Lateralität Description: Die
   * Seite des Körpers, an der sich die identifizierte Körperstelle befindet.
   */
  @Path("/items[at0002]/value|defining_code")
  private LateralitatDefiningCode lateralitatDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Anatomische Lokalisation/Alternative Struktur
   * Description: Zusätzliche Informationen über die anatomische Lage mit alternativen Ansätzen zur
   * Beschreibung der gleichen Körperstelle. Comment: Zum Beispiel, relative oder exakte Positionen
   * unter Verwendung von Koordinaten.
   */
  @Path("/items[at0053]")
  private List<Cluster> alternativeStruktur;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Anatomische Lokalisation/Multimediale Darstellung
   * Description: Bilder oder andere Medien, die der Identifizierung der Körperstelle dienen.
   */
  @Path("/items[at0054]")
  private List<Cluster> multimedialeDarstellung;

  /** Path: COVID-19-Diagnose/Problem/Diagnose/Anatomische Lokalisation/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameDerKorperstelleValue(String nameDerKorperstelleValue) {
    this.nameDerKorperstelleValue = nameDerKorperstelleValue;
  }

  public String getNameDerKorperstelleValue() {
    return this.nameDerKorperstelleValue;
  }

  public void setLateralitatDefiningCode(LateralitatDefiningCode lateralitatDefiningCode) {
    this.lateralitatDefiningCode = lateralitatDefiningCode;
  }

  public LateralitatDefiningCode getLateralitatDefiningCode() {
    return this.lateralitatDefiningCode;
  }

  public void setAlternativeStruktur(List<Cluster> alternativeStruktur) {
    this.alternativeStruktur = alternativeStruktur;
  }

  public List<Cluster> getAlternativeStruktur() {
    return this.alternativeStruktur;
  }

  public void setMultimedialeDarstellung(List<Cluster> multimedialeDarstellung) {
    this.multimedialeDarstellung = multimedialeDarstellung;
  }

  public List<Cluster> getMultimedialeDarstellung() {
    return this.multimedialeDarstellung;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
