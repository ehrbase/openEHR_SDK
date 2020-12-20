package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class News2SectionContainment extends Containment {
  public SelectAqlField<News2Section> NEWS2_SECTION =
      new AqlFieldImp<News2Section>(
          News2Section.class, "", "News2Section", News2Section.class, this);

  public SelectAqlField<TemperatureObservation> TEMPERATURE =
      new AqlFieldImp<TemperatureObservation>(
          News2Section.class,
          "/items[openEHR-EHR-OBSERVATION.body_temperature.v1]",
          "temperature",
          TemperatureObservation.class,
          this);

  public SelectAqlField<PulseOximetryObservation> PULSE_OXIMETRY =
      new AqlFieldImp<PulseOximetryObservation>(
          News2Section.class,
          "/items[openEHR-EHR-OBSERVATION.pulse_oximetry.v1]",
          "pulseOximetry",
          PulseOximetryObservation.class,
          this);

  public SelectAqlField<PulseObservation> PULSE =
      new AqlFieldImp<PulseObservation>(
          News2Section.class,
          "/items[openEHR-EHR-OBSERVATION.pulse.v1]",
          "pulse",
          PulseObservation.class,
          this);

  public SelectAqlField<RespirationsObservation> RESPIRATIONS =
      new AqlFieldImp<RespirationsObservation>(
          News2Section.class,
          "/items[openEHR-EHR-OBSERVATION.respiration.v1]",
          "respirations",
          RespirationsObservation.class,
          this);

  public SelectAqlField<AcvpuScaleObservation> ACVPU_SCALE =
      new AqlFieldImp<AcvpuScaleObservation>(
          News2Section.class,
          "/items[openEHR-EHR-OBSERVATION.acvpu.v0]",
          "acvpuScale",
          AcvpuScaleObservation.class,
          this);

  public SelectAqlField<BloodPressureObservation> BLOOD_PRESSURE =
      new AqlFieldImp<BloodPressureObservation>(
          News2Section.class,
          "/items[openEHR-EHR-OBSERVATION.blood_pressure.v2]",
          "bloodPressure",
          BloodPressureObservation.class,
          this);

  public SelectAqlField<News2ScoreObservation> NEWS2_SCORE =
      new AqlFieldImp<News2ScoreObservation>(
          News2Section.class,
          "/items[openEHR-EHR-OBSERVATION.news2.v1]",
          "news2Score",
          News2ScoreObservation.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          News2Section.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private News2SectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static News2SectionContainment getInstance() {
    return new News2SectionContainment();
  }
}
