package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Boolean;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.diagnose_details.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:52.386760900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class DiagnosedetailsCluster implements LocatableEntity {
  /**
   * COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Begründung von Ausnahmen
   */
  @Path("/items[at0001]/value|value")
  private String begrundungVonAusnahmenValue;

  /**
   * COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Diagnosetyp
   */
  @Path("/items[at0002]/value|value")
  private String diagnosetypValue;

  /**
   * COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Vorhanden bei Aufnahme
   */
  @Path("/items[at0016]/value|value")
  private Boolean vorhandenBeiAufnahmeValue;

  /**
   * COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Vorhanden bei Entlassung
   */
  @Path("/items[at0017]/value|value")
  private Boolean vorhandenBeiEntlassungValue;

  /**
   * COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBegrundungVonAusnahmenValue(String begrundungVonAusnahmenValue) {
     this.begrundungVonAusnahmenValue = begrundungVonAusnahmenValue;
  }

  public String getBegrundungVonAusnahmenValue() {
     return this.begrundungVonAusnahmenValue ;
  }

  public void setDiagnosetypValue(String diagnosetypValue) {
     this.diagnosetypValue = diagnosetypValue;
  }

  public String getDiagnosetypValue() {
     return this.diagnosetypValue ;
  }

  public void setVorhandenBeiAufnahmeValue(Boolean vorhandenBeiAufnahmeValue) {
     this.vorhandenBeiAufnahmeValue = vorhandenBeiAufnahmeValue;
  }

  public Boolean isVorhandenBeiAufnahmeValue() {
     return this.vorhandenBeiAufnahmeValue ;
  }

  public void setVorhandenBeiEntlassungValue(Boolean vorhandenBeiEntlassungValue) {
     this.vorhandenBeiEntlassungValue = vorhandenBeiEntlassungValue;
  }

  public Boolean isVorhandenBeiEntlassungValue() {
     return this.vorhandenBeiEntlassungValue ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
