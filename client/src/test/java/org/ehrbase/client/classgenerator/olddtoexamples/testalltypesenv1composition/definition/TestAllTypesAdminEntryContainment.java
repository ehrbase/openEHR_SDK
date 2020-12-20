package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

public class TestAllTypesAdminEntryContainment extends Containment {
  public SelectAqlField<TestAllTypesAdminEntry> TEST_ALL_TYPES_ADMIN_ENTRY =
      new AqlFieldImp<TestAllTypesAdminEntry>(
          TestAllTypesAdminEntry.class,
          "",
          "TestAllTypesAdminEntry",
          TestAllTypesAdminEntry.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          TestAllTypesAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          TestAllTypesAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<Long> COUNT3_MAGNITUDE =
      new AqlFieldImp<Long>(
          TestAllTypesAdminEntry.class,
          "/data[at0001]/item[at0002]/value|magnitude",
          "count3Magnitude",
          Long.class,
          this);

  private TestAllTypesAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.test_all_types.v1");
  }

  public static TestAllTypesAdminEntryContainment getInstance() {
    return new TestAllTypesAdminEntryContainment();
  }
}
