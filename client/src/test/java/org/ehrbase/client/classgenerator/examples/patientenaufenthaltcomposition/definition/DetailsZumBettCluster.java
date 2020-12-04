package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.device.v1")
public class DetailsZumBettCluster {
  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Gerätename
   */
  @Path("/items[at0001]/value|value")
  private String geratenameValue;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Gerätetyp
   */
  @Path("/items[at0003]/value|value")
  private String geratetypValue;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Eigenschaften
   */
  @Path("/items[at0009]")
  private List<Cluster> eigenschaften;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Eindeutige Identifikationsnummer (ID)
   */
  @Path("/items[at0021]/value")
  private DvIdentifier eindeutigeIdentifikationsnummerId;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Geräteverwaltung
   */
  @Path("/items[at0019]")
  private List<Cluster> gerateverwaltung;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Komponenten
   */
  @Path("/items[at0018]")
  private List<Cluster> komponenten;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Erweiterung
   */
  @Path("/items[at0026]")
  private List<Cluster> erweiterung;

  /**
   * Patientenaufenthalt/Versorgungsort/Standort/Details zum Bett/Multimedia
   */
  @Path("/items[at0027]")
  private List<Cluster> multimedia;

  public void setGeratenameValue(String geratenameValue) {
     this.geratenameValue = geratenameValue;
  }

  public String getGeratenameValue() {
     return this.geratenameValue ;
  }

  public void setGeratetypValue(String geratetypValue) {
     this.geratetypValue = geratetypValue;
  }

  public String getGeratetypValue() {
     return this.geratetypValue ;
  }

  public void setEigenschaften(List<Cluster> eigenschaften) {
     this.eigenschaften = eigenschaften;
  }

  public List<Cluster> getEigenschaften() {
     return this.eigenschaften ;
  }

  public void setEindeutigeIdentifikationsnummerId(DvIdentifier eindeutigeIdentifikationsnummerId) {
     this.eindeutigeIdentifikationsnummerId = eindeutigeIdentifikationsnummerId;
  }

  public DvIdentifier getEindeutigeIdentifikationsnummerId() {
     return this.eindeutigeIdentifikationsnummerId ;
  }

  public void setGerateverwaltung(List<Cluster> gerateverwaltung) {
     this.gerateverwaltung = gerateverwaltung;
  }

  public List<Cluster> getGerateverwaltung() {
     return this.gerateverwaltung ;
  }

  public void setKomponenten(List<Cluster> komponenten) {
     this.komponenten = komponenten;
  }

  public List<Cluster> getKomponenten() {
     return this.komponenten ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setMultimedia(List<Cluster> multimedia) {
     this.multimedia = multimedia;
  }

  public List<Cluster> getMultimedia() {
     return this.multimedia ;
  }
}
