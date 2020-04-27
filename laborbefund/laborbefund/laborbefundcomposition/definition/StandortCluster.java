package laborbefund.laborbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
public class StandortCluster {
  @Path("/items[at0046]/name|value")
  private String standortbeschreibungValue;

  @Path("/items[at0048]/name|value")
  private String standortschlusselValue;

  @Path("/items[at0047]")
  private List<Cluster> details;

  @Path("/items[at0040]/value|value")
  private String standorttypValue;

  @Path("/items[at0046]/value|value")
  private String standortbeschreibungValueValue;

  @Path("/items[at0048]/value|value")
  private String standortschlusselValueValue;

  public void setStandortbeschreibungValue(String standortbeschreibungValue) {
     this.standortbeschreibungValue = standortbeschreibungValue;
  }

  public String getStandortbeschreibungValue() {
     return this.standortbeschreibungValue ;
  }

  public void setStandortschlusselValue(String standortschlusselValue) {
     this.standortschlusselValue = standortschlusselValue;
  }

  public String getStandortschlusselValue() {
     return this.standortschlusselValue ;
  }

  public void setDetails(List<Cluster> details) {
     this.details = details;
  }

  public List<Cluster> getDetails() {
     return this.details ;
  }

  public void setStandorttypValue(String standorttypValue) {
     this.standorttypValue = standorttypValue;
  }

  public String getStandorttypValue() {
     return this.standorttypValue ;
  }

  public void setStandortbeschreibungValueValue(String standortbeschreibungValueValue) {
     this.standortbeschreibungValueValue = standortbeschreibungValueValue;
  }

  public String getStandortbeschreibungValueValue() {
     return this.standortbeschreibungValueValue ;
  }

  public void setStandortschlusselValueValue(String standortschlusselValueValue) {
     this.standortschlusselValueValue = standortschlusselValueValue;
  }

  public String getStandortschlusselValueValue() {
     return this.standortschlusselValueValue ;
  }
}
