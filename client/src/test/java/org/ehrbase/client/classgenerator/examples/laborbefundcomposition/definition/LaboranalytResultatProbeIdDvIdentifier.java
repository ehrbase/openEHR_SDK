package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-19T11:36:01.980469+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_IDENTIFIER")
public class LaboranalytResultatProbeIdDvIdentifier implements RMEntity, LaboranalytResultatProbeIdChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Probe ID/Probe ID
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   */
  @Path("")
  private DvIdentifier probeId;

  public void setProbeId(DvIdentifier probeId) {
     this.probeId = probeId;
  }

  public DvIdentifier getProbeId() {
     return this.probeId ;
  }
}
