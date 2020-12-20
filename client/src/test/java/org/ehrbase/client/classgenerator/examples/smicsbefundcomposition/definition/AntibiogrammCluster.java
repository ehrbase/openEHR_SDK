package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

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
    date = "2020-12-10T13:06:12.145022900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AntibiogrammCluster implements LocatableEntity {
  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat Description: Ergebnis eines
   * Labortests für einen bestimmten Analytwert. Comment: Beispiele: 'Natrium', 'Leukozytenzahl',
   * 'T3'. Üblicherweise über eine externe Terminologie codiert.
   */
  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]")
  private List<LaboranalytResultatCluster> laboranalytResultat;

  /**
   * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten
   * Erregers/Erregerdetails/Antibiogramm/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setLaboranalytResultat(List<LaboranalytResultatCluster> laboranalytResultat) {
    this.laboranalytResultat = laboranalytResultat;
  }

  public List<LaboranalytResultatCluster> getLaboranalytResultat() {
    return this.laboranalytResultat;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
