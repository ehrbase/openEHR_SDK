package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

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
@Archetype("openEHR-EHR-CLUSTER.device.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.906064400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class DetailsZumBettCluster implements LocatableEntity {
  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Gerätename Description:
   * Identifizierung des Medizingerätes, bevorzugt durch einen allgemein gebräuchlichen Namen, einer
   * formellen und vollständig beschreibenden Bezeichnung oder falls notwendig anhand einer Klasse
   * oder Kategorie des Gerätes.
   */
  @Path("/items[at0001]/value|value")
  private String geratenameValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Gerätetyp Description: Die
   * Kategorie des Medizingeräts.
   */
  @Path("/items[at0003]/value|value")
  private String geratetypValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Eigenschaften Description:
   * Weitere Details zu bestimmten Eigenschaften des Medizingerätes.
   */
  @Path("/items[at0009]")
  private List<Cluster> eigenschaften;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Eindeutige
   * Identifikationsnummer (ID) Description: Eine numerische oder alphanumerische Zeichenfolge, die
   * diesem Gerät in einem bestimmten System zugeordnet ist.
   */
  @Path("/items[at0021]/value")
  private DvIdentifier eindeutigeIdentifikationsnummerId;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Geräteverwaltung
   * Description: Weitere Details zur Verwaltung und Wartung des Geräts. Comment: Zum Beispiel:
   * Eigentümer, Kontaktdaten, Standort, Netzwerkadresse, Ersetzungsdatum, Kalibrierungsdetails usw.
   */
  @Path("/items[at0019]")
  private List<Cluster> gerateverwaltung;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Komponenten Description:
   * Zusätzliche strukturierte Informationen zu identifizierten Komponenten des Geräts.
   */
  @Path("/items[at0018]")
  private List<Cluster> komponenten;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Erweiterung Description:
   * Zusätzliche Informationen, die zur Erfassung des lokalen Kontexts oder zur Angleichung an
   * andere Referenzmodelle/Formalismen erforderlich sind.
   */
  @Path("/items[at0026]")
  private List<Cluster> erweiterung;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Multimedia Description:
   * Digitale Repräsentation des Gerätes. Comment: Zum Beispiel: ein technisches Diagramm eines
   * Geräts oder ein digitales Bild.
   */
  @Path("/items[at0027]")
  private List<Cluster> multimedia;

  /** Path: Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setGeratenameValue(String geratenameValue) {
    this.geratenameValue = geratenameValue;
  }

  public String getGeratenameValue() {
    return this.geratenameValue;
  }

  public void setGeratetypValue(String geratetypValue) {
    this.geratetypValue = geratetypValue;
  }

  public String getGeratetypValue() {
    return this.geratetypValue;
  }

  public void setEigenschaften(List<Cluster> eigenschaften) {
    this.eigenschaften = eigenschaften;
  }

  public List<Cluster> getEigenschaften() {
    return this.eigenschaften;
  }

  public void setEindeutigeIdentifikationsnummerId(DvIdentifier eindeutigeIdentifikationsnummerId) {
    this.eindeutigeIdentifikationsnummerId = eindeutigeIdentifikationsnummerId;
  }

  public DvIdentifier getEindeutigeIdentifikationsnummerId() {
    return this.eindeutigeIdentifikationsnummerId;
  }

  public void setGerateverwaltung(List<Cluster> gerateverwaltung) {
    this.gerateverwaltung = gerateverwaltung;
  }

  public List<Cluster> getGerateverwaltung() {
    return this.gerateverwaltung;
  }

  public void setKomponenten(List<Cluster> komponenten) {
    this.komponenten = komponenten;
  }

  public List<Cluster> getKomponenten() {
    return this.komponenten;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
    this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
    return this.erweiterung;
  }

  public void setMultimedia(List<Cluster> multimedia) {
    this.multimedia = multimedia;
  }

  public List<Cluster> getMultimedia() {
    return this.multimedia;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
