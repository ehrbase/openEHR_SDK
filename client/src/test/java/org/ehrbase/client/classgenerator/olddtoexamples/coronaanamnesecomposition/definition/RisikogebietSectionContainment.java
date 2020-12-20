package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class RisikogebietSectionContainment extends Containment {
  public SelectAqlField<RisikogebietSection> RISIKOGEBIET_SECTION =
      new AqlFieldImp<RisikogebietSection>(
          RisikogebietSection.class, "", "RisikogebietSection", RisikogebietSection.class, this);

  public ListSelectAqlField<HistorieDerReiseObservation> HISTORIE_DER_REISE =
      new ListAqlFieldImp<HistorieDerReiseObservation>(
          RisikogebietSection.class,
          "/items[openEHR-EHR-OBSERVATION.travel_history.v0]",
          "historieDerReise",
          HistorieDerReiseObservation.class,
          this);

  public ListSelectAqlField<ReisefallObservation> REISEFALL =
      new ListAqlFieldImp<ReisefallObservation>(
          RisikogebietSection.class,
          "/items[openEHR-EHR-OBSERVATION.travel_event.v0]",
          "reisefall",
          ReisefallObservation.class,
          this);

  private RisikogebietSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static RisikogebietSectionContainment getInstance() {
    return new RisikogebietSectionContainment();
  }
}
