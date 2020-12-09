package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_panel.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:53.633287300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class KulturCluster implements LocatableEntity {
  /**
   * Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus
   */
  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Virus']")
  private List<ProVirusCluster> proVirus;

  /**
   * Virologischer Befund/Befund/Jedes Ereignis/Kultur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setProVirus(List<ProVirusCluster> proVirus) {
     this.proVirus = proVirus;
  }

  public List<ProVirusCluster> getProVirus() {
     return this.proVirus ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
