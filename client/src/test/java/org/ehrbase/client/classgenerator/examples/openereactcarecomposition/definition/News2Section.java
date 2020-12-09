package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:51.650759200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class News2Section implements LocatableEntity {
  /**
   * open_eREACT-Care/Assessment/NEWS2/Temperature
   */
  @Path("/items[openEHR-EHR-OBSERVATION.body_temperature.v1 and name/value='Temperature']")
  private TemperatureObservation temperature;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry
   */
  @Path("/items[openEHR-EHR-OBSERVATION.pulse_oximetry.v1]")
  private PulseOximetryObservation pulseOximetry;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse
   */
  @Path("/items[openEHR-EHR-OBSERVATION.pulse.v1 and name/value='Pulse']")
  private PulseObservation pulse;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Respirations
   */
  @Path("/items[openEHR-EHR-OBSERVATION.respiration.v1]")
  private RespirationsObservation respirations;

  /**
   * open_eREACT-Care/Assessment/NEWS2/ACVPU scale
   */
  @Path("/items[openEHR-EHR-OBSERVATION.acvpu.v0]")
  private AcvpuScaleObservation acvpuScale;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Blood pressure
   */
  @Path("/items[openEHR-EHR-OBSERVATION.blood_pressure.v2]")
  private BloodPressureObservation bloodPressure;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score
   */
  @Path("/items[openEHR-EHR-OBSERVATION.news2.v1]")
  private News2ScoreObservation news2Score;

  /**
   * open_eREACT-Care/Assessment/NEWS2/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setTemperature(TemperatureObservation temperature) {
     this.temperature = temperature;
  }

  public TemperatureObservation getTemperature() {
     return this.temperature ;
  }

  public void setPulseOximetry(PulseOximetryObservation pulseOximetry) {
     this.pulseOximetry = pulseOximetry;
  }

  public PulseOximetryObservation getPulseOximetry() {
     return this.pulseOximetry ;
  }

  public void setPulse(PulseObservation pulse) {
     this.pulse = pulse;
  }

  public PulseObservation getPulse() {
     return this.pulse ;
  }

  public void setRespirations(RespirationsObservation respirations) {
     this.respirations = respirations;
  }

  public RespirationsObservation getRespirations() {
     return this.respirations ;
  }

  public void setAcvpuScale(AcvpuScaleObservation acvpuScale) {
     this.acvpuScale = acvpuScale;
  }

  public AcvpuScaleObservation getAcvpuScale() {
     return this.acvpuScale ;
  }

  public void setBloodPressure(BloodPressureObservation bloodPressure) {
     this.bloodPressure = bloodPressure;
  }

  public BloodPressureObservation getBloodPressure() {
     return this.bloodPressure ;
  }

  public void setNews2Score(News2ScoreObservation news2Score) {
     this.news2Score = news2Score;
  }

  public News2ScoreObservation getNews2Score() {
     return this.news2Score ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
