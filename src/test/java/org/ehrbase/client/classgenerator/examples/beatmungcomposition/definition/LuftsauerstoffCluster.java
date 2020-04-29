package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.ambient_oxygen.v0")
public class LuftsauerstoffCluster {
  @Path("/items[at0053]/value")
  private DvProportion prozentO2;

  @Path("/items[at0052]/value")
  private DvProportion fio2;

  @Path("/items[at0051]/value|magnitude")
  private Double sauerstoffDurchflussrateMagnitude;

  @Path("/items[at0051]/value|units")
  private String sauerstoffDurchflussrateUnits;

  public void setProzentO2(DvProportion prozentO2) {
     this.prozentO2 = prozentO2;
  }

  public DvProportion getProzentO2() {
     return this.prozentO2 ;
  }

  public void setFio2(DvProportion fio2) {
     this.fio2 = fio2;
  }

  public DvProportion getFio2() {
     return this.fio2 ;
  }

  public void setSauerstoffDurchflussrateMagnitude(Double sauerstoffDurchflussrateMagnitude) {
     this.sauerstoffDurchflussrateMagnitude = sauerstoffDurchflussrateMagnitude;
  }

  public Double getSauerstoffDurchflussrateMagnitude() {
     return this.sauerstoffDurchflussrateMagnitude ;
  }

  public void setSauerstoffDurchflussrateUnits(String sauerstoffDurchflussrateUnits) {
     this.sauerstoffDurchflussrateUnits = sauerstoffDurchflussrateUnits;
  }

  public String getSauerstoffDurchflussrateUnits() {
     return this.sauerstoffDurchflussrateUnits ;
  }
}
