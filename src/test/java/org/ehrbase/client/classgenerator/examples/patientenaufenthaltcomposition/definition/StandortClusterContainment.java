package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.StandortschlusselDefiningcode;

public class StandortClusterContainment extends Containment {
  public SelectAqlField<StandortCluster> STANDORT_CLUSTER = new AqlFieldImp<StandortCluster>(StandortCluster.class, "", "StandortCluster", StandortCluster.class, this);

  public SelectAqlField<String> STATION_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0027]/name|value", "stationValue", String.class, this);

  public SelectAqlField<String> CAMPUS_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0024]/value|value", "campusValue", String.class, this);

  public SelectAqlField<String> ZIMMER_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0029]/name|value", "zimmerValue", String.class, this);

  public SelectAqlField<String> BETTSTELLPLATZ_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0034]/value|value", "bettstellplatzValue", String.class, this);

  public SelectAqlField<String> STANDORTTYP_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0040]/value|value", "standorttypValue", String.class, this);

  public SelectAqlField<String> STANDORTBESCHREIBUNG_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0046]/value|value", "standortbeschreibungValue", String.class, this);

  public SelectAqlField<StandortschlusselDefiningcode> STANDORTSCHLUSSEL_DEFININGCODE = new AqlFieldImp<StandortschlusselDefiningcode>(StandortCluster.class, "/items[at0048]/value|defining_code", "standortschlusselDefiningcode", StandortschlusselDefiningcode.class, this);

  public SelectAqlField<MedizingeratCluster> MEDIZINGERAT = new AqlFieldImp<MedizingeratCluster>(StandortCluster.class, "/items[openEHR-EHR-CLUSTER.device.v1]", "medizingerat", MedizingeratCluster.class, this);

  public SelectAqlField<String> BETTSTELLPLATZ_VALUE_NAME = new AqlFieldImp<String>(StandortCluster.class, "/items[at0034]/name|value", "bettstellplatzValueName", String.class, this);

  public SelectAqlField<String> ZIMMER_VALUE_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0029]/value|value", "zimmerValueValue", String.class, this);

  public SelectAqlField<String> EBENE_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0028]/value|value", "ebeneValue", String.class, this);

  public SelectAqlField<String> GEBAUDEGRUPPE_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0025]/value|value", "gebaudegruppeValue", String.class, this);

  public SelectAqlField<String> STATION_VALUE_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0027]/value|value", "stationValueValue", String.class, this);

  private StandortClusterContainment() {
    super("openEHR-EHR-CLUSTER.location.v1");
  }

  public static StandortClusterContainment getInstance() {
    return new StandortClusterContainment();
  }
}
