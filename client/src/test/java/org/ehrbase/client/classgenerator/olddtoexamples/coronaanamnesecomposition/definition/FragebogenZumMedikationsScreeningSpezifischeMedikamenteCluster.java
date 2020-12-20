package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster {
  @Path("/items[at0021]/value|value")
  private String nameDesMedikamentsValue;

  @Path("/items[at0024]/value|defining_code")
  private MedikamentInVerwendungDefiningcode medikamentInVerwendungDefiningcode;

  public void setNameDesMedikamentsValue(String nameDesMedikamentsValue) {
    this.nameDesMedikamentsValue = nameDesMedikamentsValue;
  }

  public String getNameDesMedikamentsValue() {
    return this.nameDesMedikamentsValue;
  }

  public void setMedikamentInVerwendungDefiningcode(
      MedikamentInVerwendungDefiningcode medikamentInVerwendungDefiningcode) {
    this.medikamentInVerwendungDefiningcode = medikamentInVerwendungDefiningcode;
  }

  public MedikamentInVerwendungDefiningcode getMedikamentInVerwendungDefiningcode() {
    return this.medikamentInVerwendungDefiningcode;
  }
}
