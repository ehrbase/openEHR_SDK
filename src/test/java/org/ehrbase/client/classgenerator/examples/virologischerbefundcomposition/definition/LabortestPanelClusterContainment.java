package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class LabortestPanelClusterContainment extends Containment {
  public SelectAqlField<LabortestPanelCluster> LABORTEST_PANEL_CLUSTER = new AqlFieldImp<LabortestPanelCluster>(LabortestPanelCluster.class, "", "LabortestPanelCluster", LabortestPanelCluster.class, this);

  public ListSelectAqlField<LaboranalytResultatCluster> LABORANALYT_RESULTAT = new ListAqlFieldImp<LaboranalytResultatCluster>(LabortestPanelCluster.class, "/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]", "laboranalytResultat", LaboranalytResultatCluster.class, this);

  private LabortestPanelClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_panel.v0");
  }

  public static LabortestPanelClusterContainment getInstance() {
    return new LabortestPanelClusterContainment();
  }
}
