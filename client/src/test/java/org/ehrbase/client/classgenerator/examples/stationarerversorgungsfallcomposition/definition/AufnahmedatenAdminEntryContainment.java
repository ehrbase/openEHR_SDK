package org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class AufnahmedatenAdminEntryContainment extends Containment {
  public SelectAqlField<AufnahmedatenAdminEntry> AUFNAHMEDATEN_ADMIN_ENTRY =
      new AqlFieldImp<AufnahmedatenAdminEntry>(
          AufnahmedatenAdminEntry.class,
          "",
          "AufnahmedatenAdminEntry",
          AufnahmedatenAdminEntry.class,
          this);

  public SelectAqlField<String> VERSORGUNGSFALLGRUND_VALUE =
      new AqlFieldImp<String>(
          AufnahmedatenAdminEntry.class,
          "/data[at0001]/items[at0013]/value|value",
          "versorgungsfallgrundValue",
          String.class,
          this);

  public SelectAqlField<String> ART_DER_AUFNAHME_VALUE =
      new AqlFieldImp<String>(
          AufnahmedatenAdminEntry.class,
          "/data[at0001]/items[at0049]/value|value",
          "artDerAufnahmeValue",
          String.class,
          this);

  public SelectAqlField<TemporalAccessor> DATUM_UHRZEIT_DER_AUFNAHME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          AufnahmedatenAdminEntry.class,
          "/data[at0001]/items[at0071]/value|value",
          "datumUhrzeitDerAufnahmeValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> ZUGEWIESENER_PATIENTENSTANDORT =
      new ListAqlFieldImp<Cluster>(
          AufnahmedatenAdminEntry.class,
          "/data[at0001]/items[at0131]",
          "zugewiesenerPatientenstandort",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> VORHERIGER_PATIENTENSTANDORT =
      new ListAqlFieldImp<Cluster>(
          AufnahmedatenAdminEntry.class,
          "/data[at0001]/items[at0132]",
          "vorherigerPatientenstandort",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          AufnahmedatenAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          AufnahmedatenAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          AufnahmedatenAdminEntry.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AufnahmedatenAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.admission.v0");
  }

  public static AufnahmedatenAdminEntryContainment getInstance() {
    return new AufnahmedatenAdminEntryContainment();
  }
}
