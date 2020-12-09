package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.problem_qualifier.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:53.422285500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
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
