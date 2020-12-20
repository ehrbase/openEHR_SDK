package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class VersorgungsortAdminEntryContainment extends Containment {
  public SelectAqlField<VersorgungsortAdminEntry> VERSORGUNGSORT_ADMIN_ENTRY =
      new AqlFieldImp<VersorgungsortAdminEntry>(
          VersorgungsortAdminEntry.class,
          "",
          "VersorgungsortAdminEntry",
          VersorgungsortAdminEntry.class,
          this);

  public SelectAqlField<TemporalAccessor> BEGINN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          VersorgungsortAdminEntry.class,
          "/data[at0001]/items[at0004]/value|value",
          "beginnValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ENDE_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          VersorgungsortAdminEntry.class,
          "/data[at0001]/items[at0005]/value|value",
          "endeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<String> GRUND_DES_AUFENTHALTES_VALUE =
      new AqlFieldImp<String>(
          VersorgungsortAdminEntry.class,
          "/data[at0001]/items[at0006]/value|value",
          "grundDesAufenthaltesValue",
          String.class,
          this);

  public SelectAqlField<StandortCluster> STANDORT =
      new AqlFieldImp<StandortCluster>(
          VersorgungsortAdminEntry.class,
          "/data[at0001]/items[openEHR-EHR-CLUSTER.location.v1]",
          "standort",
          StandortCluster.class,
          this);

  public SelectAqlField<String> KOMMENTAR_VALUE =
      new AqlFieldImp<String>(
          VersorgungsortAdminEntry.class,
          "/data[at0001]/items[at0009]/value|value",
          "kommentarValue",
          String.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          VersorgungsortAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          VersorgungsortAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          VersorgungsortAdminEntry.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private VersorgungsortAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.hospitalization.v0");
  }

  public static VersorgungsortAdminEntryContainment getInstance() {
    return new VersorgungsortAdminEntryContainment();
  }
}
