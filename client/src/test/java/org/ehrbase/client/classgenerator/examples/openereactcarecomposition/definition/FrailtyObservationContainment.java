package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class FrailtyObservationContainment extends Containment {
  public SelectAqlField<FrailtyObservation> FRAILTY_OBSERVATION =
      new AqlFieldImp<FrailtyObservation>(
          FrailtyObservation.class, "", "FrailtyObservation", FrailtyObservation.class, this);

  public SelectAqlField<DvOrdinal> ASSESSMENT =
      new AqlFieldImp<DvOrdinal>(
          FrailtyObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value",
          "assessment",
          DvOrdinal.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          FrailtyObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          FrailtyObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION =
      new ListAqlFieldImp<Cluster>(
          FrailtyObservation.class,
          "/protocol[at0014]/items[at0015]",
          "extension",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          FrailtyObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          FrailtyObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          FrailtyObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private FrailtyObservationContainment() {
    super("openEHR-EHR-OBSERVATION.clinical_frailty_scale.v1");
  }

  public static FrailtyObservationContainment getInstance() {
    return new FrailtyObservationContainment();
  }
}
