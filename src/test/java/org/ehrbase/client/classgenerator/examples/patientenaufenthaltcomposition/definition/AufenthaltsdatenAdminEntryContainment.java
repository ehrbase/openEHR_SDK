package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class AufenthaltsdatenAdminEntryContainment extends Containment {
  public SelectAqlField<AufenthaltsdatenAdminEntry> AUFENTHALTSDATEN_ADMIN_ENTRY = new AqlFieldImp<AufenthaltsdatenAdminEntry>(AufenthaltsdatenAdminEntry.class, "", "AufenthaltsdatenAdminEntry", AufenthaltsdatenAdminEntry.class, this);

  public SelectAqlField<TemporalAccessor> BEGINN_VALUE = new AqlFieldImp<TemporalAccessor>(AufenthaltsdatenAdminEntry.class, "/data[at0001]/items[at0004]/value|value", "beginnValue", TemporalAccessor.class, this);

  public SelectAqlField<StandortCluster> STANDORT = new AqlFieldImp<StandortCluster>(AufenthaltsdatenAdminEntry.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.location.v1]", "standort", StandortCluster.class, this);

  public SelectAqlField<TemporalAccessor> ENDE_VALUE = new AqlFieldImp<TemporalAccessor>(AufenthaltsdatenAdminEntry.class, "/data[at0001]/items[at0005]/value|value", "endeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> GRUND_DES_AUFENTHALTES_VALUE = new AqlFieldImp<String>(AufenthaltsdatenAdminEntry.class, "/data[at0001]/items[at0006]/value|value", "grundDesAufenthaltesValue", String.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AufenthaltsdatenAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(AufenthaltsdatenAdminEntry.class, "/data[at0001]/items[at0009]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AufenthaltsdatenAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  private AufenthaltsdatenAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.hospitalization.v0");
  }

  public static AufenthaltsdatenAdminEntryContainment getInstance() {
    return new AufenthaltsdatenAdminEntryContainment();
  }
}
