package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class SepsisSectionContainment extends Containment {
  public SelectAqlField<SepsisSection> SEPSIS_SECTION =
      new AqlFieldImp<SepsisSection>(
          SepsisSection.class, "", "SepsisSection", SepsisSection.class, this);

  public SelectAqlField<SepsisScreeningObservation> SEPSIS_SCREENING =
      new AqlFieldImp<SepsisScreeningObservation>(
          SepsisSection.class,
          "/items[openEHR-EHR-OBSERVATION.sepsis_screening_tool.v0]",
          "sepsisScreening",
          SepsisScreeningObservation.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          SepsisSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private SepsisSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static SepsisSectionContainment getInstance() {
    return new SepsisSectionContainment();
  }
}
