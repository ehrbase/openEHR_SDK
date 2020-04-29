package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.device.v1")
public class MedizingeratCluster {
  @Path("/items[at0005]/value|value")
  private TemporalAccessor herstellungsdatumValue;

  @Path("/items[at0006]/value|value")
  private String chargennummerValue;

  @Path("/items[at0021]/value")
  private DvIdentifier eindeutigeIdentifikationsnummerId;

  @Path("/items[at0022]/value|value")
  private String katalognummerValue;

  @Path("/items[at0023]/value|value")
  private String modellnummerValue;

  @Path("/items[at0003]/value|value")
  private String geratetypValue;

  @Path("/items[at0004]/value|value")
  private String herstellerValue;

  @Path("/items[at0007]/value|value")
  private TemporalAccessor ablaufdatumValue;

  @Path("/items[at0008]/value|value")
  private String kommentarValue;

  @Path("/items[at0009]")
  private List<Cluster> eigenschaften;

  @Path("/items[at0001]/value|value")
  private String geratenameValue;

  @Path("/items[at0002]/value|value")
  private String beschreibungValue;

  @Path("/items[at0024]")
  private List<MedizingeratWeitereIDElement> weitereId;

  @Path("/items[at0025]/value|value")
  private String softwareversionValue;

  @Path("/items[at0026]")
  private List<Cluster> erweiterung;

  @Path("/items[at0020]/value|value")
  private String seriennummerValue;

  @Path("/items[at0027]")
  private List<Cluster> multimedia;

  @Path("/items[at0019]")
  private List<Cluster> gerateverwaltung;

  @Path("/items[at0018]")
  private List<Cluster> komponenten;

  public void setHerstellungsdatumValue(TemporalAccessor herstellungsdatumValue) {
     this.herstellungsdatumValue = herstellungsdatumValue;
  }

  public TemporalAccessor getHerstellungsdatumValue() {
     return this.herstellungsdatumValue ;
  }

  public void setChargennummerValue(String chargennummerValue) {
     this.chargennummerValue = chargennummerValue;
  }

  public String getChargennummerValue() {
     return this.chargennummerValue ;
  }

  public void setEindeutigeIdentifikationsnummerId(DvIdentifier eindeutigeIdentifikationsnummerId) {
     this.eindeutigeIdentifikationsnummerId = eindeutigeIdentifikationsnummerId;
  }

  public DvIdentifier getEindeutigeIdentifikationsnummerId() {
     return this.eindeutigeIdentifikationsnummerId ;
  }

  public void setKatalognummerValue(String katalognummerValue) {
     this.katalognummerValue = katalognummerValue;
  }

  public String getKatalognummerValue() {
     return this.katalognummerValue ;
  }

  public void setModellnummerValue(String modellnummerValue) {
     this.modellnummerValue = modellnummerValue;
  }

  public String getModellnummerValue() {
     return this.modellnummerValue ;
  }

  public void setGeratetypValue(String geratetypValue) {
     this.geratetypValue = geratetypValue;
  }

  public String getGeratetypValue() {
     return this.geratetypValue ;
  }

  public void setHerstellerValue(String herstellerValue) {
     this.herstellerValue = herstellerValue;
  }

  public String getHerstellerValue() {
     return this.herstellerValue ;
  }

  public void setAblaufdatumValue(TemporalAccessor ablaufdatumValue) {
     this.ablaufdatumValue = ablaufdatumValue;
  }

  public TemporalAccessor getAblaufdatumValue() {
     return this.ablaufdatumValue ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setEigenschaften(List<Cluster> eigenschaften) {
     this.eigenschaften = eigenschaften;
  }

  public List<Cluster> getEigenschaften() {
     return this.eigenschaften ;
  }

  public void setGeratenameValue(String geratenameValue) {
     this.geratenameValue = geratenameValue;
  }

  public String getGeratenameValue() {
     return this.geratenameValue ;
  }

  public void setBeschreibungValue(String beschreibungValue) {
     this.beschreibungValue = beschreibungValue;
  }

  public String getBeschreibungValue() {
     return this.beschreibungValue ;
  }

  public void setWeitereId(List<MedizingeratWeitereIDElement> weitereId) {
     this.weitereId = weitereId;
  }

  public List<MedizingeratWeitereIDElement> getWeitereId() {
     return this.weitereId ;
  }

  public void setSoftwareversionValue(String softwareversionValue) {
     this.softwareversionValue = softwareversionValue;
  }

  public String getSoftwareversionValue() {
     return this.softwareversionValue ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setSeriennummerValue(String seriennummerValue) {
     this.seriennummerValue = seriennummerValue;
  }

  public String getSeriennummerValue() {
     return this.seriennummerValue ;
  }

  public void setMultimedia(List<Cluster> multimedia) {
     this.multimedia = multimedia;
  }

  public List<Cluster> getMultimedia() {
     return this.multimedia ;
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
}
