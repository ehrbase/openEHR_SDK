package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class AcvpuScaleObservationContainment extends Containment {
  public SelectAqlField<AcvpuScaleObservation> ACVPU_SCALE_OBSERVATION =
      new AqlFieldImp<AcvpuScaleObservation>(
          AcvpuScaleObservation.class,
          "",
          "AcvpuScaleObservation",
          AcvpuScaleObservation.class,
          this);

  public SelectAqlField<AcvpuDefiningCode> ACVPU_DEFINING_CODE =
      new AqlFieldImp<AcvpuDefiningCode>(
          AcvpuScaleObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code",
          "acvpuDefiningCode",
          AcvpuDefiningCode.class,
          this);

  public SelectAqlField<ItemTree> TREE =
      new AqlFieldImp<ItemTree>(
          AcvpuScaleObservation.class,
          "/data[at0001]/events[at0002]/state[at0013]",
          "tree",
          ItemTree.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          AcvpuScaleObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          AcvpuScaleObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION =
      new ListAqlFieldImp<Cluster>(
          AcvpuScaleObservation.class,
          "/protocol[at0009]/items[at0011]",
          "extension",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          AcvpuScaleObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          AcvpuScaleObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          AcvpuScaleObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AcvpuScaleObservationContainment() {
    super("openEHR-EHR-OBSERVATION.acvpu.v0");
  }

  public static AcvpuScaleObservationContainment getInstance() {
    return new AcvpuScaleObservationContainment();
  }
}
