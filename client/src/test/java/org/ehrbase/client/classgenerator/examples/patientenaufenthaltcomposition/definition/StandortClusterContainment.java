package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class StandortClusterContainment extends Containment {
  public SelectAqlField<StandortCluster> STANDORT_CLUSTER =
      new AqlFieldImp<StandortCluster>(
          StandortCluster.class, "", "StandortCluster", StandortCluster.class, this);

  public SelectAqlField<String> STANDORTTYP_VALUE =
      new AqlFieldImp<String>(
          StandortCluster.class,
          "/items[at0040]/value|value",
          "standorttypValue",
          String.class,
          this);

  public SelectAqlField<String> STANDORTBESCHREIBUNG_VALUE =
      new AqlFieldImp<String>(
          StandortCluster.class,
          "/items[at0046]/value|value",
          "standortbeschreibungValue",
          String.class,
          this);

  public SelectAqlField<StandortschlusselDefiningCode> STANDORTSCHLUSSEL_DEFINING_CODE =
      new AqlFieldImp<StandortschlusselDefiningCode>(
          StandortCluster.class,
          "/items[at0048]/value|defining_code",
          "standortschlusselDefiningCode",
          StandortschlusselDefiningCode.class,
          this);

  public SelectAqlField<String> CAMPUS_VALUE =
      new AqlFieldImp<String>(
          StandortCluster.class, "/items[at0024]/value|value", "campusValue", String.class, this);

  public SelectAqlField<String> GEBAUDEGRUPPE_VALUE =
      new AqlFieldImp<String>(
          StandortCluster.class,
          "/items[at0025]/value|value",
          "gebaudegruppeValue",
          String.class,
          this);

  public SelectAqlField<String> EBENE_VALUE =
      new AqlFieldImp<String>(
          StandortCluster.class, "/items[at0028]/value|value", "ebeneValue", String.class, this);

  public SelectAqlField<String> STATIONSKENNUNG_VALUE =
      new AqlFieldImp<String>(
          StandortCluster.class,
          "/items[at0027]/value|value",
          "stationskennungValue",
          String.class,
          this);

  public SelectAqlField<String> ZIMMERKENNUNG_VALUE =
      new AqlFieldImp<String>(
          StandortCluster.class,
          "/items[at0029]/value|value",
          "zimmerkennungValue",
          String.class,
          this);

  public SelectAqlField<String> BETTPLATZKENNUNG_VALUE =
      new AqlFieldImp<String>(
          StandortCluster.class,
          "/items[at0034]/value|value",
          "bettplatzkennungValue",
          String.class,
          this);

  public SelectAqlField<DetailsZumBettCluster> DETAILS_ZUM_BETT =
      new AqlFieldImp<DetailsZumBettCluster>(
          StandortCluster.class,
          "/items[openEHR-EHR-CLUSTER.device.v1]",
          "detailsZumBett",
          DetailsZumBettCluster.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          StandortCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private StandortClusterContainment() {
    super("openEHR-EHR-CLUSTER.location.v1");
  }

  public static StandortClusterContainment getInstance() {
    return new StandortClusterContainment();
  }
}
