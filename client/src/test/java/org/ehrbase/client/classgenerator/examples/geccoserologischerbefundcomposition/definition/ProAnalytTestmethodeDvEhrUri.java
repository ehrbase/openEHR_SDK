package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvEHRURI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-05-19T16:20:30.090761700+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_EHR_URI")
public class ProAnalytTestmethodeDvEhrUri implements RMEntity, ProAnalytTestmethodeChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro
   * Analyt/Testmethode/Testmethode Description: Die Beschreibung der Methode, mit der der Test nur
   * für diesen Analyten durchgeführt wurde.
   */
  @Path("")
  private DvEHRURI testmethode;

  public void setTestmethode(DvEHRURI testmethode) {
    this.testmethode = testmethode;
  }

  public DvEHRURI getTestmethode() {
    return this.testmethode;
  }
}
