package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

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
    date = "2020-12-10T13:06:12.624027600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class StatusCluster implements LocatableEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Status/Diagnosestatus
   * Description: Stadium oder Phase des diagnostischen Prozesses.
   */
  @Path("/items[at0004]/value|defining_code")
  private DiagnosestatusDefiningCode diagnosestatusDefiningCode;

  /** Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Status/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setDiagnosestatusDefiningCode(DiagnosestatusDefiningCode diagnosestatusDefiningCode) {
    this.diagnosestatusDefiningCode = diagnosestatusDefiningCode;
  }

  public DiagnosestatusDefiningCode getDiagnosestatusDefiningCode() {
    return this.diagnosestatusDefiningCode;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
