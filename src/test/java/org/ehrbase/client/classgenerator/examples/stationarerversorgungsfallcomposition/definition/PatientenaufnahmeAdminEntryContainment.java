package org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class PatientenaufnahmeAdminEntryContainment extends Containment {
  public SelectAqlField<PatientenaufnahmeAdminEntry> PATIENTENAUFNAHME_ADMIN_ENTRY = new AqlFieldImp<PatientenaufnahmeAdminEntry>(PatientenaufnahmeAdminEntry.class, "", "PatientenaufnahmeAdminEntry", PatientenaufnahmeAdminEntry.class, this);

  public ListSelectAqlField<Cluster> VORHERIGER_PATIENTENSTANDORT = new ListAqlFieldImp<Cluster>(PatientenaufnahmeAdminEntry.class, "/data[at0001]/items[at0132]", "vorherigerPatientenstandort", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(PatientenaufnahmeAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<String> ART_DER_AUFNAHME_VALUE = new AqlFieldImp<String>(PatientenaufnahmeAdminEntry.class, "/data[at0001]/items[at0049]/value|value", "artDerAufnahmeValue", String.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(PatientenaufnahmeAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<String> AUFNAHMEGRUND_VALUE = new AqlFieldImp<String>(PatientenaufnahmeAdminEntry.class, "/data[at0001]/items[at0013]/name|value", "aufnahmegrundValue", String.class, this);

  public ListSelectAqlField<Cluster> ZUGEWIESENER_PATIENTENSTANDORT = new ListAqlFieldImp<Cluster>(PatientenaufnahmeAdminEntry.class, "/data[at0001]/items[at0131]", "zugewiesenerPatientenstandort", Cluster.class, this);

  public SelectAqlField<String> AUFNAHMEGRUND_VALUE_TREE = new AqlFieldImp<String>(PatientenaufnahmeAdminEntry.class, "/data[at0001]/items[at0013]/value|value", "aufnahmegrundValueTree", String.class, this);

  public SelectAqlField<TemporalAccessor> UHRZEIT_DER_AUFNAHME_VALUE = new AqlFieldImp<TemporalAccessor>(PatientenaufnahmeAdminEntry.class, "/data[at0001]/items[at0071]/value|value", "uhrzeitDerAufnahmeValue", TemporalAccessor.class, this);

  private PatientenaufnahmeAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.admission.v0");
  }

  public static PatientenaufnahmeAdminEntryContainment getInstance() {
    return new PatientenaufnahmeAdminEntryContainment();
  }
}
