package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class ErregertypisierungClusterContainment extends Containment {
  public SelectAqlField<ErregertypisierungCluster> ERREGERTYPISIERUNG_CLUSTER = new AqlFieldImp<ErregertypisierungCluster>(ErregertypisierungCluster.class, "", "ErregertypisierungCluster", ErregertypisierungCluster.class, this);

  public SelectAqlField<String> BEWERTUNG_VALUE = new AqlFieldImp<String>(ErregertypisierungCluster.class, "/items[at0009]/value|value", "bewertungValue", String.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(ErregertypisierungCluster.class, "/items[at0002]/value|value", "kommentarValue", String.class, this);

  public ListSelectAqlField<EigenschaftenDesBeteiligtenErregersErregertypisierungArtDerTypisierungElement> ART_DER_TYPISIERUNG = new ListAqlFieldImp<EigenschaftenDesBeteiligtenErregersErregertypisierungArtDerTypisierungElement>(ErregertypisierungCluster.class, "/items[at0001]", "artDerTypisierung", EigenschaftenDesBeteiligtenErregersErregertypisierungArtDerTypisierungElement.class, this);

  public ListSelectAqlField<EigenschaftenDesBeteiligtenErregersErregertypisierungErgebnisElement> ERGEBNIS = new ListAqlFieldImp<EigenschaftenDesBeteiligtenErregersErregertypisierungErgebnisElement>(ErregertypisierungCluster.class, "/items[at0008]", "ergebnis", EigenschaftenDesBeteiligtenErregersErregertypisierungErgebnisElement.class, this);

  private ErregertypisierungClusterContainment() {
    super("openEHR-EHR-CLUSTER.molekulare_typisierung.v0");
  }

  public static ErregertypisierungClusterContainment getInstance() {
    return new ErregertypisierungClusterContainment();
  }
}
