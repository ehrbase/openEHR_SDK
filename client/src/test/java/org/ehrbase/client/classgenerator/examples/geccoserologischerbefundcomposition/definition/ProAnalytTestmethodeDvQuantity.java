package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-05-19T16:20:30.092760900+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_QUANTITY")
public class ProAnalytTestmethodeDvQuantity implements RMEntity, ProAnalytTestmethodeChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro
   * Analyt/Testmethode/Testmethode Description: Die Beschreibung der Methode, mit der der Test nur
   * für diesen Analyten durchgeführt wurde.
   */
  @Path("|magnitude")
  private Double testmethodeMagnitude;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro
   * Analyt/Testmethode/Testmethode Description: Die Beschreibung der Methode, mit der der Test nur
   * für diesen Analyten durchgeführt wurde.
   */
  @Path("|units")
  private String testmethodeUnits;

  public void setTestmethodeMagnitude(Double testmethodeMagnitude) {
    this.testmethodeMagnitude = testmethodeMagnitude;
  }

  public Double getTestmethodeMagnitude() {
    return this.testmethodeMagnitude;
  }

  public void setTestmethodeUnits(String testmethodeUnits) {
    this.testmethodeUnits = testmethodeUnits;
  }

  public String getTestmethodeUnits() {
    return this.testmethodeUnits;
  }
}
