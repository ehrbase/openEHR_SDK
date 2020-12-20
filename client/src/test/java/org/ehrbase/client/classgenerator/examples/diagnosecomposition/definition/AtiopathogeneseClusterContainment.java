package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AtiopathogeneseClusterContainment extends Containment {
  public SelectAqlField<AtiopathogeneseCluster> ATIOPATHOGENESE_CLUSTER =
      new AqlFieldImp<AtiopathogeneseCluster>(
          AtiopathogeneseCluster.class,
          "",
          "AtiopathogeneseCluster",
          AtiopathogeneseCluster.class,
          this);

  public ListSelectAqlField<AtiopathogeneseAtiologieDerKrankheitElement> ATIOLOGIE_DER_KRANKHEIT =
      new ListAqlFieldImp<AtiopathogeneseAtiologieDerKrankheitElement>(
          AtiopathogeneseCluster.class,
          "/items[at0001]",
          "atiologieDerKrankheit",
          AtiopathogeneseAtiologieDerKrankheitElement.class,
          this);

  public ListSelectAqlField<AtiopathogeneseBeschreibungDesEntstehensElement>
      BESCHREIBUNG_DES_ENTSTEHENS =
          new ListAqlFieldImp<AtiopathogeneseBeschreibungDesEntstehensElement>(
              AtiopathogeneseCluster.class,
              "/items[at0017]",
              "beschreibungDesEntstehens",
              AtiopathogeneseBeschreibungDesEntstehensElement.class,
              this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          AtiopathogeneseCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AtiopathogeneseClusterContainment() {
    super("openEHR-EHR-CLUSTER.etiology.v1");
  }

  public static AtiopathogeneseClusterContainment getInstance() {
    return new AtiopathogeneseClusterContainment();
  }
}
