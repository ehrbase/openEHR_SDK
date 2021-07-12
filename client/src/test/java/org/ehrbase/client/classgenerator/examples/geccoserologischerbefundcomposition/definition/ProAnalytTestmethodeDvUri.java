package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

import java.net.URI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-05-19T16:20:30.089761100+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_URI")
public class ProAnalytTestmethodeDvUri implements RMEntity, ProAnalytTestmethodeChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro
   * Analyt/Testmethode/Testmethode Description: Die Beschreibung der Methode, mit der der Test nur
   * für diesen Analyten durchgeführt wurde.
   */
  @Path("|value")
  private URI testmethodeValue;

  public void setTestmethodeValue(URI testmethodeValue) {
    this.testmethodeValue = testmethodeValue;
  }

  public URI getTestmethodeValue() {
    return this.testmethodeValue;
  }
}
