package org.ehrbase.client.classgenerator.examples.korpergrossecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class GrosseLangeObservationContainment extends Containment {
  public SelectAqlField<GrosseLangeObservation> GROSSE_LANGE_OBSERVATION = new AqlFieldImp<GrosseLangeObservation>(GrosseLangeObservation.class, "", "GrosseLangeObservation", GrosseLangeObservation.class, this);

  public SelectAqlField<Double> GROSSE_LANGE_MAGNITUDE = new AqlFieldImp<Double>(GrosseLangeObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude", "grosseLangeMagnitude", Double.class, this);

  public SelectAqlField<String> GROSSE_LANGE_UNITS = new AqlFieldImp<String>(GrosseLangeObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units", "grosseLangeUnits", String.class, this);

  public SelectAqlField<ItemTree> TREE = new AqlFieldImp<ItemTree>(GrosseLangeObservation.class, "/data[at0001]/events[at0002]/state[at0013]", "tree", ItemTree.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GrosseLangeObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(GrosseLangeObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Cluster> GERAT = new AqlFieldImp<Cluster>(GrosseLangeObservation.class, "/protocol[at0007]/items[at0011]", "gerat", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(GrosseLangeObservation.class, "/protocol[at0007]/items[at0022]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(GrosseLangeObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(GrosseLangeObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GrosseLangeObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private GrosseLangeObservationContainment() {
    super("openEHR-EHR-OBSERVATION.height.v2");
  }

  public static GrosseLangeObservationContainment getInstance() {
    return new GrosseLangeObservationContainment();
  }
}
