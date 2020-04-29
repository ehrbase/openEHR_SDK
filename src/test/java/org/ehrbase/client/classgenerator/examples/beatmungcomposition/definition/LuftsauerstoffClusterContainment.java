package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class LuftsauerstoffClusterContainment extends Containment {
  public SelectAqlField<LuftsauerstoffCluster> LUFTSAUERSTOFF_CLUSTER = new AqlFieldImp<LuftsauerstoffCluster>(LuftsauerstoffCluster.class, "", "LuftsauerstoffCluster", LuftsauerstoffCluster.class, this);

  public SelectAqlField<DvProportion> PROZENT_O2 = new AqlFieldImp<DvProportion>(LuftsauerstoffCluster.class, "/items[at0053]/value", "prozentO2", DvProportion.class, this);

  public SelectAqlField<DvProportion> FIO2 = new AqlFieldImp<DvProportion>(LuftsauerstoffCluster.class, "/items[at0052]/value", "fio2", DvProportion.class, this);

  public SelectAqlField<Double> SAUERSTOFF_DURCHFLUSSRATE_MAGNITUDE = new AqlFieldImp<Double>(LuftsauerstoffCluster.class, "/items[at0051]/value|magnitude", "sauerstoffDurchflussrateMagnitude", Double.class, this);

  public SelectAqlField<String> SAUERSTOFF_DURCHFLUSSRATE_UNITS = new AqlFieldImp<String>(LuftsauerstoffCluster.class, "/items[at0051]/value|units", "sauerstoffDurchflussrateUnits", String.class, this);

  private LuftsauerstoffClusterContainment() {
    super("openEHR-EHR-CLUSTER.ambient_oxygen.v0");
  }

  public static LuftsauerstoffClusterContainment getInstance() {
    return new LuftsauerstoffClusterContainment();
  }
}
