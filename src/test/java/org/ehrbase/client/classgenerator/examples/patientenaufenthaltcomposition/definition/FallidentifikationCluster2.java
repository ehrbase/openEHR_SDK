package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
@OptionFor("CLUSTER")
public class FallidentifikationCluster2 implements PatientenaufenthaltOrgEhrbaseEhrEncodeWrappersSnakecase6ab72419FallidentifikationChoice {
  @Path("/items[at0001]/value|value")
  private String fallKennungValue;

  @Path("/items[at0001]/name|value")
  private String fallKennungValueName;

  public void setFallKennungValue(String fallKennungValue) {
     this.fallKennungValue = fallKennungValue;
  }

  public String getFallKennungValue() {
     return this.fallKennungValue ;
  }

  public void setFallKennungValueName(String fallKennungValueName) {
     this.fallKennungValueName = fallKennungValueName;
  }

  public String getFallKennungValueName() {
     return this.fallKennungValueName ;
  }
}
