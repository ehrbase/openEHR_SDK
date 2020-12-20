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
    date = "2020-12-10T13:06:11.536499900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class News2Section implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Temperature Description: A measurement of the body
   * temperature, which is a surrogate for the whole body temperature of the person.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.body_temperature.v1 and name/value='Temperature']")
  private TemperatureObservation temperature;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry Description: Blood oxygen and related
   * measurements, measured by pulse oximetry or pulse CO-oximetry.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.pulse_oximetry.v1]")
  private PulseOximetryObservation pulseOximetry;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Pulse Description: Record details about the rate and
   * associated attributes for a pulse or heart beat.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.pulse.v1 and name/value='Pulse']")
  private PulseObservation pulse;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Respirations Description: The observed characteristics
   * of spontaneous breathing as would commonly be recorded as part of a 'vital signs' examination.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.respiration.v1]")
  private RespirationsObservation respirations;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/ACVPU scale Description: Simple scale used as part of
   * an assessment to measure and record an individual's level of consciousness. Comment: ACVPU is
   * an acronym for 'Alert', 'Confusion', 'Voice', 'Pain', 'Unresponsive'.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.acvpu.v0]")
  private AcvpuScaleObservation acvpuScale;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure Description: The local measurement of
   * arterial blood pressure which is a surrogate for arterial pressure in the systemic circulation.
   * Comment: Most commonly, use of the term 'blood pressure' refers to measurement of brachial
   * artery pressure in the upper arm.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.blood_pressure.v2]")
  private BloodPressureObservation bloodPressure;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score Description: A simple assessment score used
   * to identify clinical deterioration in a patient. Comment: This is the second version of the
   * National Early Warning Score (NEWS), issued by the UK Royal College of Physicians in 2017.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.news2.v1]")
  private News2ScoreObservation news2Score;

  /** Path: open_eREACT-Care/Assessment/NEWS2/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setTemperature(TemperatureObservation temperature) {
    this.temperature = temperature;
  }

  public TemperatureObservation getTemperature() {
    return this.temperature;
  }

  public void setPulseOximetry(PulseOximetryObservation pulseOximetry) {
    this.pulseOximetry = pulseOximetry;
  }

  public PulseOximetryObservation getPulseOximetry() {
    return this.pulseOximetry;
  }

  public void setPulse(PulseObservation pulse) {
    this.pulse = pulse;
  }

  public PulseObservation getPulse() {
    return this.pulse;
  }

  public void setRespirations(RespirationsObservation respirations) {
    this.respirations = respirations;
  }

  public RespirationsObservation getRespirations() {
    return this.respirations;
  }

  public void setAcvpuScale(AcvpuScaleObservation acvpuScale) {
    this.acvpuScale = acvpuScale;
  }

  public AcvpuScaleObservation getAcvpuScale() {
    return this.acvpuScale;
  }

  public void setBloodPressure(BloodPressureObservation bloodPressure) {
    this.bloodPressure = bloodPressure;
  }

  public BloodPressureObservation getBloodPressure() {
    return this.bloodPressure;
  }

  public void setNews2Score(News2ScoreObservation news2Score) {
    this.news2Score = news2Score;
  }

  public News2ScoreObservation getNews2Score() {
    return this.news2Score;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
