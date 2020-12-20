package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

public class TestAllTypesInstructionContainment extends Containment {
  public SelectAqlField<TestAllTypesInstruction> TEST_ALL_TYPES_INSTRUCTION =
      new AqlFieldImp<TestAllTypesInstruction>(
          TestAllTypesInstruction.class,
          "",
          "TestAllTypesInstruction",
          TestAllTypesInstruction.class,
          this);

  public SelectAqlField<String> NARRATIVE_VALUE =
      new AqlFieldImp<String>(
          TestAllTypesInstruction.class, "/narrative|value", "narrativeValue", String.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          TestAllTypesInstruction.class, "/language", "language", Language.class, this);

  public SelectAqlField<ItemStructure> DESCRIPTION =
      new AqlFieldImp<ItemStructure>(
          TestAllTypesInstruction.class,
          "/activities[at0001]/description",
          "description",
          ItemStructure.class,
          this);

  public SelectAqlField<Temporal> PARTIAL_DATE_VALUE =
      new AqlFieldImp<Temporal>(
          TestAllTypesInstruction.class,
          "/activities[at0001]/description[at0002]/items[at0003]/value|value",
          "partialDateValue",
          Temporal.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          TestAllTypesInstruction.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<String> CURRENT_ACTIVITY =
      new AqlFieldImp<String>(
          TestAllTypesInstruction.class,
          "/activities[at0001]/action_archetype_id",
          "currentActivity",
          String.class,
          this);

  public SelectAqlField<TemporalAccessor> PARTIAL_DATETIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          TestAllTypesInstruction.class,
          "/activities[at0001]/description[at0002]/items[at0004]/value|value",
          "partialDatetimeValue",
          TemporalAccessor.class,
          this);

  private TestAllTypesInstructionContainment() {
    super("openEHR-EHR-INSTRUCTION.test_all_types.v1");
  }

  public static TestAllTypesInstructionContainment getInstance() {
    return new TestAllTypesInstructionContainment();
  }
}
