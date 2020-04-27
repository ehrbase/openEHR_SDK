package laborbefund.laborbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class StandortDetailsDerTestanforderungCluster {
  @Path("/items[at0063]/value")
  private DvIdentifier auftragsIdEmpfanger;

  @Path("/items[at0062]/name|value")
  private String einsendendenSystemsValue;

  @Path("/items[at0062]/value")
  private DvIdentifier einsendendenSystems;

  @Path("/items[at0035]")
  private List<Cluster> verteilerliste;

  @Path("/items[at0090]")
  private Cluster einsender;

  public void setAuftragsIdEmpfanger(DvIdentifier auftragsIdEmpfanger) {
     this.auftragsIdEmpfanger = auftragsIdEmpfanger;
  }

  public DvIdentifier getAuftragsIdEmpfanger() {
     return this.auftragsIdEmpfanger ;
  }

  public void setEinsendendenSystemsValue(String einsendendenSystemsValue) {
     this.einsendendenSystemsValue = einsendendenSystemsValue;
  }

  public String getEinsendendenSystemsValue() {
     return this.einsendendenSystemsValue ;
  }

  public void setEinsendendenSystems(DvIdentifier einsendendenSystems) {
     this.einsendendenSystems = einsendendenSystems;
  }

  public DvIdentifier getEinsendendenSystems() {
     return this.einsendendenSystems ;
  }

  public void setVerteilerliste(List<Cluster> verteilerliste) {
     this.verteilerliste = verteilerliste;
  }

  public List<Cluster> getVerteilerliste() {
     return this.verteilerliste ;
  }

  public void setEinsender(Cluster einsender) {
     this.einsender = einsender;
  }

  public Cluster getEinsender() {
     return this.einsender ;
  }
}
