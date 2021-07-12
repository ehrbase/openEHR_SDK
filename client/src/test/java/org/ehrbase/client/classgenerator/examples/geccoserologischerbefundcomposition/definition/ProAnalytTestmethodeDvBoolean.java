package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-05-19T16:20:30.087761200+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_BOOLEAN")
public class ProAnalytTestmethodeDvBoolean implements RMEntity, ProAnalytTestmethodeChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro
   * Analyt/Testmethode/Testmethode Description: Die Beschreibung der Methode, mit der der Test nur
   * für diesen Analyten durchgeführt wurde.
   */
  @Path("|value")
  private Boolean testmethodeValue;

  public void setTestmethodeValue(Boolean testmethodeValue) {
    this.testmethodeValue = testmethodeValue;
  }

  public Boolean isTestmethodeValue() {
    return this.testmethodeValue;
  }
}
