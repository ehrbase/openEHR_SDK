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

public class EntlassungsdatenAdminEntryContainment extends Containment {
  public SelectAqlField<EntlassungsdatenAdminEntry> ENTLASSUNGSDATEN_ADMIN_ENTRY =
      new AqlFieldImp<EntlassungsdatenAdminEntry>(
          EntlassungsdatenAdminEntry.class,
          "",
          "EntlassungsdatenAdminEntry",
          EntlassungsdatenAdminEntry.class,
          this);

  public SelectAqlField<String> ART_DER_ENTLASSUNG_VALUE =
      new AqlFieldImp<String>(
          EntlassungsdatenAdminEntry.class,
          "/data[at0001]/items[at0040]/value|value",
          "artDerEntlassungValue",
          String.class,
          this);

  public SelectAqlField<KlinischerZustandDesPatientenDefiningCode>
      KLINISCHER_ZUSTAND_DES_PATIENTEN_DEFINING_CODE =
          new AqlFieldImp<KlinischerZustandDesPatientenDefiningCode>(
              EntlassungsdatenAdminEntry.class,
              "/data[at0001]/items[at0002]/value|defining_code",
              "klinischerZustandDesPatientenDefiningCode",
              KlinischerZustandDesPatientenDefiningCode.class,
              this);

  public SelectAqlField<TemporalAccessor> DATUM_UHRZEIT_DER_ENTLASSUNG_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          EntlassungsdatenAdminEntry.class,
          "/data[at0001]/items[at0011]/value|value",
          "datumUhrzeitDerEntlassungValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<String> ZUSATZLICHE_INFORMATIONEN_VALUE =
      new AqlFieldImp<String>(
          EntlassungsdatenAdminEntry.class,
          "/data[at0001]/items[at0050]/value|value",
          "zusatzlicheInformationenValue",
          String.class,
          this);

  public ListSelectAqlField<Cluster> LETZTER_PATIENTENSTANDORT =
      new ListAqlFieldImp<Cluster>(
          EntlassungsdatenAdminEntry.class,
          "/data[at0001]/items[at0066]",
          "letzterPatientenstandort",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> ZUGEWIESENER_PATIENTENSTANDORT =
      new ListAqlFieldImp<Cluster>(
          EntlassungsdatenAdminEntry.class,
          "/data[at0001]/items[at0067]",
          "zugewiesenerPatientenstandort",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          EntlassungsdatenAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          EntlassungsdatenAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          EntlassungsdatenAdminEntry.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  private EntlassungsdatenAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.discharge_summary.v0");
  }

  public static EntlassungsdatenAdminEntryContainment getInstance() {
    return new EntlassungsdatenAdminEntryContainment();
  }
}
