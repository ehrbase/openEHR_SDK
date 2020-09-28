package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class News2Section {
    @Path("/items[openEHR-EHR-OBSERVATION.pulse_oximetry.v1]")
    private PulseOximetryObservation pulseOximetry;

    @Path("/items[openEHR-EHR-OBSERVATION.acvpu.v0]")
    private AcvpuScaleObservation acvpuScale;

    @Path("/items[openEHR-EHR-OBSERVATION.blood_pressure.v2]")
    private BloodPressureObservation bloodPressure;

    @Path("/items[openEHR-EHR-OBSERVATION.body_temperature.v1 and name/value='Temperature']")
    private TemperatureObservation temperature;

    @Path("/items[openEHR-EHR-OBSERVATION.respiration.v1]")
    private RespirationsObservation respirations;

    @Path("/items[openEHR-EHR-OBSERVATION.pulse.v1 and name/value='Pulse']")
    private PulseObservation pulse;

    @Path("/items[openEHR-EHR-OBSERVATION.news2.v1]")
    private News2ScoreObservation news2Score;

    public void setPulseOximetry(PulseOximetryObservation pulseOximetry) {
        this.pulseOximetry = pulseOximetry;
    }

    public PulseOximetryObservation getPulseOximetry() {
        return this.pulseOximetry;
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

    public void setTemperature(TemperatureObservation temperature) {
        this.temperature = temperature;
    }

    public TemperatureObservation getTemperature() {
        return this.temperature;
    }

    public void setRespirations(RespirationsObservation respirations) {
        this.respirations = respirations;
    }

    public RespirationsObservation getRespirations() {
        return this.respirations;
    }

    public void setPulse(PulseObservation pulse) {
        this.pulse = pulse;
    }

    public PulseObservation getPulse() {
        return this.pulse;
    }

    public void setNews2Score(News2ScoreObservation news2Score) {
        this.news2Score = news2Score;
    }

    public News2ScoreObservation getNews2Score() {
        return this.news2Score;
    }
}
