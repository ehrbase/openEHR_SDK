package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvCodedText;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-05-19T16:20:30.084762700+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_CODED_TEXT")
public class ProAnalytTestmethodeDvCodedText implements RMEntity, ProAnalytTestmethodeChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro
   * Analyt/Testmethode/Testmethode Description: Die Beschreibung der Methode, mit der der Test nur
   * für diesen Analyten durchgeführt wurde.
   */
  @Path("")
  private DvCodedText testmethode;

  public void setTestmethode(DvCodedText testmethode) {
    this.testmethode = testmethode;
  }

  public DvCodedText getTestmethode() {
    return this.testmethode;
  }
}
