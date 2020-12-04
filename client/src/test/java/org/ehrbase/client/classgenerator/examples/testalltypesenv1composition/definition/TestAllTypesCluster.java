package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Boolean;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.test_all_types.v1")
public class TestAllTypesCluster {
  /**
   * Test all types/Test all types/section 2/section 3/Test all types/Test all types/cluster 5/cluster 6/boolean 2
   */
  @Path("/items[at0001]/items[at0002]/items[at0003]/value|value")
  private Boolean boolean2Value;

  /**
   * Test all types/Test all types/section 2/section 3/Test all types/Test all types/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBoolean2Value(Boolean boolean2Value) {
     this.boolean2Value = boolean2Value;
  }

  public Boolean isBoolean2Value() {
     return this.boolean2Value ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
