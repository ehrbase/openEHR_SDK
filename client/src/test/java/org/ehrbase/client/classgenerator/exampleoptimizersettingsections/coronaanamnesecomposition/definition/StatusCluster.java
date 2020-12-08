package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.problem_qualifier.v1")
public class StatusCluster implements LocatableEntity {
  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Status/Diagnosestatus
   */
  @Path("/items[at0004]/value|defining_code")
  private DiagnosestatusDefiningCode diagnosestatusDefiningCode;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Status/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setDiagnosestatusDefiningCode(DiagnosestatusDefiningCode diagnosestatusDefiningCode) {
     this.diagnosestatusDefiningCode = diagnosestatusDefiningCode;
  }

  public DiagnosestatusDefiningCode getDiagnosestatusDefiningCode() {
     return this.diagnosestatusDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
