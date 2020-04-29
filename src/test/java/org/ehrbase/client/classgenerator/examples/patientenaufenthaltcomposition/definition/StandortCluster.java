package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import java.lang.String;

import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.StandortschlusselDefiningcode;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
public class StandortCluster {
  @Path("/items[at0027]/name|value")
  private String stationValue;

  @Path("/items[at0024]/value|value")
  private String campusValue;

  @Path("/items[at0029]/name|value")
  private String zimmerValue;

  @Path("/items[at0034]/value|value")
  private String bettstellplatzValue;

  @Path("/items[at0040]/value|value")
  private String standorttypValue;

  @Path("/items[at0046]/value|value")
  private String standortbeschreibungValue;

  @Path("/items[at0048]/value|defining_code")
  private StandortschlusselDefiningcode standortschlusselDefiningcode;

  @Path("/items[openEHR-EHR-CLUSTER.device.v1]")
  private MedizingeratCluster medizingerat;

  @Path("/items[at0034]/name|value")
  private String bettstellplatzValueName;

  @Path("/items[at0029]/value|value")
  private String zimmerValueValue;

  @Path("/items[at0028]/value|value")
  private String ebeneValue;

  @Path("/items[at0025]/value|value")
  private String gebaudegruppeValue;

  @Path("/items[at0027]/value|value")
  private String stationValueValue;

    @Path("/name")
    private DvText name;

  public void setStationValue(String stationValue) {
     this.stationValue = stationValue;
  }

  public String getStationValue() {
     return this.stationValue ;
  }

  public void setCampusValue(String campusValue) {
     this.campusValue = campusValue;
  }

  public String getCampusValue() {
     return this.campusValue ;
  }

  public void setZimmerValue(String zimmerValue) {
     this.zimmerValue = zimmerValue;
  }

  public String getZimmerValue() {
     return this.zimmerValue ;
  }

  public void setBettstellplatzValue(String bettstellplatzValue) {
     this.bettstellplatzValue = bettstellplatzValue;
  }

  public String getBettstellplatzValue() {
     return this.bettstellplatzValue ;
  }

  public void setStandorttypValue(String standorttypValue) {
     this.standorttypValue = standorttypValue;
  }

  public String getStandorttypValue() {
     return this.standorttypValue ;
  }

  public void setStandortbeschreibungValue(String standortbeschreibungValue) {
     this.standortbeschreibungValue = standortbeschreibungValue;
  }

  public String getStandortbeschreibungValue() {
     return this.standortbeschreibungValue ;
  }

  public void setStandortschlusselDefiningcode(
      StandortschlusselDefiningcode standortschlusselDefiningcode) {
     this.standortschlusselDefiningcode = standortschlusselDefiningcode;
  }

  public StandortschlusselDefiningcode getStandortschlusselDefiningcode() {
     return this.standortschlusselDefiningcode ;
  }

  public void setMedizingerat(MedizingeratCluster medizingerat) {
     this.medizingerat = medizingerat;
  }

  public MedizingeratCluster getMedizingerat() {
     return this.medizingerat ;
  }

  public void setBettstellplatzValueName(String bettstellplatzValueName) {
     this.bettstellplatzValueName = bettstellplatzValueName;
  }

  public String getBettstellplatzValueName() {
     return this.bettstellplatzValueName ;
  }

  public void setZimmerValueValue(String zimmerValueValue) {
     this.zimmerValueValue = zimmerValueValue;
  }

  public String getZimmerValueValue() {
     return this.zimmerValueValue ;
  }

  public void setEbeneValue(String ebeneValue) {
     this.ebeneValue = ebeneValue;
  }

  public String getEbeneValue() {
     return this.ebeneValue ;
  }

  public void setGebaudegruppeValue(String gebaudegruppeValue) {
     this.gebaudegruppeValue = gebaudegruppeValue;
  }

  public String getGebaudegruppeValue() {
     return this.gebaudegruppeValue ;
  }

  public void setStationValueValue(String stationValueValue) {
     this.stationValueValue = stationValueValue;
  }

  public String getStationValueValue() {
     return this.stationValueValue ;
  }

    public DvText getName() {
        return name;
    }

    public void setName(DvText name) {
        this.name = name;
    }
}
