package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.test_all_types.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.812496900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class TestAllTypesSection implements LocatableEntity {
  /** Path: Test all types/Test all types/section 2/section 3/Test all types Description: unknown */
  @Path("/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]")
  private List<TestAllTypesInstruction> section3TestAllTypes;

  /** Path: Test all types/Test all types/section 2/section 3/Test all types Description: unknown */
  @Path("/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]")
  private List<TestAllTypesAction> section3TestAllTypes2;

  /** Path: Test all types/Test all types/section 2/Test all types Description: unknown */
  @Path("/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]")
  private List<TestAllTypesAdminEntry> section2TestAllTypes;

  /** Path: Test all types/Test all types/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSection3TestAllTypes(List<TestAllTypesInstruction> section3TestAllTypes) {
    this.section3TestAllTypes = section3TestAllTypes;
  }

  public List<TestAllTypesInstruction> getSection3TestAllTypes() {
    return this.section3TestAllTypes;
  }

  public void setSection3TestAllTypes2(List<TestAllTypesAction> section3TestAllTypes2) {
    this.section3TestAllTypes2 = section3TestAllTypes2;
  }

  public List<TestAllTypesAction> getSection3TestAllTypes2() {
    return this.section3TestAllTypes2;
  }

  public void setSection2TestAllTypes(List<TestAllTypesAdminEntry> section2TestAllTypes) {
    this.section2TestAllTypes = section2TestAllTypes;
  }

  public List<TestAllTypesAdminEntry> getSection2TestAllTypes() {
    return this.section2TestAllTypes;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
