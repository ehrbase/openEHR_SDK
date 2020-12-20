package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.test_all_types.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.818494500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class TestAllTypesCluster implements LocatableEntity {
  /**
   * Path: Test all types/Test all types/section 2/section 3/Test all types/Test all types/cluster
   * 5/cluster 6/boolean 2 Description: *
   */
  @Path("/items[at0001]/items[at0002]/items[at0003]/value|value")
  private Boolean boolean2Value;

  /**
   * Path: Test all types/Test all types/section 2/section 3/Test all types/Test all
   * types/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBoolean2Value(Boolean boolean2Value) {
    this.boolean2Value = boolean2Value;
  }

  public Boolean isBoolean2Value() {
    return this.boolean2Value;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
